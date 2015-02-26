package com.spacechase0.minecraft.toxicrain;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ToxicRainTickHandler implements ITickHandler
{
	@Override
	public void tickStart( EnumSet< TickType > type, Object... tickData )
	{
	}
	
	@Override
	public void tickEnd( EnumSet< TickType > type, Object... tickData )
	{
		EntityPlayer player = ( EntityPlayer ) tickData[ 0 ];
		if ( player.worldObj.isRemote )
		{
			return;
		}
		
		Long time = lastTickTimes.get( player );
		if ( time == null )
		{
			lastTickTimes.put( player, new Long( System.currentTimeMillis() ) );
			time = lastTickTimes.get( player );
		}
		
		Long curr = System.currentTimeMillis();
		if ( Math.abs( time - curr ) >= 1000 )
		{
			lastTickTimes.put( player, curr );
			
			int resist = 1;
			InventoryPlayer inv = player.inventory;
			for ( ItemStack stack : inv.armorInventory )
			{
				resist += checkItem( stack ) * 5;
			}
			
			if ( resist == 1 || rand.nextInt( resist ) == 0 )
			{
				doToxicRain( player );
			}
		}
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
	
	private int checkItem( ItemStack stack )
	{
		int lvl = EnchantmentHelper.getEnchantmentLevel( UncorrodibleEnchantment.EFFECT_ID, stack );
		return lvl;
	}
	
	private void doToxicRain( EntityPlayer player )
	{
		World world = player.worldObj;
		if ( world.isRemote )
		{
			return;
		}
		
		boolean isUnderSky = world.canLightningStrikeAt(MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY + (double)player.height), MathHelper.floor_double(player.posZ));
		
		if ( world.getWorldInfo().isRaining() && isUnderSky )
		{
			PotionEffect active = player.getActivePotionEffect( Potion.poison );
			if ( active == null )
			{
				active = new PotionEffect( Potion.poison.id, 60 );
			}
			else
			{
				active.duration += 45;
				/*
				if ( active.duration > 300 )
				{
					active = new PotionEffect( Potion.poison.id, active.duration / 5, active.getAmplifier() + 1 );
				}
				*/
			}
			player.addPotionEffect( active );
		}
	}
	
	private Random rand = new Random();
	private Map< EntityPlayer, Long > lastTickTimes = new HashMap< EntityPlayer, Long >();
}