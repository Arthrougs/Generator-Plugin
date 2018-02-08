package me.arthrougs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class randomTP
  extends JavaPlugin
{
  public ArrayList<Player> playerFalling = new ArrayList<Player>();
  public HashMap<String, Location> originalLoc = new HashMap<String, Location>();
  public HashMap<Player, Player> tpa = new HashMap<Player, Player>();
  public String joinMessage = "";
  private Connection conn;
  public void onEnable()
  {
    try{
    	this.conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/lightninglog", "ehrem", "flashy!");
    }catch(SQLException e){
    	getLogger().log(Level.WARNING, "Failed to connect to database.");
    }
	new EventListener(this);
    getLogger().log(Level.INFO, "Plugin started!");
  }
  
  public void onDisable() {
	  try {
          this.conn.close();
      } catch (SQLException e) {
          getLogger().log(Level.WARNING, "Failed to close connection.");
      }
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    Player player = (Player)sender;
    
    //RANDOM TELEPORT COMMAND
    if ((cmd.getName().equalsIgnoreCase("rtp")) && ((sender instanceof Player)))
    {
      removeOriginalLocHash(player);
      Location originalLocation = player.getLocation();
      this.originalLoc.put(player.getName(), originalLocation);
      Location teleportLocation = null;
      Random random = new Random();
      
      int x = random.nextInt(10000) + 1;
      int y = 150;
      int z = random.nextInt(10000) + 1;
      boolean OnLand = false;
      this.playerFalling.add(player);
      while (!OnLand)
      {
        teleportLocation = new Location(player.getWorld(), x, y, z);
        if (teleportLocation.getBlock().getType() != Material.AIR)
        {
          OnLand = true;
          player.teleport(new Location(player.getWorld(), teleportLocation.getX(), teleportLocation.getY() + 1.0D, teleportLocation.getZ()));
        }
        else
        {
          y--;
        }
      }
      player.sendMessage(ChatColor.BLUE + "You have been teleported " + (int)teleportLocation.distance(originalLocation) + " blocks away!");
      getLogger().log(Level.INFO, "Teleported " + player.getName() + " to random location");
      return true;
    }
    
    //BACK COMMAND
    if ((cmd.getName().equalsIgnoreCase("back")) && ((sender instanceof Player)))
    {
      Location backLocation = (Location)this.originalLoc.get(player.getName());
      if (backLocation == null)
      {
        player.sendMessage(ChatColor.RED + "There is no location to return to!");
      }
      else
      {
    	  Location originalLocation = player.getLocation();
          this.originalLoc.put(player.getName(), originalLocation);
    	  player.teleport(backLocation);
      }
    }
    
    //TPA COMMAND
    if ((cmd.getName().equalsIgnoreCase("tpa")) && ((sender instanceof Player)))
    {
      if (args.length == 1)
      {
        Player target = Bukkit.getPlayer(args[0]);
        if (target != null)
        {
          this.tpa.put(target, player);
          player.sendMessage(ChatColor.DARK_PURPLE + "Teleport request sent to " + target.getName());
          target.sendMessage(ChatColor.DARK_PURPLE + player.getName() + " would like to teleport to you! /tpaccept to accept");
        }
        else
        {
          player.sendMessage(ChatColor.RED + "Player does not exist!");
        }
      }
      else
      {
        player.sendMessage(ChatColor.RED + "/tpa [user]");
      }
    }
    
    //TPACCEPT COMMAND
    else if ((cmd.getName().equalsIgnoreCase("tpaccept")) && ((sender instanceof Player))) {
      if (this.tpa.get(player) != null)
      {
        removeOriginalLocHash((Player)this.tpa.get(player));
        Location originalLocation = ((Player)this.tpa.get(player)).getLocation();
        this.originalLoc.put(((Player)this.tpa.get(player)).getName(), originalLocation);
        ((Player)this.tpa.get(player)).teleport(player);
        ((Player)this.tpa.get(player)).sendMessage(ChatColor.BLUE + "Teleport request accepted!");
        player.sendMessage(ChatColor.BLUE + "You have accepted the teleport request!");
        this.tpa.remove(player);
      }
      else
      {
        player.sendMessage(ChatColor.RED + "No tpa request found!");
      }
    }
    
    //SET HOME COMMAND
    if ((cmd.getName().equalsIgnoreCase("sethome")) && ((sender instanceof Player)))
    {
      Location loc = player.getLocation();
      String path = "Homes." + player.getName();
      
      getConfig().set(path + ".x", Double.valueOf(loc.getX()));
      getConfig().set(path + ".y", Double.valueOf(loc.getY()));
      getConfig().set(path + ".z", Double.valueOf(loc.getZ()));
      getConfig().set(path + ".world", player.getWorld().getName());
      getLogger().log(Level.INFO, "Set home for " + player.getName());
      player.sendMessage(ChatColor.BLUE + "Home has been set!");
    }
    
    //HOME COMMAND
    else if ((cmd.getName().equalsIgnoreCase("home")) && ((sender instanceof Player)))
    {
      Location homeLoc = player.getLocation();
      removeOriginalLocHash(player);
      Location originalLocation = player.getLocation();
      this.originalLoc.put(player.getName(), originalLocation);
      
      String path = "Homes." + player.getName();
      if (getConfig().getString(path + ".x") != null)
      {
        double x = Double.parseDouble(getConfig().getString(path + ".x"));
        double y = Double.parseDouble(getConfig().getString(path + ".y"));
        double z = Double.parseDouble(getConfig().getString(path + ".z"));
        String world = getConfig().getString(path + ".world");
        World homeWorld = Bukkit.getWorld(world);
        
        homeLoc.setX(x);
        homeLoc.setY(y);
        homeLoc.setZ(z);
        homeLoc.setWorld(homeWorld);
        
        player.sendMessage(ChatColor.YELLOW + "Teleporting Home!");
        player.teleport(homeLoc);
      }
      else
      {
        player.sendMessage(ChatColor.DARK_RED + "You have not set a home!");
      }
    }
    
    else if ((cmd.getName().equalsIgnoreCase("setMOTD")) && ((sender instanceof Player))){
    	if (args.length != 0)
        {
    		String messageCreator = "";
    		for(int i = 0; i<args.length; i++){
        	   if(i == 0)
    			messageCreator = args[i];
        	   else{
        		   messageCreator = messageCreator + " " + args[i];
        	   }
          }
    		joinMessage = messageCreator;
        }else{
        	player.sendMessage("To use /setMOTD: /setMOTD <message>");
        }
    }
    else if((cmd.getName().equalsIgnoreCase("submit")) && ((sender instanceof Player))){
    	String name = player.getName();
    	String insertState = "INSERT INTO data" + "(PlayerName) Values" + "(?)";
    	try {
			PreparedStatement ps = conn.prepareStatement(insertState);
			ps.setString(1, name + "Kek");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    return false;
  }
  
  
  public void insertToTable(String name){
	  String insertState = "INSERT INTO data" + "(PlayerName) Values" + "(?)";
  	try {
			PreparedStatement ps = conn.prepareStatement(insertState);
			ps.setString(1, name);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
  }
  
  public void removeFromTable(String name){
	  String insertState = "DELETE FROM data WHERE PlayerName = ?";
  	try {
			PreparedStatement ps = conn.prepareStatement(insertState);
			ps.setString(1, name);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
  }
  
  //ACCESSORS/MUTATORS
  public void addPlayerFalling(Player player)
  {
    this.playerFalling.add(player);
  }
  
  public void removePlayerFalling(Player player)
  {
    this.playerFalling.remove(player);
  }
  
  public ArrayList<Player> getList()
  {
    return this.playerFalling;
  }
  
  public void removeOriginalLocHash(Player player)
  {
    if (this.originalLoc.containsKey(player)) {
      this.originalLoc.remove(player);
    }
  }
  
  public void removeTPAHash(Player player)
  {
    if (this.tpa.containsKey(player)) {
      this.tpa.remove(player);
    }
  }
  
  public String getJoinMessage(){
	  return joinMessage;
  }
}
