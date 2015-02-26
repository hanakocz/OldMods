package com.spacechase0.minecraft.morepotiontypes;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;
import net.minecraftforge.common.Configuration;

import com.spacechase0.minecraft.spacecore.BaseMod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod( modid = "SC0_MorePotionTypes", useMetadata = true, dependencies="required-after:SC0_SpaceCore" )
@NetworkMod( clientSideRequired = true, serverSideRequired = false )
public class MorePotionTypes extends BaseMod
{
	public MorePotionTypes()
	{
		super( "morepotiontypes" );
	}
	
	@Instance( "SC0_MorePotionTypes" )
	public static MorePotionTypes instance;

	@SidedProxy( clientSide = "com.spacechase0.minecraft.morepotiontypes.CommonProxy",
			     serverSide = "com.spacechase0.minecraft.morepotiontypes.CommonProxy" )
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit( FMLPreInitializationEvent event )
	{
		super.preInit( event );
	}
	
	@EventHandler
	public void init( FMLInitializationEvent event )
	{
		super.init( event );
	}
	
	@EventHandler
	public void postInit( FMLPostInitializationEvent event )
	{
		super.postInit( event );
		
		if ( config.get( "restrictions", "allowExtendedTier2", true ).getBoolean( true ) )
		{
			Item.glowstone.setPotionEffect( "+5-7" ); // No -6
			Item.redstone.setPotionEffect( "+6-7" ); // No -5
		}
		
		// Remove in 1.7:
		// Water breathing, fish
		
		if ( config.get( "types", "haste", true ).getBoolean( true ) )
		{
			// Fermented spider eyes will turn this into an invis. potions
			Item.ingotGold.setPotionEffect( "+0+1+2-3+13&4-4" );
	        PotionHelper.potionRequirements.put( Integer.valueOf( Potion.digSpeed.getId() ), "0 & 1 & 2 & !3 & 2+6");
		}
		
		if ( config.get( "types", "jump", true ).getBoolean( true ) )
		{
			// Fermented spider eyes will turn this into an instant damage potion
			Item.slimeBall.setPotionEffect( "+0-1+2+3+13&4-4" );
	        PotionHelper.potionRequirements.put( Integer.valueOf( Potion.jump.getId() ), "0 & !1 & 2 & 3 & 2+6");
	        PotionHelper.potionAmplifiers.put( Integer.valueOf( Potion.jump.getId() ), "5" );
		}
		
		if ( config.get( "types", "resistance", true ).getBoolean( true ) )
		{
			// Fermented spider eyes will turn this into an .. potion
			Item.diamond.setPotionEffect( "+0+1+2+3+13&4-4" );
	        PotionHelper.potionRequirements.put( Integer.valueOf( Potion.resistance.getId() ), "0 & 1 & 2 & 3 & 2+6");
	        PotionHelper.potionAmplifiers.put( Integer.valueOf( Potion.resistance.getId() ), "5" );
		}
	}
	
	public static Configuration config;
}
