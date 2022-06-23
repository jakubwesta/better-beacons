package com.betbea.common.block.enity;

import com.betbea.ModRegistry;
import com.betbea.util.CrystalQuality;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class CrystalBlockEntity extends BlockEntity {
    public boolean isConnected = false;

    public CrystalBlockEntity(CrystalQuality quality, BlockPos pos, BlockState state) {
        super(ModRegistry.getCrystalBlockEntity(quality), pos, state);
    }
}
