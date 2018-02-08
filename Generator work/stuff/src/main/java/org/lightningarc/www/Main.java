package org.lightningarc.www;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.lightningarc.www.proxy.CommonProxy;

@Mod(modid = lightningGlobal.MOD_ID, name = lightningGlobal.MOD_NAME, version = lightningGlobal.VERSION)

public class Main {
	
	@Instance(lightningGlobal.MOD_ID)
	public static Main instance; 
	
	@SidedProxy(clientSide = lightningGlobal.lightning_CLIENT_PROXY, serverSide = lightningGlobal.lightning_COMMON_PROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent){
		
		this.proxy.preInit(preEvent);
		
	}
	@EventHandler
	public void init(FMLInitializationEvent event){
		
		this.proxy.init(event);
		
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent postEvent){
	
		this.proxy.postInit(postEvent);
		
	}
}
