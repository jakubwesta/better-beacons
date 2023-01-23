package com.betbea.config;

public class ModConfigJsonObject {
    public DropChances dropChances = new DropChances();
    public class DropChances {
        public float exquisiteEnderShard = 0.5f;
        public float clearAmethystShard = 0.1f;
    }

    public CrystalStrengthMultipliers crystalStrengthMultipliers = new CrystalStrengthMultipliers();
    public class CrystalStrengthMultipliers {
        public float matte = 1f;
        public float clear = 1.75f;
        public float exquisite = 2.5f;
    }

    public int baseMaterialPower = 1;

    public int baseBeaconRange = 80;

    public ColumnAmountPowerMultipliers columnAmountPowerMultipliers = new ColumnAmountPowerMultipliers();
    public class ColumnAmountPowerMultipliers {
        public float first3 = 1f;
        public float second3 = 0.75f;
        public float third3 = 0.5f;
        public float fourth3 = 0.25f;
    }

    public EffectsRequirements effectsRequirements = new EffectsRequirements();
    public class EffectsRequirements {
        public EffectRequirements haste = new EffectRequirements.EffectRequirementsBuilder().setCopperPower(1.5f).setGoldPower(1f).build();
    }
}
