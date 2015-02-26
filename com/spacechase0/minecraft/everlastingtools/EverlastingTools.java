package com.spacechase0.minecraft.everlastingtools;

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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

// 1.0.3 - Fix enchantment ID from config file not being used.
// 1.0.2 - Allow working for any item with durability.

@Mod( modid = "SC0_EverlastingTools", useMetadata = true, dependencies="required-after:SC0_SpaceCore" )
@NetworkMod( clientSideRequired = false, serverSideRequired = false )
public class EverlastingTools extends BaseMod
{
	public EverlastingTools()
	{
		super( "everlastingtools" );
	}
	
	@Instance( "SC0_EverlastingTools" )
	public static EverlastingTools instance;
	
	@SidedProxy( clientSide = "com.spacechase0.minecraft.everlastingtools.CommonProxy",
			     serverSide = "com.spacechase0.minecraft.everlastingtools.CommonProxy" )
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
		
		addEnchantments();
		addRecipes();
		addTickHandlers();
	}
	
	@EventHandler
	public void postInit( FMLPostInitializationEvent event )
	{
		super.postInit( event );
	}
	
	private void addEnchantments()
	{
		EverlastingEnchantment.EFFECT_ID = getEnchantmentId( "everlasting", 0 );
		EverlastingEnchantment.ENCHANTMENT = new EverlastingEnchantment();
	}
	
	private void addRecipes()
	{
		GameRegistry.addRecipe( new EverlastingRecipe() );
	}
	
	private void addTickHandlers()
	{
		TickRegistry.registerTickHandler( new EverlastingTickHandler(), Side.CLIENT );
		TickRegistry.registerTickHandler( new EverlastingTickHandler(), Side.SERVER );
	}
	
	private void addNames()
	{
		LanguageRegistry.instance().addStringLocalization( "enchantment.everlasting", "Everlasting" );
	}
	
	private int getEnchantmentId( String name, int num )
	{
		return config.get( "enchantment", name, DEFAULT_ENCHANTMENT_BASE + num ).getInt();
	}
	
	public Configuration config;
	private static final int DEFAULT_ENCHANTMENT_BASE = 195;
}
