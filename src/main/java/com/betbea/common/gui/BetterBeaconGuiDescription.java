package com.betbea.common.gui;

import com.betbea.ModRegistry;
import com.betbea.util.ColumnMaterial;
import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WTabPanel;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.Identifier;

public class BetterBeaconGuiDescription extends SyncedGuiDescription {
    private static final int INVENTORY_SIZE = 1;
    private static final int PANEL_X_SIZE = 300;
    private static final int PANEL_Y_SIZE = 240;
    private static final int BUTTON_SIZE = 20;
    private static final float PANEL_CONTRAST = 0.35f;

    public BetterBeaconGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ModRegistry.BEACON_SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context));

        WTabPanel tabsPanel = new WTabPanel();
        setRootPanel(tabsPanel);
        //tabsPanel.setBackgroundPainter(BackgroundPainter.createColorful(0xFF333333));

        tabsPanel.add(getCopperPanel(), tab -> tab.icon(new ItemIcon(Items.COPPER_INGOT)));
        tabsPanel.add(getGoldPanel(), tab -> tab.icon(new ItemIcon(Items.GOLD_INGOT)));
        tabsPanel.add(getDiamondPanel(), tab -> tab.icon(new ItemIcon(Items.DIAMOND)));
        tabsPanel.add(getEmeraldPanel(), tab -> tab.icon(new ItemIcon(Items.EMERALD)));
        tabsPanel.validate(this);
    }

    private WGridPanel getCopperPanel() {
        WGridPanel panel = getBasicPanel(ColumnMaterial.COPPER);

        panel.add(new EffectButton(new Identifier("minecraft" ,"textures/mob_effect/haste.png"), "Haste boost lmao"), 20, 20, BUTTON_SIZE, BUTTON_SIZE);

        return panel;
    }

    private WGridPanel getGoldPanel() {
        WGridPanel panel = getBasicPanel(ColumnMaterial.GOLD);

        return panel;
    }

    private WGridPanel getDiamondPanel() {
        WGridPanel panel = getBasicPanel(ColumnMaterial.DIAMOND);
        return panel;
    }

    private WGridPanel getEmeraldPanel() {
        WGridPanel panel = getBasicPanel(ColumnMaterial.EMERALD);
        return panel;
    }

    private WGridPanel getBasicPanel(ColumnMaterial panelType) {
        WGridPanel panel = new BeaconBasicPanel(1, panelType);
        panel.setSize(PANEL_X_SIZE, PANEL_Y_SIZE);
        panel.setInsets(Insets.NONE);
        panel.setBackgroundPainter(BackgroundPainter.createColorful(panelType.getPanelColor(), PANEL_CONTRAST));
        return panel;
    }
}