package com.spacechase0.minecraft.togglespawners;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntityMobSpawner;

public class ToggleableMobSpawnerTileEntity extends TileEntityMobSpawner
{
	@Override
    public void updateEntity()
	{
		if ( ToggleSpawners.areSpawnersActive() )
		{
			super.updateEntity();
		}
	}

	@Override
    public MobSpawnerBaseLogic getSpawnerLogic()
    {
        return ( ToggleSpawners.areSpawnersActive() ? super.getSpawnerLogic() : fakeLogic );
    }
	
	@Override
    public void onDataPacket( INetworkManager net, Packet132TileEntityData packet )
	{
		readFromNBT( packet.data );
	}
	
	private MobSpawnerBaseLogic fakeLogic = new FakeMobSpawnerLogic( this );
}
