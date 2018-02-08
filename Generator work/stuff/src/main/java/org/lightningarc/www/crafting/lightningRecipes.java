package org.lightningarc.www.crafting;

import org.lightningarc.www.blocks.lightningBlocks;
import org.lightningarc.www.items.lightningItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class lightningRecipes {

	public static void initRecipes(){
		
		//Copper Block
		GameRegistry.addRecipe(new ItemStack(lightningBlocks.copperBlock),
				new Object[] {
					"###",
					"###",
					"###",
					'#', lightningItems.copperIngot
				});
		//Nickel Block
		GameRegistry.addRecipe(new ItemStack(lightningBlocks.nickelBlock),
				new Object[] {
					"###",
					"###",
					"###",
					'#', lightningItems.nickelIngot
				});
		//Ruby Block
		GameRegistry.addRecipe(new ItemStack(lightningBlocks.rubyBlock),
				new Object[] {
					"###",
					"###",
					"###",
					'#', lightningItems.rubyStone
				});
		//Salt Block
		GameRegistry.addRecipe(new ItemStack(lightningBlocks.saltBlock),
				new Object[] {
					"###",
					"###",
					"###",
					'#', lightningItems.saltDust
				});
		
		//Copper Tools
		GameRegistry.addRecipe(new ItemStack(lightningItems.copperPickaxe),
				new Object[]{
					"###",
					" S ",
					" S ",
					'#', lightningItems.copperIngot, 'S', Items.stick
			});
		
		GameRegistry.addRecipe(new ItemStack(lightningItems.copperAxe),
				new Object[]{
					"## ",
					"#S ",
					" S ",
					'#', lightningItems.copperIngot, 'S', Items.stick
			});
		
		GameRegistry.addRecipe(new ItemStack(lightningItems.copperSpade),
				new Object[]{
					" # ",
					" S ",
					" S ",
					'#', lightningItems.copperIngot, 'S', Items.stick
			});
		
		GameRegistry.addRecipe(new ItemStack(lightningItems.copperHoe),
				new Object[]{
					"## ",
					" S ",
					" S ",
					'#', lightningItems.copperIngot, 'S', Items.stick
			});
		
		GameRegistry.addRecipe(new ItemStack(lightningItems.copperSword),
				new Object[]{
					" # ",
					" # ",
					" S ",
					'#', lightningItems.copperIngot, 'S', Items.stick
			});
		
		//Nickel Tools
		GameRegistry.addRecipe(new ItemStack(lightningItems.nickelPickaxe),
				new Object[]{
					"###",
					" S ",
					" S ",
					'#', lightningItems.nickelIngot, 'S', Items.stick
			});
		GameRegistry.addRecipe(new ItemStack(lightningItems.nickelAxe),
				new Object[]{
					"## ",
					"#S ",
					" S ",
					'#', lightningItems.nickelIngot, 'S', Items.stick
			});
		GameRegistry.addRecipe(new ItemStack(lightningItems.nickelSpade),
				new Object[]{
					" # ",
					" S ",
					" S ",
					'#', lightningItems.nickelIngot, 'S', Items.stick
			});
		GameRegistry.addRecipe(new ItemStack(lightningItems.nickelHoe),
				new Object[]{
					"## ",
					" S ",
					" S ",
					'#', lightningItems.nickelIngot, 'S', Items.stick
			});
		GameRegistry.addRecipe(new ItemStack(lightningItems.nickelSword),
				new Object[]{
					" # ",
					" # ",
					" S ",
					'#', lightningItems.nickelIngot, 'S', Items.stick
			});
		
		
		
		//Shapless-----------------------------------------------------------------------------------------
		GameRegistry.addShapelessRecipe(new ItemStack(lightningItems.copperIngot, 9), 
				new Object [] {
					lightningBlocks.copperBlock
				});
		
		GameRegistry.addShapelessRecipe(new ItemStack(lightningItems.nickelIngot, 9), 
				new Object [] {
					lightningBlocks.nickelBlock
				});
		
		GameRegistry.addShapelessRecipe(new ItemStack(lightningItems.rubyStone, 9), 
				new Object [] {
					lightningBlocks.rubyBlock
				});
		
	}
	
}
