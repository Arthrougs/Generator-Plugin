package me.arthrougs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class shopGUI extends JavaPlugin{

	public EventListener listening = null;
	public buyGUI buygui = null;
	public String bold = ChatColor.BOLD + "";
	public String ital = ChatColor.ITALIC + "";
	ArrayList<ItemStack> p1Contents = new ArrayList<ItemStack>();
	
	//test
	/*REWRITE SQL TO RUN ON ASYC THREAD, NOT SYNC!!!! WILL CAUSE MAJOR LAG IF NOT SWITCHED!!!
	READ ME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	READ ME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	READ ME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	READ ME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	READ ME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	READ ME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	READ ME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	READ ME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	READ ME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	READ ME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	READ ME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	READ ME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	READ ME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	READ ME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	READ ME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	READ ME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	*/
	
	private Connection conn;
	
	@Override
	public void onEnable(){
		try{
	    	this.conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/lightninglog", "ehrem", "flashy!");
	    }catch(SQLException e){
	    	getLogger().log(Level.WARNING, "Failed to connect to database.");
	    }
		listening = new EventListener(this);
		buygui = new buyGUI(this);
		getLogger().log(Level.INFO, "shopGUI started!");
	}
	
	@Override
	public void onDisable(){
		try {
	          this.conn.close();
	      } catch (SQLException e) {
	          getLogger().log(Level.WARNING, "Failed to close connection.");
	      }
	}

	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
	  Player player = (Player)sender;
	  if ((cmd.getName().equalsIgnoreCase("adminshop")) && ((sender instanceof Player))){
			listening.openAdminGUI(player);
		  	return true;
	  }
	  
	  if ((cmd.getName().equalsIgnoreCase("msi")) && ((sender instanceof Player))){
			if(args.length == 0){
				player.sendMessage("Usage: /msi <Item> <Price> <Amount>");
			}else if(args.length == 3){
				player.sendMessage(args[0]);
				ItemStack is = null;
				try{
					 is = new ItemStack(Material.getMaterial(args[0]), Integer.parseInt(args[2]));
				}catch(Exception e){
					player.sendMessage(ChatColor.RED + "Material was typed incorrectly or amount was not specified correctly");
					return true;
				}	
				ItemMeta im = is.getItemMeta();
				List<String> lore = new ArrayList<String>();
			
				lore.add(ChatColor.GOLD + "Price: $" + args[1]);
				im.setLore(lore);
				is.setItemMeta(im);
				player.getInventory().addItem(is);
				return true;
			}
	  }
	  
	  return false;
	}
	
}
