package me.arthrougs;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class buyGUI implements Listener{
	
	public shopGUI plugin;
	public String bold = ChatColor.BOLD + "";
	public String ital = ChatColor.ITALIC + "";
	HashMap<Player, Integer> buyPage = new HashMap<Player, Integer>();
	ArrayList<ItemStack> p1Contents = new ArrayList<ItemStack>();
	
	@EventHandler
	public void onLogin(PlayerJoinEvent event){
		Player player = (Player)event.getPlayer();
		buyPage.put(player, 0);
	}
	
	public void onQuit(PlayerQuitEvent event){
		Player player = (Player)event.getPlayer();
		if(buyPage.containsKey(player)){
			buyPage.remove(player);
		}
		
	}
	
	
	public buyGUI(shopGUI instance){
		plugin = instance;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	//REWRITE AND IMPLEMENT inventorySerializer TO HADLE SERIALIZER
	//REWRITE WITH ONLY ONE METHOD TO HANDLE ALL GUI'S
	
	public void openBuyP1(Player player){
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GOLD + bold + ital + "Buy: Materials");
		if(plugin.getConfig().getString("tabs.one") == null){
			player.sendMessage(ChatColor.RED + "There is no shop right now! Sorry!");
		}else{
			//Item Stack
			ItemStack Next = new ItemStack(Material.ARROW);
			
			//Item Meta
			ItemMeta NextMeta = Next.getItemMeta();
			
			//Set Meta
			NextMeta.setDisplayName(ChatColor.DARK_RED + bold + "Next");
			Next.setItemMeta(NextMeta);
			
			inv.setItem(50, Next);
			
			buyPage.put(player, 1);
			player.openInventory(inv);
		}
	}
	
	
	public void openBuyP2(Player player){
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GOLD + bold + ital + "Buy: Commands");
		
		//Item Stack
		ItemStack Buy = new ItemStack(Material.PAPER);
		ItemStack Next = new ItemStack(Material.ARROW);
		ItemStack Back = new ItemStack(Material.ARROW);
		
		//Item Meta
		ItemMeta BuyMeta = Buy.getItemMeta();
		ItemMeta NextMeta = Next.getItemMeta();
		ItemMeta BackMeta = Next.getItemMeta();
		
		//Set Meta
		BuyMeta.setDisplayName(ChatColor.DARK_PURPLE + bold + ital + "Temp items");
		Buy.setItemMeta(BuyMeta);
		NextMeta.setDisplayName(ChatColor.DARK_RED + bold + "Next");
		Next.setItemMeta(NextMeta);
		BackMeta.setDisplayName(ChatColor.DARK_RED + bold + "Back");
		Back.setItemMeta(BackMeta);
		
		
		inv.setItem(0, Buy);
		inv.setItem(50, Next);
		inv.setItem(48, Back);
		
		buyPage.put(player, 2);
		player.openInventory(inv);
	}
	
	public void openBuyP3(Player player){
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GOLD + bold + ital + "Buy: Services");
		
		//Item Stack
		ItemStack Buy = new ItemStack(Material.PAPER);
		ItemStack Next = new ItemStack(Material.ARROW);
		ItemStack Back = new ItemStack(Material.ARROW);
		//Item Meta
		
		ItemMeta BuyMeta = Buy.getItemMeta();
		ItemMeta BackMeta = Next.getItemMeta();
		
		//Set Meta
		BuyMeta.setDisplayName(ChatColor.DARK_PURPLE + bold + ital + "Temp items");
		Buy.setItemMeta(BuyMeta);
		BackMeta.setDisplayName(ChatColor.DARK_RED + bold + "Back");
		Back.setItemMeta(BackMeta);
		
		
		inv.setItem(0, Buy);
		//inv.setItem(50, Next);
		inv.setItem(48, Back);
		
		buyPage.put(player, 3);
		player.openInventory(inv);
	}
	
	
	public void openABuyP1(Player player) {
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_RED + "Admin " + ChatColor.GOLD + bold + ital + "Buy: Materials");
		if(plugin.getConfig().getString("tabs.one") == null){
			player.sendMessage(ChatColor.RED + "There is no inventory data!");
		}else{
			//Get inventory
			player.sendMessage("Getting inventory data from config");
		}
			//Item Stack
			ItemStack Next = new ItemStack(Material.ARROW);
			
			//Item Meta
			ItemMeta NextMeta = Next.getItemMeta();
			
			//Set Meta
			NextMeta.setDisplayName(ChatColor.DARK_RED + bold + "Next");
			Next.setItemMeta(NextMeta);
			
			inv.setItem(50, Next);
			
			buyPage.put(player, 1);
			player.openInventory(inv);
		
			
		}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		if(!(event.getInventory().getName().equalsIgnoreCase(ChatColor.GOLD + bold + ital + "Buy: Materials") || event.getInventory().getName().equalsIgnoreCase(ChatColor.GOLD + bold + ital + "Buy: Commands") || event.getInventory().getName().equalsIgnoreCase(ChatColor.GOLD + bold + ital + "Buy: Services")))
			return;
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);
		
		if(event.getCurrentItem()==null || event.getCurrentItem().getType()==Material.AIR || !event.getCurrentItem().hasItemMeta()){
			player.closeInventory();
			return;
		}
		
		switch(event.getCurrentItem().getType()){
		case PAPER:
			player.sendMessage(ChatColor.GREEN + bold + ital + "Openning Buy!");
			break;
		case ARROW:
			try{
				handleArrows(event.getCurrentItem(), player);
			}catch(NullPointerException e){
				
			}
			
		default:
			break;
		}
	}
	
	public void handleArrows(ItemStack arrow, Player player){
		if(arrow.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_RED + bold + "Next")){
			int currentGUI = buyPage.get(player);
			switch(currentGUI){
			case 1:
				openBuyP2(player);
				break;
			case 2:
				openBuyP3(player);
				break;
			default:
				break;
			}
		}
		if(arrow.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_RED + bold + "Back")){
			int currentGUI = buyPage.get(player);
			switch(currentGUI){
			case 2: 
				openBuyP1(player);
				break;
			case 3:
				openBuyP2(player);
				break;
			default:
				break;
			}
		}
	}

	
	
}
