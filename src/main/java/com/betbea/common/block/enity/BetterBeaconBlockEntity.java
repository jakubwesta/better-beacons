package com.betbea.common.block.enity;

import com.betbea.Mod;
import com.betbea.ModRegistry;
import com.betbea.common.block.ColumnBlock;
import com.betbea.common.block.CrystalBlock;
import com.betbea.common.gui.BetterBeaconGuiDescription;
import com.betbea.util.*;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
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
import java.util.HashMap;
import java.util.Map;

public class BetterBeaconBlockEntity extends BlockEntity implements NamedScreenHandlerFactory {
    public AllColumnsData columnsData = new AllColumnsData(this);
    public HashMap<ColumnMaterial, Integer> columnAmount = new HashMap<>();
    public HashMap<ColumnMaterial, Float> materialsPower = new HashMap<>();
    public HashMap<BeaconEffect, Boolean> effectReqsMet = new HashMap<>();

    public BetterBeaconBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.BETTER_BEACON_BLOCK_ENTITY, pos, state);
        for (ColumnMaterial material : ColumnMaterial.values()) {
            if (material != ColumnMaterial.INVALID && material != ColumnMaterial.NONE) {
                materialsPower.put(material, 0f);
                columnAmount.put(material, 0);
            }
        }
        for (BeaconEffect effect : BeaconEffect.values()) {
            effectReqsMet.put(effect, false);
        }
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
            Mod.sendDebugLog("index: " + index + " -----> " + "blockPos: " + blockPos);

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
                        ((CrystalBlock) world.getBlockState(blockPos.add(0, 3, 0)).getBlock()).getQuality(),
                        (CrystalBlockEntity) world.getBlockEntity(blockPos.add(0, 3, 0)));
            } else {
                entity.columnsData.setInvalidColumn(index);
            }
            index += 1;
        }

        // Updating materials power
        HashMap<ColumnMaterial, Float> newPowersMap = new HashMap<>();
        HashMap<ColumnMaterial, Integer> columnAmount = new HashMap<>();
        for (ColumnMaterial material : ColumnMaterial.values()) {
            newPowersMap.put(material, 0f);
            columnAmount.put(material, 0);
        }
        for (ColumnData column : entity.columnsData.getColumns()) {
            ModUtils.increaseMapInt(columnAmount, column.getColumnMaterial(), 1);
            ModUtils.increaseMapFloat(newPowersMap, column.getColumnMaterial(), column.getMaterialPower() * getAmountMultiplier(columnAmount.get(column.getColumnMaterial())));
        }
        entity.materialsPower = newPowersMap;
        System.out.println(newPowersMap);
        entity.columnAmount = columnAmount;
        System.out.println(columnAmount);

        // Updating met requirements for effects
        for (BeaconEffect effect : BeaconEffect.values()) {
            entity.effectReqsMet.replace(effect, entity.meetsRequirements(effect));
            Mod.sendDebugLog(effect.id + " -> " + entity.meetsRequirements(effect));
        }
    }

    private static float getAmountMultiplier(int index) {
        if (index <= 3) {
            return Mod.CONFIG.columnAmountPowerMultipliers.first3;
        } else if (index <= 6) {
            return Mod.CONFIG.columnAmountPowerMultipliers.second3;
        } else if (index <= 9) {
            return Mod.CONFIG.columnAmountPowerMultipliers.third3;
        } else if (index <= 12) {
            return Mod.CONFIG.columnAmountPowerMultipliers.fourth3;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean meetsRequirements(BeaconEffect effect) {
        boolean reqsMet = true;
        for (Map.Entry<ColumnMaterial, Float> entry : effect.requirements.entrySet()) {
            if (this.materialsPower.get(entry.getKey()) < entry.getValue()) {
                reqsMet = false;
            }
        }
        return reqsMet;
    }

    @Override
    public void markRemoved() {
        for (ColumnData column : columnsData.getColumns()) {
            if (column.getCrystalBlockEntity().isPresent()) {
                column.getCrystalBlockEntity().get().disconnectBeacon();
            }
        }
        super.markRemoved();
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
