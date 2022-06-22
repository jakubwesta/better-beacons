package com.betbea.common.gui;

import com.betbea.Mod;
import com.betbea.ModRegistry;
import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BetterBeaconGuiDescription extends SyncedGuiDescription {
    private static final int INVENTORY_SIZE = 1;

    public BetterBeaconGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ModRegistry.BEACON_SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context));

        WTabPanel tabsPanel = new WTabPanel();
        setRootPanel(tabsPanel);

        WGridPanel effectsPanel = new WGridPanel(1);
        effectsPanel.setSize(400, 240);
        effectsPanel.setInsets(Insets.NONE);

        WSprite background = new WSprite(new Identifier(Mod.MODID, "textures/gui/container/better_beacon_effects.png"));
        effectsPanel.add(background, 0, 0, 400, 240);

        tabsPanel.add(effectsPanel, tab -> tab.icon(new ItemIcon(Items.BEACON)));
        tabsPanel.add(effectsPanel, tab -> tab.title(Text.of("Beacon main")));
        tabsPanel.add(effectsPanel, tab -> tab.title(Text.of("Test")).icon(new ItemIcon(Items.DIAMOND)));
        tabsPanel.add(effectsPanel, tab -> tab.icon(new ItemIcon(Items.ACACIA_SAPLING)));
        tabsPanel.validate(this);
    }

}