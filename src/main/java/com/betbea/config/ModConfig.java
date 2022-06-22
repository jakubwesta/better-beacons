package com.betbea.config;

import com.betbea.Mod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Path;

public class ModConfig {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private static final Path configPath = FabricLoader.getInstance().getConfigDir();
    private static final Path configFilePath = configPath.resolve("betbea.json");

    public static void initializeConfig() {
        try {
            File configFile = new File(String.valueOf(configFilePath));
            if (!configFile.exists()) {
                ModConfigJsonObject jsonClassNew = new ModConfigJsonObject();
                Writer writer = new FileWriter(configFilePath.toFile());
                gson.toJson(jsonClassNew, writer);
                writer.flush();
                writer.close();
            }
            Reader reader = new FileReader(configFile);
            Mod.CONFIG = gson.fromJson(reader, ModConfigJsonObject.class);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
