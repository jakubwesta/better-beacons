package com.betbea;

import com.betbea.common.block.BetterBeaconBlock;
import com.betbea.common.block.ColumnBlock;
import com.betbea.common.block.CrystalBlock;
import com.betbea.common.block.enity.BetterBeaconBlockEntity;
import com.betbea.common.block.enity.CrystalBlockEntity;
import com.betbea.common.gui.BetterBeaconGuiDescription;
import com.betbea.common.item.ClearAmethystShard;
import com.betbea.common.item.ExquisiteEnderShard;
import com.betbea.util.ColumnMaterial;
import com.betbea.util.CrystalQuality;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class ModRegistry {
    private static final HashMap<String, ColumnBlock> COLUMNS = new HashMap<>();
    private static final HashMap<String, CrystalBlock> CRYSTALS = new HashMap<>();
    private static final HashMap<String, BlockEntityType<CrystalBlockEntity>> CRYSTAL_BLOCK_ENTITIES = new HashMap<>();

    public static final ExquisiteEnderShard EXQUISITE_ENDER_SHARD = new ExquisiteEnderShard();
    public static final ClearAmethystShard CLEAR_AMETHYST_SHARD = new ClearAmethystShard();

    public static final BetterBeaconBlock BETTER_BEACON_BLOCK = new BetterBeaconBlock();
    public static BlockEntityType<BetterBeaconBlockEntity> BETTER_BEACON_BLOCK_ENTITY;
    public static ScreenHandlerType<BetterBeaconGuiDescription> BEACON_SCREEN_HANDLER_TYPE;

    public static final TagKey<Block> COLUMN_BLOCKS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Mod.MODID, "column_blocks"));
    public static final TagKey<Block> CRYSTAL_BLOCKS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Mod.MODID, "crystal_blocks"));
    public static final TagKey<Item> CRYSTAL_ITEMS = TagKey.of(Registry.ITEM_KEY, new Identifier(Mod.MODID, "crystal_items"));


    private static void registerBlock(String id, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(Mod.MODID, id), block);
        Registry.register(Registry.ITEM, new Identifier(Mod.MODID, id), new BlockItem(block, new Item.Settings().group(Mod.MOD_ITEM_GROUP)));
    }

    private static void registerItem(String id, Item item) {
        Registry.register(Registry.ITEM, new Identifier(Mod.MODID, id), item);
    }

    private static void registerEffect(String id, StatusEffect effect) {
        Registry.register(Registry.STATUS_EFFECT, new Identifier(Mod.MODID, id), effect);
    }

    public static void register() {
        for (ColumnMaterial material : ColumnMaterial.values()) {
            if (material == ColumnMaterial.INVALID) {
                continue;
            }
            ColumnBlock column = new ColumnBlock(material);
            COLUMNS.put(material.getName(), column);
            registerBlock(column.getId(), COLUMNS.get(material.getName()));
        }

        for (CrystalQuality quality : CrystalQuality.values()) {
            if (quality == CrystalQuality.INVALID) {
                continue;
            }
            CrystalBlock crystal = new CrystalBlock(quality);
            CRYSTALS.put(quality.getName(), crystal);
            registerBlock(crystal.getId(), CRYSTALS.get(quality.getName()));

            CRYSTAL_BLOCK_ENTITIES.put(quality.getName(), Registry.register(
                    Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(Mod.MODID, crystal.getBlockEntityId()),
                    FabricBlockEntityTypeBuilder.create((pos, state) -> new CrystalBlockEntity(quality, pos, state), getCrystal(quality)).build()
            ));
        }

        registerBlock("better_beacon", BETTER_BEACON_BLOCK);
        BETTER_BEACON_BLOCK_ENTITY = Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                new Identifier(Mod.MODID, "better_beacon_block_entity"),
                FabricBlockEntityTypeBuilder.create(BetterBeaconBlockEntity::new, BETTER_BEACON_BLOCK).build(null));
        BEACON_SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(new Identifier(Mod.MODID, "better_beacon"), ((syncId, inventory) -> new BetterBeaconGuiDescription(syncId, inventory, ScreenHandlerContext.EMPTY)));

        registerItem("exquisite_ender_shard", EXQUISITE_ENDER_SHARD);
        registerItem("clear_amethyst_shard", CLEAR_AMETHYST_SHARD);


    }

    public static ColumnBlock getColumn(ColumnMaterial material) {
        return COLUMNS.get(material.getName());
    }

    public static CrystalBlock getCrystal(CrystalQuality quality) {
        return CRYSTALS.get(quality.getName());
    }

    public static BlockEntityType<CrystalBlockEntity> getCrystalBlockEntity(CrystalQuality quality) {
        return CRYSTAL_BLOCK_ENTITIES.get(quality.getName());
    }
}
