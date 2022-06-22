package com.betbea.util;

import net.minecraft.util.StringIdentifiable;

import java.util.Locale;

public enum ColumnPart implements StringIdentifiable {
    BOTTOM(),
    MIDDLE(),
    TOP();

    @Override
    public String asString() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public boolean isTop() {
        return this == TOP;
    }

    public boolean isMiddle() {
        return this == MIDDLE;
    }

    public boolean isBottom() {
        return this == BOTTOM;
    }
}
