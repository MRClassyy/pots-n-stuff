package net.usernaem.potsnstuff.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CraftConfig {
	
    public static ForgeConfigSpec.BooleanValue CRAFT_VIAL;
    public static ForgeConfigSpec.BooleanValue CRAFT_MARBL;
    public static ForgeConfigSpec.BooleanValue CRAFT_TIPPED;
	
	 public static void registerServerConfig(ForgeConfigSpec.Builder SERVER_BUILDER) {
	        SERVER_BUILDER.comment("Settings for custom crafting recipes").push("craft");

	        CRAFT_VIAL = SERVER_BUILDER
	                .comment("Set if potions in vials can be crafted")
	                .define("potionVialCraft", true);
	        CRAFT_MARBL = SERVER_BUILDER
	                .comment("Set if potions in marbles can be crafted")
	                .define("potionMarblCraft", true);

	        SERVER_BUILDER.pop();
	    }
}
