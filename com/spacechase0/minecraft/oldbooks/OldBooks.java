package com.spacechase0.minecraft.oldbooks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
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

@Mod( modid = "SC0_OldBooks", useMetadata = true, version = "1.0.0" )
@NetworkMod( clientSideRequired = false, serverSideRequired = false )
public class OldBooks
{ 
	public OldBooks()
	{
	}
	
	@Instance( "SC0_OldBooks" )
	public static OldBooks instance;
	
	@SidedProxy( clientSide = "com.spacechase0.minecraft.oldbooks.CommonProxy",
			     serverSide = "com.spacechase0.minecraft.oldbooks.CommonProxy" )
	public static CommonProxy proxy;
	
	@PreInit
	public void preInit( FMLPreInitializationEvent event )
	{
	}
	
	@Init
	public void init( FMLInitializationEvent event )
	{
		addRecipes();
	}
	
	@PostInit
	public void postInit( FMLPostInitializationEvent event )
	{
	}
	
	private void addRecipes()
	{
		GameRegistry.addRecipe( new ItemStack( Item.book ),
		                        new Object[]
		                        {
			                    	" # ",
			                    	" # ",
			                    	" # ",
			                    	'#', Item.paper
		                        } );
	}
}
