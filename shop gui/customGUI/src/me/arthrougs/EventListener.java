package me.arthrougs;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class EventListener implements Listener{

	public shopGUI plugin;
	public String bold = ChatColor.BOLD + "";
	public String ital = ChatColor.ITALIC + "";
	public buyGUI buygui = null;
	
	//FIX SNOWBALL THROWABLE ERROR
	/*
	 * CAUSE:
	 * 		Skeletons
	 * 		Dispensers
	 * 		Droppers
	 * 		Witches
	 * 		Solution: Change snowball throwable to activate only on instance of player.
	 */
	//COOLDOWN ON SNOWBALL THOW
	//FIX 
	
	
	public EventListener(shopGUI instance){
		plugin = instance;
		buygui = new buyGUI(plugin);
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	public void openGUI(Player player){
		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GOLD + bold + ital + "Shop");
		
		ItemStack Buy = new ItemStack(Material.EMERALD_BLOCK);
		ItemMeta BuyMeta = Buy.getItemMeta();
		ItemStack Sell = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta SellMeta = Sell.getItemMeta();
		
		BuyMeta.setDisplayName(ChatColor.GREEN + bold + ital + "Buy");
		Buy.setItemMeta(BuyMeta);
		SellMeta.setDisplayName(ChatColor.RED + bold + ital + "Sell");
		Sell.setItemMeta(SellMeta);
		
		inv.setItem(12, Buy);
		inv.setItem(14, Sell);
		
		player.openInventory(inv);
	}
	
	public void openAdminGUI(Player player){
		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GOLD + bold + ital + "Admin GUI");
		
		ItemStack Buy = new ItemStack(Material.EMERALD_BLOCK);
		ItemMeta BuyMeta = Buy.getItemMeta();
		ItemStack Sell = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta SellMeta = Sell.getItemMeta();
		
		BuyMeta.setDisplayName(ChatColor.GREEN + bold + ital + "Buy");
		Buy.setItemMeta(BuyMeta);
		SellMeta.setDisplayName(ChatColor.RED + bold + ital + "Sell");
		Sell.setItemMeta(SellMeta);
		
		inv.setItem(12, Buy);
		inv.setItem(14, Sell);
		
		player.openInventory(inv);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		if(ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("shop"))
		{
			Player player = (Player) event.getWhoClicked();
			event.setCancelled(true);
			
			if(event.getCurrentItem()==null || event.getCurrentItem().getType()==Material.AIR || !event.getCurrentItem().hasItemMeta()){
				player.closeInventory();
				return;
			}
			
			switch(event.getCurrentItem().getType()){
			case EMERALD_BLOCK:
				player.sendMessage(ChatColor.GREEN + bold + ital + "Opening Buy!");
				buygui.openBuyP1(player);
				break;
			case REDSTONE_BLOCK:
				player.sendMessage(ChatColor.RED + bold + ital + "Opening Sell!");
				break;
			default:
				break;
			}
		}else if(ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("admin gui")){
			Player player = (Player) event.getWhoClicked();
			event.setCancelled(true);
			
			if(event.getCurrentItem()==null || event.getCurrentItem().getType()==Material.AIR || !event.getCurrentItem().hasItemMeta()){
				player.closeInventory();
				return;
			}
			
			switch(event.getCurrentItem().getType()){
			case EMERALD_BLOCK:
				player.sendMessage(ChatColor.GREEN + bold + ital + "Opening Buy!");
				buygui.openABuyP1(player);
				break;
			case REDSTONE_BLOCK:
				player.sendMessage(ChatColor.RED + bold + ital + "Opening Sell!");
				break;
			default:
				break;
			}
		}
		
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event){
		Item item = event.getItemDrop();
		if(item != null && item.getItemStack().getType() == Material.SNOW_BALL){
			if(item.getItemStack().hasItemMeta() && item.getItemStack().getItemMeta().hasDisplayName() && item.getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + bold + ital + "Shop")){
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = (Player)event.getPlayer();
		Inventory inv = player.getInventory();
		if (!inv.contains(Material.SNOW_BALL)){
			event.getPlayer().getInventory().addItem(createShopSnowball());
		}
			
	
		
	}
	
	public ItemStack createShopSnowball(){
		ItemStack shopButton= new ItemStack(Material.SNOW_BALL);
		ItemMeta shopButtonMeta = shopButton.getItemMeta();
		
		shopButtonMeta.setDisplayName(ChatColor.GOLD + bold + ital + "Shop");
		shopButton.setItemMeta(shopButtonMeta);
		
		return shopButton;
	}
	
	@EventHandler
	public void onPlayerThrowHit(ProjectileHitEvent event){
		if(event.getEntity() instanceof Snowball){
			Snowball snowball = (Snowball) event.getEntity();
			createRandFW(snowball);
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
					spawnVillager(snowball);
				}
				},5L);
			
		}
	}
	
	@EventHandler
	public void onPlayerThrow(ProjectileLaunchEvent event){
		Player player = (Player) event.getEntity().getShooter();
		if(event.getEntity() instanceof Snowball && event.getEntity().getShooter() instanceof Player){
			PlayerInventory pInventory = player.getInventory();
			int slotIndex = pInventory.getHeldItemSlot();
			ItemStack itemInHand = player.getItemInHand();
			if(itemInHand.getType() == Material.SNOW_BALL){
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						pInventory.setItem(slotIndex, createShopSnowball());
					}
					}, 1L);
				
			}
		}
	}
	
	@EventHandler
	public void clickVillager(PlayerInteractEntityEvent event){
		Player player = (Player)event.getPlayer();
		
		if(event.getRightClicked().getType() != EntityType.VILLAGER)
			return;
		else if(((LivingEntity) event.getRightClicked()).getCustomName().equals(ChatColor.RED + "Villager Tier: 1")){
			event.setCancelled(true);
			openGUI(player);
		}
	}
	
	public void spawnVillager(Snowball snowball){
		Location villagerLocation = snowball.getLocation();
		int y = villagerLocation.getBlockY();
		villagerLocation.setY(y+1);
		
		@SuppressWarnings("deprecation")
		Villager villager = (Villager)snowball.getWorld().spawnCreature(villagerLocation, EntityType.VILLAGER);
		villager.setAdult();
		villager.setProfession(Profession.LIBRARIAN);
		villager.setCustomName(ChatColor.RED + "Villager Tier: 1");
	}
	public void createRandFW(Snowball snowball){
		
		Firework fw = (Firework)snowball.getWorld().spawnEntity(snowball.getLocation(), EntityType.FIREWORK);
		FireworkMeta fwm = fw.getFireworkMeta();
		
		Random r = new Random();
		int rt = r.nextInt(4)+1;
		int rc = r.nextInt(17)+1;
		int rcf = r.nextInt(17)+1;
		Type type = Type.BALL;
		
		switch(rt){
		case 1:
			type = Type.BALL;
			break;
		case 2:
			type = Type.BALL_LARGE;
			break;
		case 3:
			type = Type.BURST;
			break;
		case 4:
			type = Type.STAR;
			break;
		default:
			break;
		}
		
		
		Color color = getColor(rc);
		Color colorFade = getColor(rcf);
		
		FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(color).withFade(colorFade).with(type).trail(r.nextBoolean()).build();
		fwm.addEffect(effect);
		fwm.setPower(1);
		
		fw.setFireworkMeta(fwm);
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				fw.detonate();
			}
			}, 0L);
	}
	
	
	private Color getColor(int randomNumb){
		Color c = Color.BLUE;
		switch(randomNumb){
		case 1:
			c = Color.AQUA;
			break;
		case 2:
			c = Color.BLACK;
			break;
		case 3:
			c = Color.BLUE;
			break;
		case 4:
			c = Color.FUCHSIA;
			break;
		case 5:
			c = Color.GRAY;
			break;
		case 6:
			c = Color.GREEN;
			break;
		case 7:
			c = Color.LIME;
			break;
		case 8:
			c = Color.MAROON;
			break;
		case 9:
			c = Color.NAVY;
			break;
		case 10:
			c = Color.OLIVE;
			break;
		case 11:
			c = Color.ORANGE;
			break;
		case 12:
			c = Color.PURPLE;
			break;
		case 13:
			c = Color.RED;
			break;
		case 14:
			c = Color.SILVER;
			break;
		case 15:
			c = Color.TEAL;
			break;
		case 16:
			c = Color.WHITE;
			break;
		case 17:
			c = Color.YELLOW;
			break;
		default:
			break;
		}
		return c;
	}
	
}
