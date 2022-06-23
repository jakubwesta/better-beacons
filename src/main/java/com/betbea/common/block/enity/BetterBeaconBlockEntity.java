package com.betbea.common.block.enity;

import com.betbea.Mod;
import com.betbea.ModRegistry;
import com.betbea.common.block.ColumnBlock;
import com.betbea.common.block.CrystalBlock;
import com.betbea.common.gui.BetterBeaconGuiDescription;
import com.betbea.util.AllColumnsData;
import com.betbea.util.ColumnData;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class BetterBeaconBlockEntity extends BlockEntity implements NamedScreenHandlerFactory {
    public AllColumnsData columnsData = new AllColumnsData();

    public BetterBeaconBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.BETTER_BEACON_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, BetterBeaconBlockEntity entity) {
        // Detecting columns
        ArrayList<BlockPos> columnsPos = new ArrayList<>();
        for (int x = -1; x <= 1; x += 2 ) {
            for (int z = -1; z <= 1; z += 2) {
                columnsPos.add(pos.add(x*6, 0, z*2));
                columnsPos.add(pos.add(x*5, 0, z*5));
                columnsPos.add(pos.add(x*2, 0, z*6));
            }
        }
        int index = 0;
        for (BlockPos blockPos : columnsPos) {
            boolean isColumn = true;
            MutableText blockName = world.getBlockState(blockPos).getBlock().getName();
            for (int y = 0; y <= 2; y += 1) {
                BlockState blockState = world.getBlockState(blockPos.add(0, y, 0));
                if (!blockState.isIn(ModRegistry.COLUMN_BLOCKS) || !blockState.getBlock().getName().equals(blockName)) {
                    isColumn = false;
                }
            }
            if (!world.getBlockState(blockPos.add(0, 3, 0)).isIn(ModRegistry.CRYSTAL_BLOCKS)) {
                isColumn = false;
            }
            if (isColumn) {
                entity.columnsData.setColumn(index,
                        ((ColumnBlock) world.getBlockState(blockPos).getBlock()).getMaterial(),
                        ((CrystalBlock) world.getBlockState(blockPos.add(0, 3, 0)).getBlock()).getQuality());
            } else {
                entity.columnsData.setInvalidColumn(index);
            }
            index += 1;
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
    }

    @Override
    public Text getDisplayName() {
        return Text.empty();
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new BetterBeaconGuiDescription(syncId, inv, ScreenHandlerContext.create(world, pos));
    }
}
