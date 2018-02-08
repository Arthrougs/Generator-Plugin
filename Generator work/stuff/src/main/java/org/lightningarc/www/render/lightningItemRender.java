package org.lightningarc.www.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

import org.lightningarc.www.lightningGlobal;
import org.lightningarc.www.items.lightningItems;

public class lightningItemRender {
	
	public static void registerItemRender(){
		
		//Tools
		regItem(lightningItems.copperPickaxe);
		regItem(lightningItems.copperAxe);
		regItem(lightningItems.copperSpade);
		regItem(lightningItems.copperHoe);
		regItem(lightningItems.copperSword);
		regItem(lightningItems.nickelPickaxe);
		regItem(lightningItems.nickelAxe);
		regItem(lightningItems.nickelSpade);
		regItem(lightningItems.nickelHoe);
		regItem(lightningItems.nickelSword);
		
		//Ingots
		regItem(lightningItems.copperIngot);
		regItem(lightningItems.nickelIngot);
		
		//GEMS
		regItem(lightningItems.rubyStone);
		regItem(lightningItems.saltDust);
		regItem(lightningItems.sapphireStone);
	}
	
	public static void regItem(Item item){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(lightningGlobal.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
}
