package com.betbea.common.block;

import com.betbea.ModRegistry;
import com.betbea.util.CrystalQuality;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class CrystalBlock extends Block {
    private final CrystalQuality quality;

    public CrystalBlock(CrystalQuality quality) {
        super(FabricBlockSettings.of(Material.GLASS).hardness(2f));
        this.quality = quality;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.down());
        if (blockState.isIn(ModRegistry.COLUMN_BLOCKS)) {
            return blockState.get(ColumnBlock.COLUMN_PART).isTop();
        }
        return false;
    }

    public CrystalQuality getQuality() {
        return quality;
    }

    public String getId() {
        return quality.idPrefix + "_crystal";
    }
}
