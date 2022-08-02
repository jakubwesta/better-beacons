package com.betbea.common.block.enity;

import com.betbea.ModRegistry;
import com.betbea.util.CrystalQuality;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;

public class CrystalBlockEntity extends BlockEntity {
    private Optional<BetterBeaconBlockEntity> connectedBeacon = Optional.empty();

    public CrystalBlockEntity(CrystalQuality quality, BlockPos pos, BlockState state) {
        super(ModRegistry.getCrystalBlockEntity(quality), pos, state);
    }

    public void connectToBeacon(BetterBeaconBlockEntity entity) {
        connectedBeacon = Optional.ofNullable(entity);
    }

    public void disconnectBeacon() {
        connectedBeacon = Optional.empty();
    }

    public Optional<BetterBeaconBlockEntity> getConnectedBeacon() {
        return connectedBeacon;
    }
}
