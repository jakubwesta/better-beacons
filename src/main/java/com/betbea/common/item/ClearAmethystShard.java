package com.betbea.common.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public class ClearAmethystShard extends Item {
    public ClearAmethystShard() {
        super(new FabricItemSettings().rarity(Rarity.UNCOMMON));
    }
}
