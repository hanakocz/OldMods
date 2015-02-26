package com.spacechase0.minecraft.condensedpotions;

import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

// 1.0.4 - Updated for SpaceCore 0.6.0.

@Mod( modid = "SC0_CondensedPotions", useMetadata = true )
@NetworkMod( clientSideRequired = true, serverSideRequired = false )
public class CondensedPotions
{
	public CondensedPotions()
	{
	}
	
	@Instance( "SC0_CondensedPotions" )
	public static CondensedPotions instance;
	
	@SidedProxy( clientSide = "com.spacechase0.minecraft.condensedpotions.CommonProxy",
			     serverSide = "com.spacechase0.minecraft.condensedpotions.CommonProxy" )
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit( FMLPreInitializationEvent event )
	{
		loadConfig( event );
	}
	
	@EventHandler
	public void init( FMLInitializationEvent event )
	{
		addItems();
		addRecipes();
	}
	
	@EventHandler
	public void postInit( FMLPostInitializationEvent event )
	{
		config.save();
	}
	
	private void loadConfig( FMLPreInitializationEvent event )
	{
		config = new Configuration( event.getSuggestedConfigurationFile() );
		config.load();
	}
	
	private void addItems()
	{
		condensedNormalItem = new CondensedPotionItem( getItemId( "condensedPotionNormal", 0 ), false );
		condensedSplashItem = new CondensedPotionItem( getItemId( "condensedPotionSplash", 1 ), true );
	}
	
	private void addRecipes()
	{
		GameRegistry.addRecipe( new CondensedPotionRecipes() );
	}
	
	private int getItemId( String name, int num )
	{
		return config.getItem( name, DEFAULT_ITEM_BASE + num ).getInt();
	}
	
	// Items
	private Configuration config;
	public static Item condensedSplashItem = null;
	public static Item condensedNormalItem = null;
	private static final int DEFAULT_ITEM_BASE = 27750;
}
