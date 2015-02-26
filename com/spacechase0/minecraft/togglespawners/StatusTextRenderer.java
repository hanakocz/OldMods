package com.spacechase0.minecraft.togglespawners;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class StatusTextRenderer implements ITickHandler
{
	protected StatusTextRenderer()
	{
	}
	
	@Override
	public void tickStart( EnumSet< TickType > type, Object... tickData )
	{
	}

	@Override
	public void tickEnd( EnumSet< TickType > type, Object... tickData )
	{
		Minecraft mc = FMLClientHandler.instance().getClient();
		if ( mc.currentScreen == null && ToggleSpawners.shouldRenderText() )
		{
			GL11.glColor3f( 1.f, 1.f, 1.f );
			String str = "Spawners " + ( ToggleSpawners.areSpawnersActive() ? "Active" : "Inactive" );
			mc.fontRenderer.drawStringWithShadow( str, 5, 5, 0xFFFFFFFF );
		}
	}

	@Override
	public EnumSet< TickType > ticks()
	{
		return EnumSet.of( TickType.RENDER );
	}

	@Override
	public String getLabel()
	{
		return "SC0_ToggleSpawners";
	}
}
