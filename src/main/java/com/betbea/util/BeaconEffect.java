package com.betbea.util;

import com.betbea.Mod;

import java.util.HashMap;

public enum BeaconEffect {
    HASTE("haste", Mod.CONFIG.effectsRequirements.haste.getRequirements());

    public final String id;
    public final HashMap<ColumnMaterial, Float> requirements;

    BeaconEffect(String id, HashMap<ColumnMaterial, Float> reqs) {
        this.id = id;
        this.requirements = reqs;
    }
}
