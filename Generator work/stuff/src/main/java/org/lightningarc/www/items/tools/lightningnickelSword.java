package org.lightningarc.www.items.tools;

import org.lightningarc.www.lightningGlobal;

import net.minecraft.item.ItemSword;

public class lightningnickelSword extends ItemSword {

	public lightningnickelSword(String unlocalizedName, ToolMaterial material) {
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(lightningGlobal.laCreativeTab);
	}

}
