package com.spacechase0.minecraft.craftableenchantmentbooks;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod( modid = "SC0_CraftableEnchantmentBooks", useMetadata = true )
@NetworkMod( clientSideRequired = false, serverSideRequired = false )
public class CraftableEnchantedBooks
{ 
	public CraftableEnchantedBooks()
	{
	}
	
	@Instance( "SC0_CraftableEnchantmentBooks" )
	public static CraftableEnchantedBooks instance;
	
	@SidedProxy( clientSide = "com.spacechase0.minecraft.craftableenchantmentbooks.CommonProxy",
			     serverSide = "com.spacechase0.minecraft.craftableenchantmentbooks.CommonProxy" )
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit( FMLPreInitializationEvent event )
	{
		loadConfig( event );
	}
	
	@EventHandler
	public void init( FMLInitializationEvent event )
	{
		addRecipes();
	}
	
	@EventHandler
	public void postInit( FMLPostInitializationEvent event )
	{
		config.save();
	}
	
	private void addRecipes()
	{
		GameRegistry.addRecipe( new EnchantedBookRecipes() );
		
		EnchantmentData prot = new EnchantmentData( Enchantment.protection, 1 );
		EnchantmentData eff = new EnchantmentData( Enchantment.efficiency, 1 );
		EnchantmentData sharp = new EnchantmentData( Enchantment.sharpness, 1 );
		
		doEnchantment( Enchantment.protection, 3, Item.ingotIron, 4, Item.leather, 4, null );
		doEnchantment( Enchantment.fireProtection, 3, Item.magmaCream, 2, Item.blazeRod, 2, prot );
		doEnchantment( Enchantment.featherFalling, 3, Item.feather, 8, prot );
		doEnchantment( Enchantment.blastProtection, 3, Item.gunpowder, 8, prot );
		doEnchantment( Enchantment.projectileProtection, 3, Item.arrow, 8, prot );
		doEnchantment( Enchantment.respiration, 2, Item.netherStalkSeeds, 4, Item.reed, 4, null );
		doEnchantment( Enchantment.aquaAffinity, 1, Item.bucketWater, 8, eff );
		doEnchantment( Enchantment.thorns, 2, Item.ingotIron, 4, Item.itemsList[ Block.cactus.blockID ], 4, null );
		
		doEnchantment( Enchantment.sharpness, 4, Item.ingotIron, 6, Item.flint, 2, null );
		doEnchantment( Enchantment.smite, 4, Item.rottenFlesh, 4, Item.bone, 4, sharp );
		doEnchantment( Enchantment.baneOfArthropods, 4, Item.fermentedSpiderEye, 4, Item.silk, 4, sharp );
		doEnchantment( Enchantment.knockback, 1, Item.snowball, 5, Item.egg, 3, null );
		doEnchantment( Enchantment.fireAspect, 1, Item.blazePowder, 4, null );
		doEnchantment( Enchantment.looting, 2, Item.itemsList[ Block.blockLapis.blockID ], 4, null );
		
		doEnchantment( Enchantment.efficiency, 4, Item.itemsList[ Block.blockRedstone.blockID ], 4, null );
		doEnchantment( Enchantment.silkTouch, 1, Item.diamond, 2, Item.silk, 6, null );
		doEnchantment( Enchantment.fortune, 2, Item.itemsList[ Block.blockLapis.blockID ], 8, null );
		
		doEnchantment( Enchantment.power, 4, Item.ingotIron, 7, Item.flint, 1, null );
		doEnchantment( Enchantment.punch, 2, Item.snowball, 3, Item.egg, 5, null );
		doEnchantment( Enchantment.flame, 2, Item.magmaCream, 4, null );
		doEnchantment( Enchantment.infinity, 2, Item.diamond, 2, Item.eyeOfEnder, 6, null );
		
		doEnchantment( Enchantment.unbreaking, 2, Item.ingotIron, 8, null );
	}
	
	private void loadConfig( FMLPreInitializationEvent event )
	{
		config = new Configuration( event.getSuggestedConfigurationFile() );
		config.load();
	}
	
	private void doEnchantment( Enchantment ench, int level, Item mat, int amt, EnchantmentData start )
	{
		doEnchantment( ench, level, mat, amt, null, -1, start );
	}
	
	private void doEnchantment( Enchantment ench, int level, Item mat1, int amt1, Item mat2, int amt2, EnchantmentData start )
	{
		for ( int i = 1; i <= level; ++i )
		{
			ItemStack[] items = new ItemStack[ 8 ];
			int ii = 0;
			for ( ; ii < amt1; ++ii )
			{
				items[ ii ] = new ItemStack( mat1 );
			}
			for ( ; ii < amt1 + amt2; ++ii )
			{
				items[ ii ] = new ItemStack( mat2 );
			}
			EnchantmentData out = new EnchantmentData( ench, i );
			EnchantmentData in = ( i != 1 ) ? new EnchantmentData( ench, i - 1 ) : start;
			EnchantedBookRecipes.addEnchantmentRecipe( items, out, in );
		}
	}
	
	private Configuration config;
}
