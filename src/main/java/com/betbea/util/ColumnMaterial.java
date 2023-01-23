package com.betbea.util;

import java.util.Locale;

public enum ColumnMaterial {
    INVALID("invalid", 0),
    NONE("", 0),
    COPPER("copper", 0xFFC97537),
    GOLD("gold", 0xFFFEC446),
    EMERALD("emerald", 0xFF59B32D),
    DIAMOND("diamond", 0xFF0A99D5);

    public final String idPrefix;
    public final int panelColor;

    ColumnMaterial(String idPrefix, int color) {
        this.idPrefix = idPrefix;
        this.panelColor = color;
    }

    public int getPanelColor() {
        return panelColor;
    }

    public String getName() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
