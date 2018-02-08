package org.lightningarc.www.items.tools;

import org.lightningarc.www.lightningGlobal;

import net.minecraft.item.ItemPickaxe;

public class lightningCopperPickaxe extends ItemPickaxe {
	
	public lightningCopperPickaxe(String unlocalizedName, ToolMaterial material){
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(lightningGlobal.laCreativeTab);
	}

}
