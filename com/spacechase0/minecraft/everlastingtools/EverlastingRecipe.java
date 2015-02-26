package com.spacechase0.minecraft.everlastingtools;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class EverlastingRecipe implements IRecipe
{
    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches( InventoryCrafting inv, World world )
    {
    	return ( doCheck( inv ) != null );
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult( InventoryCrafting inv )
    {
    	ItemStack result = doCheck( inv );
    	if ( result == null )
    	{
    		return null;
    	}
    	result = result.copy();
    	
    	result.addEnchantment( EverlastingEnchantment.ENCHANTMENT, 1 );
    	result.setRepairCost( 0xFFFF ); // 65535 - Even if the anvil is modified to allow repairing anything, NOBODY can get that. :P
    	
    	return result;
    }

    /**
     * Returns the size of the recipe area
     */
    public int getRecipeSize()
    {
        return 9;
    }

    public ItemStack getRecipeOutput()
    {
        return null;
    }
    
    private ItemStack doCheck( InventoryCrafting inv )
    {
        ItemStack tool = null;
        boolean lamp, cream, charge, eye, diamond, gold, emerald, iron;
        lamp = cream = charge = eye = diamond = gold = emerald = iron = false;

        for ( int i = 0; i < inv.getSizeInventory(); ++i )
        {
            ItemStack stack = inv.getStackInSlot( i );
            if ( stack == null )
            {
            	continue;
            }
            
            Item item = stack.getItem();
            if ( Enchantment.unbreaking.canApply( stack ) ) //item instanceof ItemArmor || item instanceof ItemSword || item instanceof ItemTool || item instanceof ItemBow )
            {
            	tool = stack;
            }
            else if ( stack.itemID == Block.redstoneLampIdle.blockID )
            {
            	lamp = true;
            }
            else if ( stack.itemID == Item.magmaCream.itemID )
            {
            	cream = true;
            }
            else if ( stack.itemID == Item.fireballCharge.itemID )
            {
            	charge = true;
            }
            else if ( stack.itemID == Item.eyeOfEnder.itemID )
            {
            	eye = true;
            }
            else if ( stack.itemID == Block.blockDiamond.blockID )
            {
            	diamond = true;
            }
            else if ( stack.itemID == Block.blockGold.blockID )
            {
            	gold = true;
            }
            else if ( stack.itemID == Block.blockEmerald.blockID )
            {
            	emerald = true;
            }
            else if ( stack.itemID == Block.blockIron.blockID )
            {
            	iron = true;
            }
        }
        
        if ( lamp && cream && charge && eye && diamond && gold && emerald && iron )
        {
        	return tool;
        }
        
        return null;
    }
}
