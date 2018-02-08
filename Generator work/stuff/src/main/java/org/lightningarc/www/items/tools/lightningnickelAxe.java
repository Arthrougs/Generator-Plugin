package org.lightningarc.www.items.tools;

import org.lightningarc.www.lightningGlobal;

import net.minecraft.item.ItemAxe;

public class lightningnickelAxe extends ItemAxe {

	public lightningnickelAxe(String unlocalizedName, ToolMaterial material) {
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(lightningGlobal.laCreativeTab);
	}

}
