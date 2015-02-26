package com.spacechase0.minecraft.togglespawners;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;

public class KeyHandler extends KeyBindingRegistry.KeyHandler
{
	protected KeyHandler( KeyBinding[] keys )
	{
		super( keys, getFalseArray( keys.length ) );
		
		for ( int i = 0; i < keys.length; ++i )
		{
			addKeyBindingToControls( keys[ i ] );
		}
	}

    public void keyDown( EnumSet< TickType > types, KeyBinding kb, boolean tickEnd, boolean isRepeat )
    {
    	if ( tickEnd )
    	{
    		return;
    	}
    	
    	if ( kb.keyDescription.equals( ToggleSpawners.toggleKey.keyDescription ) )
    	{
    		ToggleSpawners.spawnersActive = !ToggleSpawners.spawnersActive;
    	}
    	else if ( kb.keyDescription.equals( ToggleSpawners.renderKey.keyDescription ) )
    	{
    		ToggleSpawners.shouldRender = !ToggleSpawners.shouldRender;
    	}
    }
    
    public void keyUp( EnumSet< TickType > types, KeyBinding kb, boolean tickEnd )
    {
    }
    
    public EnumSet< TickType > ticks()
    {
    	return EnumSet.of( TickType.CLIENT );
    }
    
    public String getLabel()
    {
    	return "toggleSpawners";
    }
    
    private static boolean[] getFalseArray( int length )
    {
    	boolean[] array = new boolean[ length ];
    	
    	for ( int i = 0; i < length; ++i )
    	{
    		array[ i ] = false;
    	}
    	
    	return array;
    }
	
	private void addKeyBindingToControls( KeyBinding key )
	{
		Minecraft mc = FMLClientHandler.instance().getClient();
		GameSettings settings = mc.gameSettings;
		
		KeyBinding[] keys = new KeyBinding[ settings.keyBindings.length + 1 ];
		for ( int i = 0; i < settings.keyBindings.length; ++i )
		{
			keys[ i ] = settings.keyBindings[ i ];
		}
		keys[ keys.length - 1 ] = key;
		
		settings.keyBindings = keys;
	}
}