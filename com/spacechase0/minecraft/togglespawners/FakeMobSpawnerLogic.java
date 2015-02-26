package com.spacechase0.minecraft.togglespawners;

import net.minecraft.block.Block;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.world.World;

public class FakeMobSpawnerLogic extends MobSpawnerBaseLogic
{
	FakeMobSpawnerLogic( ToggleableMobSpawnerTileEntity theTileEntity )
    {
        tileEntity = theTileEntity;
    }

    public void func_98267_a(int par1)
    {
        this.tileEntity.worldObj.addBlockEvent(this.tileEntity.xCoord, this.tileEntity.yCoord, this.tileEntity.zCoord, Block.mobSpawner.blockID, par1, 0);
    }

    public World getSpawnerWorld()
    {
        return this.tileEntity.worldObj;
    }

    public int getSpawnerX()
    {
        return this.tileEntity.xCoord;
    }

    public int getSpawnerY()
    {
        return this.tileEntity.yCoord;
    }

    public int getSpawnerZ()
    {
        return this.tileEntity.zCoord;
    }
	
	@Override
	public boolean canRun()
	{
		return ToggleSpawners.areSpawnersActive() && super.canRun();
	}
    
    public final ToggleableMobSpawnerTileEntity tileEntity;
}
