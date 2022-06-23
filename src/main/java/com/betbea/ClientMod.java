package com.betbea;

import com.betbea.common.gui.BetterBeaconBlockScreen;
import com.betbea.common.gui.BetterBeaconGuiDescription;
import com.betbea.common.render.BetterBeaconBlockEntityRenderer;
import com.betbea.common.render.CrystalBlockEntityRenderer;
import com.betbea.util.CrystalQuality;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;

public class ClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.<BetterBeaconGuiDescription, BetterBeaconBlockScreen>register(ModRegistry.BEACON_SCREEN_HANDLER_TYPE, (gui, inventory, title) -> new BetterBeaconBlockScreen(gui, inventory.player, title));
        BlockEntityRendererRegistry.register(ModRegistry.BETTER_BEACON_BLOCK_ENTITY, BetterBeaconBlockEntityRenderer::new);
        for (CrystalQuality quality : CrystalQuality.values()) {
            if (quality == CrystalQuality.INVALID) {
                continue;
            }
            BlockEntityRendererRegistry.register(ModRegistry.getCrystalBlockEntity(quality), CrystalBlockEntityRenderer::new);
        }
    }
}
