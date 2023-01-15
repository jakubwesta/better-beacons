package com.betbea.config;

import com.betbea.util.ColumnMaterial;

import java.util.HashMap;

public class EffectRequirements {
    public float copperPower;
    public float goldPower;
    public float diamondPower;
    public float emeraldPower;

    public EffectRequirements(float copper, float gold, float diamond, float emerald) {
        this.copperPower = copper;
        this.goldPower = gold;
        this.diamondPower = diamond;
        this.emeraldPower = emerald;
    }

    public HashMap<ColumnMaterial, Float> getRequirements() {
        HashMap<ColumnMaterial, Float> reqs = new HashMap<>();
        reqs.put(ColumnMaterial.COPPER, this.copperPower);
        reqs.put(ColumnMaterial.GOLD, this.goldPower);
        reqs.put(ColumnMaterial.DIAMOND, this.diamondPower);
        reqs.put(ColumnMaterial.EMERALD, this.emeraldPower);
        return reqs;
    }

    public static class EffectRequirementsBuilder {
        private float copperPower = 0f;
        private float goldPower = 0f;
        private float diamondPower = 0f;
        private float emeraldPower = 0f;

        EffectRequirementsBuilder setCopperPower(float copperPower) {
            this.copperPower = copperPower;
            return this;
        }

        EffectRequirementsBuilder setGoldPower(float goldPower) {
            this.goldPower = goldPower;
            return this;
        }

        EffectRequirementsBuilder setDiamondPower(float diamondPower) {
            this.diamondPower = diamondPower;
            return this;
        }

        EffectRequirementsBuilder setEmeraldPower(float emeraldPower) {
            this.emeraldPower = emeraldPower;
            return this;
        }

        EffectRequirements build() {
            return new EffectRequirements(copperPower, goldPower, diamondPower, emeraldPower);
        }
    }
}
