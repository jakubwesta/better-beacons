package com.betbea.util;

public class ColumnData {
    private final ColumnMaterial columnMaterial;
    private final CrystalQuality crystalQuality;

    public ColumnData(ColumnMaterial material, CrystalQuality quality) {
        this.columnMaterial = material;
        this.crystalQuality = quality;
    }

    public ColumnData() {
        this.columnMaterial = ColumnMaterial.INVALID;
        this.crystalQuality = CrystalQuality.INVALID;
    }

    public ColumnMaterial getColumnMaterial() {
        return columnMaterial;
    }

    public CrystalQuality getCrystalQuality() {
        return crystalQuality;
    }
}
