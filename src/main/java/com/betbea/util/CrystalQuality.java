package com.betbea.util;

import java.util.Locale;

public enum CrystalQuality {
    INVALID(),
    MATTE(),
    CLEAR(),
    EXQUISITE();

    public final String idPrefix;

    CrystalQuality() {
        this.idPrefix = getName();
    }

    public String getName() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
