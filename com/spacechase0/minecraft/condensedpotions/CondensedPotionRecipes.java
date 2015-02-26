package com.spacechase0.minecraft.condensedpotions;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class CondensedPotionRecipes implements IRecipe
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
    	ItemStack item = doCheck( inv );
    	if ( item == null )
    	{
    		return null;
    	}
    	
    	int id = ItemPotion.isSplash( item.getItemDamage() ) ? CondensedPotions.condensedSplashItem.itemID : CondensedPotions.condensedNormalItem.itemID;
    	ItemStack pot = new ItemStack( id, 1, 1 );
    	
    	NBTTagCompound potTag = new NBTTagCompound();
    	item.writeToNBT( potTag );
    	
    	NBTTagCompound comp = new NBTTagCompound();
    	comp.setTag( "Potion", potTag );
    	pot.setTagCompound( comp );
    	
    	return pot;
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
    	ItemStack item = null;
    	int count = 0;
    	
        for ( int i = 0; i < inv.getSizeInventory(); ++i, ++count )
        {
            ItemStack stack = inv.getStackInSlot( i );
            if ( stack == null )
            {
            	return null;
            }

            if ( item == null )
            {
            	item = stack;
            }
            else if ( stack.itemID != item.itemID || stack.getItemDamage() != item.getItemDamage() || !check( stack.getTagCompound(), item.getTagCompound() ) )
            {
            	return null;
            }
        }
        
        return ( count >= 9 && ( item.getItem() instanceof ItemPotion ) ) ? item : null;
    }
    
    private boolean check( NBTTagCompound a, NBTTagCompound b )
    {
    	boolean nullA = ( a == null );
    	boolean nullB = ( b == null );
    	if ( nullA != nullB )
    	{
    		return false;
    	}
    	
    	return ( a == null ) ? true : a.equals( b );
    }
}
