package net.usernaem.potsnstuff.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class EffectConfig {
	
    public static ForgeConfigSpec.IntValue BLAST_RESIST;
    public static ForgeConfigSpec.IntValue ARROW_RESIST;
    public static ForgeConfigSpec.IntValue FALL_RESIST;
    public static ForgeConfigSpec.DoubleValue FRAIL_MULTIPLY;
    public static ForgeConfigSpec.IntValue CORROSION_VALUE;
    public static ForgeConfigSpec.IntValue DISARM_CHANCE;
	
	 public static void registerServerConfig(ForgeConfigSpec.Builder SERVER_BUILDER) {
	        SERVER_BUILDER.comment("Settings for the new effects").push("effects");

	        BLAST_RESIST = SERVER_BUILDER
	                .comment("Percent of blast damage reduction for blast immunity effect")
	                .defineInRange("blastResistance", 100, 0, 100);
	        ARROW_RESIST = SERVER_BUILDER
	                .comment("Percent of projectile damage reduction for projectile immunity effect")
	                .defineInRange("projectileResistance", 100, 0, 100);
	        FALL_RESIST = SERVER_BUILDER
	                .comment("Percent of fall damage reduction for lightfoot effect")
	                .defineInRange("fallResistance", 100, 0, 100);
	        FRAIL_MULTIPLY = SERVER_BUILDER
	                .comment("Frail effect damage multiplier")
	                .defineInRange("frailMultiply", 2.0f, 1.0f, 1000.0f);
	        CORROSION_VALUE = SERVER_BUILDER
	                .comment("Corrosion effect damage value")
	                .defineInRange("corrosionDamage", 10, 0, 1000);
	        DISARM_CHANCE = SERVER_BUILDER
	                .comment("Chance for affected entity to drop held item")
	                .defineInRange("disarmChance", 20, 0, 100);

	        SERVER_BUILDER.pop();
	    }
}
