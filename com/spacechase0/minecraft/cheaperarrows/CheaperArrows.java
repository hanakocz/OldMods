package com.spacechase0.minecraft.cheaperarrows;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
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

@Mod( modid = "SC0_CheaperArrows", useMetadata = true )
@NetworkMod( clientSideRequired = false, serverSideRequired = false )
public class CheaperArrows
{ 
	public CheaperArrows()
	{
	}
	
	@Instance( "SC0_CheaperArrows" )
	public static CheaperArrows instance;
	
	@SidedProxy( clientSide = "com.spacechase0.minecraft.cheaperarrows.CommonProxy",
			     serverSide = "com.spacechase0.minecraft.cheaperarrows.CommonProxy" )
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
		GameRegistry.addRecipe( new ItemStack( Item.arrow, 4 ),
		                        new Object[]
		                        {
			                    	" ^ ",
			                    	" | ",
			                    	" | ",
			                    	'^', Block.cobblestone,
			                    	'|', Block.planks,
		                        } );
	}
}
