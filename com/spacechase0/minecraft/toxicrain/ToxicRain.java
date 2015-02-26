package com.spacechase0.minecraft.toxicrain;

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
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

// 1.0.2 - Fixed enchantment applying to non-armor.

@Mod( modid = "SC0_ToxicRain", useMetadata = true, dependencies="required-after:SC0_SpaceCore" )
@NetworkMod( clientSideRequired = false, serverSideRequired = false )
public class ToxicRain extends BaseMod
{
	public ToxicRain()
	{
		super( "toxicrain" );
	}
	
	@Instance( "SC0_ToxicRain" )
	public static ToxicRain instance;
	
	@SidedProxy( clientSide = "com.spacechase0.minecraft.toxicrain.CommonProxy",
			     serverSide = "com.spacechase0.minecraft.toxicrain.CommonProxy" )
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
		addTickHandlers();
	}
	
	@EventHandler
	public void postInit( FMLPostInitializationEvent event )
	{
		super.postInit( event );
	}
	
	private void addEnchantments()
	{
		UncorrodibleEnchantment.EFFECT_ID = getEnchantmentId( "uncorrodible", 0 );
	}
	
	private void addTickHandlers()
	{
		TickRegistry.registerTickHandler( new ToxicRainTickHandler(), Side.CLIENT );
		TickRegistry.registerTickHandler( new ToxicRainTickHandler(), Side.SERVER );
	}
	
	private int getEnchantmentId( String name, int num )
	{
		return config.get( "enchantment", name, DEFAULT_ENCHANTMENT_BASE + num ).getInt();
	}
	
	public Configuration config;
	private static final int DEFAULT_ENCHANTMENT_BASE = 200;
}
