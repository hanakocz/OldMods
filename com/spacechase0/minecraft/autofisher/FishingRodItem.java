package com.spacechase0.minecraft.autofisher;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FishingRodItem extends ItemFishingRod
{
	public FishingRodItem( int id )
	{
		super( id );
		setUnlocalizedName( "fishingRod" );
		setTextureName( "fishing_rod" );
	}
	
	@SideOnly( Side.CLIENT )
    public ItemStack onItemRightClick( ItemStack stack, World world, EntityPlayer player )
    {
		ItemStack ret = super.onItemRightClick( stack, world, player );
		
		if ( world.isRemote )
		{
			Minecraft mc = FMLClientHandler.instance().getClient();
			
			EntityFishHook hook = player.fishEntity;
			if ( player == mc.thePlayer && hook != null )
			{
				stagnated = false;
				prevMotionY = 0;
				timeSinceCasted = 0;
			}
		}
		
		return ret;
    }
	
	@SideOnly( Side.CLIENT )
	public void onUpdate( ItemStack stack, World world, Entity entity, int par4, boolean active )
	{
		super.onUpdate( stack, world, entity, par4, active );
		if ( !world.isRemote || !active )
		{
			return;
		}
		
		Minecraft mc = FMLClientHandler.instance().getClient();
		
		EntityPlayer player = ( EntityPlayer ) entity;
		if ( player != mc.thePlayer )
		{
			return;
		}
		
		EntityFishHook hook = player.fishEntity;
		if ( hook == null )
		{
			if ( castNextTick )
			{
				mc.playerController.sendUseItem( mc.thePlayer, mc.theWorld, stack );
				castNextTick = false;
			}
			return;
		}
		else if ( ++timeSinceCasted < 30 )
		{
			return;
		}
		
		if ( Math.abs( hook.motionX + hook.motionY + hook.motionZ ) < 0.05 && !stagnated )
		{
			stagnated = true;
			return;
		}
		else if ( !stagnated )
		{
			return;
		}
		
		double motion = hook.serverPosY - prevMotionY;
		if ( motion < -2 /*hook.motionY < -0.03 && prevMotionY > -0.02*/ && stagnated )
		{
			mc.playerController.sendUseItem( mc.thePlayer, mc.theWorld, stack );
			castNextTick = true;
		}
		prevMotionY = hook.serverPosY;
	}
	
	private boolean stagnated = false;
	private double prevMotionY = 0;
	private boolean castNextTick = false;
	private int timeSinceCasted = 0;
}
