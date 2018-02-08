package org.lightningarc.www.items;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.lightningarc.www.items.tools.lightningCopperAxe;
import org.lightningarc.www.items.tools.lightningCopperHoe;
import org.lightningarc.www.items.tools.lightningCopperPickaxe;
import org.lightningarc.www.items.tools.lightningCopperSpade;
import org.lightningarc.www.items.tools.lightningCopperSword;
import org.lightningarc.www.items.tools.lightningnickelAxe;
import org.lightningarc.www.items.tools.lightningnickelHoe;
import org.lightningarc.www.items.tools.lightningnickelPickaxe;
import org.lightningarc.www.items.tools.lightningnickelSpade;
import org.lightningarc.www.items.tools.lightningnickelSword;

public class lightningItems {

	//Tools
	public static ToolMaterial COPPERTOOLS = EnumHelper.addToolMaterial("COPPERTOOLS", 2, 225, 6.0F, 2.0f, 14);
	public static ToolMaterial NICKELTOOLS = EnumHelper.addToolMaterial("NICKELTOOLS", 3, 250, 6.0F, 3.0f, 15);
	
	public static Item copperPickaxe;
	public static Item copperAxe;
	public static Item copperSpade;
	public static Item copperHoe;
	public static Item copperSword;
	
	
	
	public static Item nickelPickaxe;
	public static Item nickelAxe;
	public static Item nickelSpade;
	public static Item nickelHoe;
	public static Item nickelSword;
	
	
	//Ingots
	public static Item copperIngot;
	public static Item nickelIngot;
	
	//Gems
	public static Item rubyStone;
	public static Item saltDust;
	public static Item sapphireStone;
	
	public static void initItems(){
		
		//Tools
		//Copper
		GameRegistry.registerItem(copperPickaxe = new lightningCopperPickaxe("copperPickaxe", COPPERTOOLS), "copperPickaxe");
		GameRegistry.registerItem(copperAxe = new lightningCopperAxe("copperAxe", COPPERTOOLS), "copperAxe");
		GameRegistry.registerItem(copperSpade = new lightningCopperSpade("copperSpade", COPPERTOOLS), "copperSpade");
		GameRegistry.registerItem(copperHoe = new lightningCopperHoe("copperHoe", COPPERTOOLS), "copperHoe");
		GameRegistry.registerItem(copperSword = new lightningCopperSword("copperSword", COPPERTOOLS), "copperSword");
		//Nickel
		GameRegistry.registerItem(nickelPickaxe = new lightningnickelPickaxe("nickelPickaxe", NICKELTOOLS), "nickelPickaxe");
		GameRegistry.registerItem(nickelAxe = new lightningnickelAxe("nickelAxe", NICKELTOOLS), "nickelAxe");
		GameRegistry.registerItem(nickelSpade = new lightningnickelSpade("nickelSpade", NICKELTOOLS), "nickelSpade");
		GameRegistry.registerItem(nickelHoe = new lightningnickelHoe("nickelHoe", NICKELTOOLS), "nickelHoe");
		GameRegistry.registerItem(nickelSword = new lightningnickelSword("nickelSword", NICKELTOOLS), "nickelSword");

		
		//Ingots
		GameRegistry.registerItem(copperIngot = new lightningItem("copperIngot"), "copperIngot");
		GameRegistry.registerItem(nickelIngot = new lightningItem("nickelIngot"), "nickelIngot");

		//GEMS
		GameRegistry.registerItem(rubyStone = new lightningItem("rubyStone"), "rubyStone");
		GameRegistry.registerItem(sapphireStone = new lightningItem("sapphireStone"), "sapphireStone");
		GameRegistry.registerItem(saltDust = new lightningItem("saltDust"), "saltDust");

		
	}
}
