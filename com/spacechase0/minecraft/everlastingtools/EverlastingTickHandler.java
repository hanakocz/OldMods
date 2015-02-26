package com.spacechase0.minecraft.everlastingtools;

import java.util.EnumSet;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class EverlastingTickHandler implements ITickHandler
{
	
	@Override
	public void tickStart( EnumSet< TickType > type, Object... tickData )
	{
		EntityPlayer player = ( EntityPlayer ) tickData[ 0 ];
		InventoryPlayer inv = player.inventory;
		
		for ( ItemStack stack : inv.armorInventory )
		{
			checkItem( stack );
		}
		for ( ItemStack stack : inv.mainInventory )
		{
			checkItem( stack );
		}
	}
	
	@Override
	public void tickEnd( EnumSet< TickType > type, Object... tickData )
	{
	}
	
	@Override
	public EnumSet< TickType > ticks()
	{
		return EnumSet.of( TickType.PLAYER );
	}
	
	@Override
	public String getLabel()
	{
		return null;
	}
	
	private void checkItem( ItemStack stack )
	{
		int lvl = EnchantmentHelper.getEnchantmentLevel( EverlastingEnchantment.EFFECT_ID, stack );
		if ( lvl <= 0 )
		{
			return;
		}
		
		Item item = stack.getItem();
		if ( item == null )
		{
			return;
		}
		
		if ( stack.getItemDamage() != 0 )
		{
			stack.setItemDamage( 0 );
		}
	}
}