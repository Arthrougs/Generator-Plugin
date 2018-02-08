package org.lightningarc.www;

import org.lightningarc.www.items.lightningItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class lightningCreativeTab extends CreativeTabs{

	public lightningCreativeTab(String string) {
		super(string);
		
	}
	
	public Item getTabIconItem(){
		return lightningItems.copperIngot;
	}
	
}
