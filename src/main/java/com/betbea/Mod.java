package com.betbea;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.BeaconBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mod implements ModInitializer {
	public static final String MODID = "betbea";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static final ItemGroup MOD_ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier(MODID, "general"),
			() -> new ItemStack(Blocks.BEACON)
	);

	@Override
	public void onInitialize() {
		sendLog("Initializing Better Beacons...");
		ModRegistry.register();
	}

	public static void sendLog(String message) {
		LOGGER.info(message);
	}
}
