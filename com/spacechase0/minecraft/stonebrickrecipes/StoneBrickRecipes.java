package com.spacechase0.minecraft.stonebrickrecipes;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod( modid = "SC0_StoneBrickRecipes", useMetadata = true )
@NetworkMod( clientSideRequired = false, serverSideRequired = false )
public class StoneBrickRecipes
{
	public StoneBrickRecipes()
	{
	}
	
	@Instance( "SC0_StoneBrickRecipes" )
	public static StoneBrickRecipes instance;
	
	@SidedProxy( clientSide = "com.spacechase0.minecraft.stonebrickrecipes.CommonProxy",
			     serverSide = "com.spacechase0.minecraft.stonebrickrecipes.CommonProxy" )
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit( FMLPreInitializationEvent event )
	{
	}
	
	@EventHandler
	public void init( FMLInitializationEvent event )
	{
		addRecipes();
	}
	
	@EventHandler
	public void postInit( FMLPostInitializationEvent event )
	{
	}
	
	private void addRecipes()
	{
		GameRegistry.addRecipe( new ItemStack( Block.stoneBrick, 4, 2 ),
		                        new Object[]
		                        {
			                    	"##",
			                    	"##",
			                    	'#', Block.cobblestone,
		                        } );
		
		GameRegistry.addRecipe( new ItemStack( Block.stoneBrick, 4, 1 ),
                                new Object[]
                                        {
                                        	"##",
                                        	"##",
                                        	'#', Block.cobblestoneMossy,
                                        } );
	}
}
