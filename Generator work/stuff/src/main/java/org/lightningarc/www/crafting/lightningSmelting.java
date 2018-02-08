package org.lightningarc.www.crafting;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.lightningarc.www.blocks.lightningBlocks;
import org.lightningarc.www.items.lightningItems;

public class lightningSmelting {
	
	public static void initSmelting(){
		
		GameRegistry.addSmelting(lightningBlocks.copperOre, new ItemStack(lightningItems.copperIngot), 0);
		GameRegistry.addSmelting(lightningBlocks.nickelOre, new ItemStack(lightningItems.nickelIngot), 0);
		GameRegistry.addSmelting(lightningBlocks.saltBlock, new ItemStack(lightningBlocks.burntSaltBlock), 0);

	}
	
}
