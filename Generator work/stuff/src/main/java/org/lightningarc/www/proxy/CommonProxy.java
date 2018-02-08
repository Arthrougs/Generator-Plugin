package org.lightningarc.www.proxy;

import org.lightningarc.www.blocks.lightningBlocks;
import org.lightningarc.www.crafting.lightningRecipes;
import org.lightningarc.www.crafting.lightningSmelting;
import org.lightningarc.www.items.lightningItems;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent preEvent){
		lightningBlocks.initBlocks();
		lightningItems.initItems();
		
		lightningRecipes.initRecipes();
		lightningSmelting.initSmelting();
	}
	
	public void init(FMLInitializationEvent event){
		
	}

	public void postInit(FMLPostInitializationEvent postEvent){
	
	}
	
}
