package com.betbea;

import com.betbea.common.gui.BetterBeaconBlockScreen;
import com.betbea.common.gui.BetterBeaconGuiDescription;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class ClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.<BetterBeaconGuiDescription, BetterBeaconBlockScreen>register(ModRegistry.BEACON_SCREEN_HANDLER_TYPE, (gui, inventory, title) -> new BetterBeaconBlockScreen(gui, inventory.player, title));
    }
}
