package com.betbea.common.block;

import com.betbea.ModRegistry;
import com.betbea.common.block.enity.CrystalBlockEntity;
import com.betbea.util.CrystalQuality;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class CrystalBlock extends BlockWithEntity {
    private final CrystalQuality quality;

    public CrystalBlock(CrystalQuality quality) {
        super(FabricBlockSettings.of(Material.GLASS).hardness(-1f).requiresTool());
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

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        player.giveItemStack(new ItemStack(ModRegistry.getCrystal(getQuality())));
        world.setBlockState(pos, Blocks.AIR.getDefaultState());
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CrystalBlockEntity(getQuality(), pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public CrystalQuality getQuality() {
        return quality;
    }

    public String getId() {
        return quality.idPrefix + "_crystal";
    }

    public String getBlockEntityId() {
        return quality.idPrefix + "_crystal_block_entity";
    }
}
