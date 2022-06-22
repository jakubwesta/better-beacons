package com.betbea.common.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BetterBeaconBlockScreen extends CottonInventoryScreen<BetterBeaconGuiDescription> {
    public BetterBeaconBlockScreen(BetterBeaconGuiDescription description, PlayerEntity inventory, Text title) {
        super(description, inventory, title);
    }
}
