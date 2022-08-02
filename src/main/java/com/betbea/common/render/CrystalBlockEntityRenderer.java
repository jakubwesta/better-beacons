package com.betbea.common.render;

import com.betbea.common.block.enity.CrystalBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;

@Environment(EnvType.CLIENT)
public class CrystalBlockEntityRenderer implements BlockEntityRenderer<CrystalBlockEntity> {
    public CrystalBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {

    }

    @Override
    public void render(CrystalBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (entity.getConnectedBeacon().isPresent()) {
            matrices.push();
            MinecraftClient.getInstance().getItemRenderer().renderItem(new ItemStack(Items.BEACON), ModelTransformation.Mode.GUI, 111, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, 0);
            vertexConsumers.getBuffer(RenderLayer.getSolid()).color(255, 0, 0, 200);
            MinecraftClient.getInstance().getBlockRenderManager().renderBlock(Blocks.DIAMOND_BLOCK.getDefaultState(), entity.getPos().add(0, 3, 0), MinecraftClient.getInstance().world, matrices, vertexConsumers.getBuffer(RenderLayer.getBlockLayers().get(RenderLayer.getBlockLayers().size() - 1)).color(255, 0, 0, 200) , true, Random.create());
            matrices.pop();
        }
    }
}
