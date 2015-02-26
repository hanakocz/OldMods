package com.spacechase0.minecraft.togglespawners;

import java.lang.reflect.Field;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityMobSpawnerRenderer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly( Side.CLIENT )
@Mod( modid = "SC0_ToggleSpawners", useMetadata = true )
public class ToggleSpawners
{
	public ToggleSpawners()
	{
	}
	
	@EventHandler
	public void init( FMLInitializationEvent event )
	{
		Block.blocksList[ Block.mobSpawner.blockID ] = null;
		mobSpawner = new ToggleableMobSpawnerBlock( Block.mobSpawner.blockID );
		
		// Associate our tile entity with the vanilla one.
		if ( event.getSide().equals( Side.CLIENT ) ) registerTileEntityRenderer();
		try
		{
			Class c = TileEntity.class;
			Field nameToClassField = c.getDeclaredFields()[ 0 ];
			Field classToNameField = c.getDeclaredFields()[ 1 ];
			nameToClassField.setAccessible( true );
			classToNameField.setAccessible( true );
			Map nameToClassMap = ( Map ) nameToClassField.get( null );
			Map classToNameMap = ( Map ) classToNameField.get( null );
			nameToClassMap.put( "MobSpawner", ToggleableMobSpawnerTileEntity.class );
			classToNameMap.put( ToggleableMobSpawnerTileEntity.class, "MobSpawner" );
		}
		catch ( Exception exception )
		{
			exception.printStackTrace();
		}
		
		toggleKey = new KeyBinding( "Toggle Spawner Status", Keyboard.KEY_M );
		renderKey = new KeyBinding( "Toggle Render Status", Keyboard.KEY_N );
		keyHandler = new KeyHandler( new KeyBinding[] { toggleKey, renderKey } );
		statRenderer = new StatusTextRenderer();

		TickRegistry.registerTickHandler( keyHandler, Side.CLIENT );
		TickRegistry.registerTickHandler( statRenderer, Side.CLIENT );
	}
	
	private void registerTileEntityRenderer()
	{
		ClientRegistry.bindTileEntitySpecialRenderer( ToggleableMobSpawnerTileEntity.class, new TileEntityMobSpawnerRenderer() );
	}
	
	public static ToggleableMobSpawnerBlock mobSpawner;
	protected static KeyBinding toggleKey;
	protected static KeyBinding renderKey;
	private static KeyHandler keyHandler;
	private static StatusTextRenderer statRenderer;
	
	public static boolean areSpawnersActive()
	{
		return spawnersActive;
	}
	
	public static boolean shouldRenderText()
	{
		return shouldRender;
	}
	
	protected static boolean spawnersActive = true;
	protected static boolean shouldRender = true;
}
