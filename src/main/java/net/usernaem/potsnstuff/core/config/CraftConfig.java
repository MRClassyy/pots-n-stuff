package net.usernaem.potsnstuff.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CraftConfig {
	
    public static ForgeConfigSpec.BooleanValue CRAFT_VIAL;
    public static ForgeConfigSpec.BooleanValue CRAFT_MARBL;
    public static ForgeConfigSpec.BooleanValue CRAFT_TIPPED;
    public static ForgeConfigSpec.BooleanValue TIPPED_INFINITE;
    public static ForgeConfigSpec.IntValue TIPPED_COUNT;
    public static ForgeConfigSpec.BooleanValue TIPPED_HARM;
    public static ForgeConfigSpec.DoubleValue TIPPED_COOLDOWN;
	
	 public static void registerServerConfig(ForgeConfigSpec.Builder SERVER_BUILDER) {
	        SERVER_BUILDER.comment("Settings for custom crafting recipes").push("craft");

	        CRAFT_VIAL = SERVER_BUILDER
	                .comment("Set if potions in vials can be crafted")
	                .define("potionVialCraft", true);
	        CRAFT_MARBL = SERVER_BUILDER
	                .comment("Set if potions in marbles can be crafted")
	                .define("potionMarblCraft", true);
	        CRAFT_TIPPED = SERVER_BUILDER
	                .comment("Set if weapons can be tipped with potions")
	                .define("tippedWeaponCraft", true);
	        
	        SERVER_BUILDER.push("weapon");
	        
	        TIPPED_INFINITE = SERVER_BUILDER
	                .comment("Set if potion tipped weapons have an infinite amount of uses")
	                .define("tippedWeaponInfinite", true);

	        TIPPED_COUNT = SERVER_BUILDER
	                .comment("Sets how many charges a tipped weapon has")
	                .defineInRange("tippedWeaponCount", 16, 1, 128);

	        TIPPED_HARM = SERVER_BUILDER
	                .comment("Set if instant harmful potions can be applied to weapons")
	                .define("tippedWeaponHarm", true);

	        TIPPED_COOLDOWN = SERVER_BUILDER
	                .comment("Set attack cooldown time for when an attack can apply weapon effect again")
	                .defineInRange("tippedWeaponCooldown", 0.5f, 0, 1);
	        
	       SERVER_BUILDER.pop().pop();

	    }
}
