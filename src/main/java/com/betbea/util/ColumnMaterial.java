package com.betbea.util;

import java.util.List;
import java.util.Locale;

public enum ColumnMaterial {
    INVALID("invalid"),
    NONE(""),
    COPPER("copper"),
    GOLD("gold"),
    EMERALD("emerald"),
    DIAMOND("diamond");

    public final String idPrefix;

    ColumnMaterial(String idPrefix) {
        this.idPrefix = idPrefix;
    }

    public String getName() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
