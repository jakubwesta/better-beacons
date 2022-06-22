package com.betbea.common.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public class ExquisiteEnderShard extends Item {
    public ExquisiteEnderShard() {
        super(new FabricItemSettings().fireproof().rarity(Rarity.RARE).maxCount(16));
    }
}
