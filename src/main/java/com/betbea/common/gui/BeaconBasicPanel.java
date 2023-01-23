package com.betbea.common.gui;

import com.betbea.util.ColumnMaterial;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;

public class BeaconBasicPanel extends WGridPanel {
    private final ColumnMaterial material;

    public BeaconBasicPanel(int gridSize, ColumnMaterial material) {
        super(gridSize);
        this.material = material;
    }

    public ColumnMaterial getMaterial() {
        return material;
    }
}
