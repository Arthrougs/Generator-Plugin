package org.lightningarc.www.items.tools;

import org.lightningarc.www.lightningGlobal;

import net.minecraft.item.ItemHoe;

public class lightningnickelHoe extends ItemHoe {

	public lightningnickelHoe(String unlocalizedName, ToolMaterial material) {
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(lightningGlobal.laCreativeTab);
	}

}
