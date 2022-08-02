package com.betbea.util;

import com.betbea.Mod;
import com.betbea.common.block.enity.CrystalBlockEntity;
import com.betbea.config.ModConfigJsonObject;

import java.util.Optional;

public class ColumnData {
    private final ColumnMaterial columnMaterial;
    private final CrystalQuality crystalQuality;
    private final Optional<CrystalBlockEntity> crystalEntity;

    public ColumnData(ColumnMaterial material, CrystalQuality quality, CrystalBlockEntity crystalEntity) {
        this.columnMaterial = material;
        this.crystalQuality = quality;
        this.crystalEntity = Optional.of(crystalEntity);
    }

    public ColumnData() {
        this.columnMaterial = ColumnMaterial.INVALID;
        this.crystalQuality = CrystalQuality.INVALID;
        this.crystalEntity = Optional.empty();
    }

    public float getMaterialPower() {
        ModConfigJsonObject cfg = Mod.CONFIG;
        float crystalMultiplier = switch (crystalQuality) {
            case MATTE -> cfg.crystalStrengthMultipliers.matte;
            case CLEAR -> cfg.crystalStrengthMultipliers.clear;
            case EXQUISITE -> cfg.crystalStrengthMultipliers.exquisite;
            case INVALID -> 0f;
        };

        return cfg.baseMaterialPower * crystalMultiplier;
    }

    public ColumnMaterial getColumnMaterial() {
        return columnMaterial;
    }

    public CrystalQuality getCrystalQuality() {
        return crystalQuality;
    }

    public Optional<CrystalBlockEntity> getCrystalBlockEntity() {
        return crystalEntity;
    }
}
