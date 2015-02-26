package com.spacechase0.minecraft.toxicrain;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class UncorrodibleEnchantment extends Enchantment
{
    protected UncorrodibleEnchantment()
    {
        super( EFFECT_ID, 8, EnumEnchantmentType.armor );
        this.setName( "uncorrodible" );
    }

    @Override
    public int getMinEnchantability( int level )
    {
        return 5 + ( ( level - 1 ) * 6 );
    }

    @Override
    public int getMaxEnchantability( int level )
    {
        return getMinEnchantability( level ) + 10;
    }

    @Override
    public int getMaxLevel()
    {
        return 3;
    }
    
    @Override
    public boolean canApply( ItemStack stack )
    {
    	return ( stack.getItem() instanceof ItemArmor );
    }
    
    protected static int EFFECT_ID = 200;
    public static final Enchantment ENCHANTMENT = new UncorrodibleEnchantment();
}
