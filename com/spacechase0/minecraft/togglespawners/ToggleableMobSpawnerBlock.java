package com.spacechase0.minecraft.togglespawners;

import net.minecraft.block.BlockMobSpawner;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ToggleableMobSpawnerBlock extends BlockMobSpawner
{
	public ToggleableMobSpawnerBlock( int id )
	{
		super( id );
		
		// Copied from the line in Block
		setHardness( 5.0F );
		setStepSound( soundMetalFootstep );
		setUnlocalizedName( "mobSpawner" );
		setTextureName( "mob_spawner" );
		disableStats();
	}
	
	@Override
    public TileEntity createNewTileEntity(World par1World)
    {
        return new ToggleableMobSpawnerTileEntity();
    }
}
