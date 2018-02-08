package org.lightningarc.www.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class lightningBlocks {

	//ORES
	public static Block copperOre;
	public static Block nickelOre;
	public static Block saltOre;
	public static Block test;
	
	//Gems
	public static Block rubyOre;
	public static Block sapphireOre;
	
	//Gem Blocks
	public static Block rubyBlock;
	public static Block sapphireBlock;
	
	//Metal Blocks
	public static Block copperBlock;
	public static Block nickelBlock;
	
	//Misc Blocks
	public static Block saltBlock;
	public static Block burntSaltBlock;
	
	public static void initBlocks() {
			
		//ORES
		GameRegistry.registerBlock(copperOre = new lightningBlock("copperOre", Material.rock, 3, 15), "copperOre");
		GameRegistry.registerBlock(nickelOre = new lightningBlock("nickelOre", Material.rock, 3, 15), "nickelOre");
		GameRegistry.registerBlock(saltOre = new lightningBlock("saltOre", Material.rock, 3, 15), "saltOre");
		GameRegistry.registerBlock(test = new lightningBlock("test", Material.rock, 3, 15), "test");

		
		//GEMS
		GameRegistry.registerBlock(rubyOre = new lightningBlock("rubyOre", Material.rock, 3, 15), "rubyOre");
		GameRegistry.registerBlock(sapphireOre = new lightningBlock("sapphireOre", Material.rock, 3, 15), "sapphireOre");

		//Gem Blocks
		GameRegistry.registerBlock(rubyBlock = new lightningBlock("rubyBlock", Material.rock, 3, 15), "rubyBlock");
		GameRegistry.registerBlock(sapphireBlock = new lightningBlock("sapphireBlock", Material.rock, 3, 15), "sapphireBlock");

		
		//Metal Blocks
		GameRegistry.registerBlock(copperBlock = new lightningBlock("copperBlock", Material.iron, 3, 15), "copperBlock");
		GameRegistry.registerBlock(nickelBlock = new lightningBlock("nickelBlock", Material.iron, 3, 15), "nickelBlock");
		
		//MISC
		GameRegistry.registerBlock(saltBlock = new lightningBlock("saltBlock", Material.sand, 1, 2), "saltBlock");
		GameRegistry.registerBlock(burntSaltBlock = new lightningBlock("burntSaltBlock", Material.rock, 3, 15), "burntSaltBlock");
	}
	
}
