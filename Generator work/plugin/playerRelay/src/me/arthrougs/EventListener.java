package me.arthrougs;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener{
	public int playerCount = 0;
	
	public randomTP plugin;
	
	public EventListener(randomTP instance){
		plugin = instance;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		playerCount = playerCount+1;
		plugin.insertToTable(player.getName());
		
		if(playerCount == 1){
			player.sendMessage(ChatColor.BLUE + "You are the only player online!");
		}else{
			player.sendMessage(ChatColor.BLUE + "Welcome " + player.getName() + "! There are " + playerCount + " players online!");
		}
		player.sendMessage(ChatColor.DARK_RED + plugin.joinMessage);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onQuit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		plugin.removeFromTable(player.getName());
		playerCount = playerCount-1;
		plugin.removeOriginalLocHash(player);
		plugin.removeTPAHash(player);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void dmg(final EntityDamageEvent event)
	{
		Entity e = event.getEntity();
		ArrayList<Player>  falling = plugin.getList();
		if(event.getCause() == DamageCause.FALL){
			if(e instanceof Player){
				Player player = (Player)e;
				if(falling.contains(player)){
					event.setCancelled(true);
					plugin.removePlayerFalling(player);
				}
			}
		}
		if(event.getCause() == DamageCause.SUFFOCATION){
			if (e instanceof Player){
				Player player = (Player)e;
				boolean inGround = true;
				Location teleportLocation = null;
				Location originalLocation = player.getLocation();
				
				double x = originalLocation.getX();
				double z = originalLocation.getZ();
				double y = originalLocation.getY();
				
				while (inGround == true){
					teleportLocation = new Location(player.getWorld(), x,y,z);
					
					if(teleportLocation.getBlock().getType() == Material.AIR){
						inGround = false;
						player.teleport(new Location(player.getWorld(), teleportLocation.getX(), teleportLocation.getY() + 1, teleportLocation.getZ()));
	
					}else{
						y=y+1;
					}
				
				}
			}
		}
	}
	
	
	
	
}
