package org.lightningarc.www.blocks;

import java.util.Random;

import org.lightningarc.www.lightningGlobal;
import org.lightningarc.www.items.lightningItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class lightningBlock extends Block{

	public lightningBlock(String unlocalizedName, Material material, float hardness, float resistance){
		super(material);
		
		
		this.setCreativeTab(lightningGlobal.laCreativeTab);
		this.setUnlocalizedName(unlocalizedName);
		this.setHardness(hardness);
		this.setResistance(resistance);
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune){
		return this == lightningBlocks.rubyOre ? lightningItems.rubyStone : (this == lightningBlocks.saltOre ? lightningItems.saltDust : (this == lightningBlocks.sapphireOre ? lightningItems.sapphireStone : Item.getItemFromBlock(this)));
	}
	
	

	
	
	public int quantityDropped(Random random){
		return this == lightningBlocks.rubyOre ? 1 + random.nextInt(3) : (this == lightningBlocks.saltOre ? 3 + random.nextInt(8) :  (this == lightningBlocks.sapphireOre ? 1 + random.nextInt(3) : 1));
	}
	
}
