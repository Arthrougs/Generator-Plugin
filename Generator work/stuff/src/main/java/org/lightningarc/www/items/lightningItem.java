package org.lightningarc.www.items;

import org.lightningarc.www.lightningGlobal;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class lightningItem extends Item{
	
	public lightningItem(String name){
		super();
		
		this.setUnlocalizedName(name);
		this.setCreativeTab(lightningGlobal.laCreativeTab);
	}
}
