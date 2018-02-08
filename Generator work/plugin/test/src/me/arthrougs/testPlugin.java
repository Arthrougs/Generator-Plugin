package me.arthrougs;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class testPlugin extends JavaPlugin {
	
	
	
	@Override
	public void onEnable(){
		new PlayerListener(this);
	}
	
	@Override
	public void onDisable(){
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if(cmd.getName().equalsIgnoreCase("herro") && sender instanceof Player){
			
			Player player = (Player) sender;
			player.sendMessage("Hello," + player.getName());
			return true;
		}
		
		return false;
	}
	
	
	

}
