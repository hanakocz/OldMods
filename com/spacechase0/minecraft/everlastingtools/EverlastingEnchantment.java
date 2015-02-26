package com.spacechase0.minecraft.everlastingtools;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EverlastingEnchantment extends Enchantment
{
    protected EverlastingEnchantment()
    {
        super( EFFECT_ID, 0, EnumEnchantmentType.all );
        this.setName( "everlasting" );
    }

    public int getMinEnchantability(int par1)
    {
        return 999999;
    }

    public int getMaxEnchantability( int par1 )
    {
        return super.getMinEnchantability( par1 ) + 1;
    }

    public int getMaxLevel()
    {
        return 1;
    }
    
    protected static int EFFECT_ID = 195;
    public static Enchantment ENCHANTMENT;
}
