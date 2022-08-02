package com.betbea.util;

import java.util.HashMap;

public class ModUtils {
    public static <K> void increaseMapInt(HashMap<K, Integer> map, K keyToIncrease, Integer increaseAmount) {
        map.replace(keyToIncrease, map.get(keyToIncrease) + increaseAmount);
    }

    public static <K> void increaseMapFloat(HashMap<K, Float> map, K keyToIncrease, Float increaseAmount) {
        map.replace(keyToIncrease, map.get(keyToIncrease) + increaseAmount);
    }

    public static <K extends Enum<K>> HashMap<K, Integer> emptyIntMapFromEnum(Class<K> enumClass) {
        HashMap<K, Integer> map = new HashMap<>();
        for (K k : enumClass.getEnumConstants()) {
            map.put(k, 0);
        }
        return map;
    }

    public static <K extends Enum<K>> HashMap<K, Float> emptyFloatMapFromEnum(Class<K> enumClass) {
        HashMap<K, Float> map = new HashMap<>();
        for (K k : enumClass.getEnumConstants()) {
            map.put(k, 0f);
        }
        return map;
    }
}
