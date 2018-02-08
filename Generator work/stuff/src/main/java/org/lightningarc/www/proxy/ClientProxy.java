package org.lightningarc.www.proxy;

import org.lightningarc.www.render.lightningBlockRender;
import org.lightningarc.www.render.lightningItemRender;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	
	public void preInit(FMLPreInitializationEvent preEvent){
		
		super.preInit(preEvent);
		
	}
	
	public void init(FMLInitializationEvent event){
		
		super.init(event);
		lightningBlockRender.registerBlockRender();
		lightningItemRender.registerItemRender();
		
	}

	public void postInit(FMLPostInitializationEvent postEvent){
	
		super.postInit(postEvent);
		
	}
	
}
