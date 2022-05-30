package net.usernaem.potsnstuff.core.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class Config {

    public static void register() {
        registerServerConfigs();
    }

    private static void registerServerConfigs() {
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        CraftConfig.registerServerConfig(SERVER_BUILDER);
        EffectConfig.registerServerConfig(SERVER_BUILDER);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_BUILDER.build());
    }
}
