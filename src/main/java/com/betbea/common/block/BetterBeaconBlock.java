package com.betbea.common.block;

import com.betbea.ModRegistry;
import com.betbea.common.block.enity.BetterBeaconBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class BetterBeaconBlock extends BlockWithEntity {
    public BetterBeaconBlock() {
        super(FabricBlockSettings.of(Material.STONE).hardness(8f).requiresTool());
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        for (int x = -32; x <= 32; x += 1) {
            for (int z = -32; z <= 32; z += 1) {
                for (int y = -16; y <= 16; y += 1) {
                    if (world.getBlockState(pos.add(x, y, z)).getBlock() instanceof BetterBeaconBlock) {
                        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.of("You can't place beacon here because there is already a beacon nearby!"));
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModRegistry.BETTER_BEACON_BLOCK_ENTITY, (BetterBeaconBlockEntity::tick));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BetterBeaconBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        return ActionResult.PASS;
    }
}
