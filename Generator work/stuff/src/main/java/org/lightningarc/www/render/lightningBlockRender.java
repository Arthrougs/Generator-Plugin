package org.lightningarc.www.render;


import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

import org.lightningarc.www.lightningGlobal;
import org.lightningarc.www.blocks.lightningBlocks;

public final class lightningBlockRender {
	
	public static void registerBlockRender(){
		
		//ORES
		regBlock(lightningBlocks.copperOre);
		regBlock(lightningBlocks.nickelOre);
		regBlock(lightningBlocks.saltOre);
		regBlock(lightningBlocks.test);
		
		//GEMS
		regBlock(lightningBlocks.rubyOre);
		regBlock(lightningBlocks.sapphireOre);
		
		//Gem Blocks
		regBlock(lightningBlocks.rubyBlock);
		regBlock(lightningBlocks.sapphireBlock);
		
		//Metal Blocks
		regBlock(lightningBlocks.copperBlock);
		regBlock(lightningBlocks.nickelBlock);
		
		//MISC
		regBlock(lightningBlocks.saltBlock);
		regBlock(lightningBlocks.burntSaltBlock);
	}
	
	public static void regBlock(Block block){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(lightningGlobal.MOD_ID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}
	
}
