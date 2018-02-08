package me.arthrougs;


import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener{
	public int playerCount = 0;
	private final testPlugin main;
	public PlayerListener(testPlugin plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.main = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		playerCount = playerCount+1;
		
		if(playerCount == 1){
			player.sendMessage(ChatColor.BLUE + "You are the only player online!");
		}else{
			player.sendMessage(ChatColor.BLUE + "Welcome " + player.getName() + "! There are " + playerCount + " players online!");
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event){
		playerCount = playerCount-1;
	}
	
	
}
