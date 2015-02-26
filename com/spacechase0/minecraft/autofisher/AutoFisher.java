package com.spacechase0.minecraft.autofisher;

import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod( modid = "SC0_AutoFisher", useMetadata = true )
public class AutoFisher
{
	public AutoFisher()
	{
	}
	
	@EventHandler
	public void init( FMLInitializationEvent event )
	{
		Item.itemsList[ Item.fishingRod.itemID ] = null;
		fishingRod = new FishingRodItem( Item.fishingRod.itemID - 256 );
		Item.fishingRod = fishingRod;
	}
	
	public static FishingRodItem fishingRod;
}
