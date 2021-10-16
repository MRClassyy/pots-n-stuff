
package net.usernaem.potsnstuff.core.config;


import java.io.File;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;

//@EventBusSubscriber(modid = PotsNStuff.MOD_ID_STRING, bus = EventBusSubscriber.Bus.MOD)
public class PnsConfig {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	
	
	public static final ForgeConfigSpec.ConfigValue<Boolean> fuze_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Integer> fuze_normal_time;
	public static final ForgeConfigSpec.ConfigValue<Integer> fuze_normal_effect;
	public static final ForgeConfigSpec.ConfigValue<Integer> fuze_strong_effect;
	public static final ForgeConfigSpec.ConfigValue<Boolean> grounding_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Integer> grounding_normal_time;
	public static final ForgeConfigSpec.ConfigValue<Integer> grounding_long_time;
	public static final ForgeConfigSpec.ConfigValue<Boolean> recall_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Integer> recall_normal_time;
	public static final ForgeConfigSpec.ConfigValue<Integer> recall_long_time;
	public static final ForgeConfigSpec.ConfigValue<Boolean> photosynthesis_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Integer> photosynthesis_normal_time;
	public static final ForgeConfigSpec.ConfigValue<Integer> photosynthesis_long_time;
	public static final ForgeConfigSpec.ConfigValue<Boolean> inferno_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Integer> inferno_normal_time;
	public static final ForgeConfigSpec.ConfigValue<Integer> inferno_long_time;
	public static final ForgeConfigSpec.ConfigValue<Boolean> absorption_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Integer> absorption_normal_time;
	public static final ForgeConfigSpec.ConfigValue<Integer> absorption_long_time;
	public static final ForgeConfigSpec.ConfigValue<Integer> absorption_normal_effect;
	public static final ForgeConfigSpec.ConfigValue<Integer> absorption_strong_effect;
	public static final ForgeConfigSpec.ConfigValue<Boolean> health_boost_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Integer> health_boost_normal_time;
	public static final ForgeConfigSpec.ConfigValue<Integer> health_boost_long_time;
	public static final ForgeConfigSpec.ConfigValue<Integer> health_boost_normal_effect;
	public static final ForgeConfigSpec.ConfigValue<Integer> health_boost_strong_effect;
	public static final ForgeConfigSpec.ConfigValue<Boolean> haste_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Integer> haste_normal_time;
	public static final ForgeConfigSpec.ConfigValue<Integer> haste_long_time;
	public static final ForgeConfigSpec.ConfigValue<Integer> haste_normal_effect;
	public static final ForgeConfigSpec.ConfigValue<Integer> haste_strong_effect;
	public static final ForgeConfigSpec.ConfigValue<Boolean> iron_skin_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Integer> iron_skin_normal_time;
	public static final ForgeConfigSpec.ConfigValue<Integer> iron_skin_long_time;
	public static final ForgeConfigSpec.ConfigValue<Boolean> diamond_skin_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Integer> diamond_skin_normal_time;
	public static final ForgeConfigSpec.ConfigValue<Integer> diamond_skin_long_time;
	public static final ForgeConfigSpec.ConfigValue<Boolean> reflect_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Integer> reflect_normal_time;
	public static final ForgeConfigSpec.ConfigValue<Integer> reflect_long_time;
	public static final ForgeConfigSpec.ConfigValue<Boolean> blast_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Integer> blast_normal_time;
	public static final ForgeConfigSpec.ConfigValue<Integer> blast_long_time;
	public static final ForgeConfigSpec.ConfigValue<Boolean> undeath_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Integer> undeath_normal_time;
	public static final ForgeConfigSpec.ConfigValue<Integer> undeath_long_time;
	public static final ForgeConfigSpec.ConfigValue<Boolean> frail_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Integer> frail_normal_time;
	public static final ForgeConfigSpec.ConfigValue<Integer> frail_long_time;
	//public static final ForgeConfigSpec.ConfigValue<Double> frail_multiplier;
	public static final ForgeConfigSpec.ConfigValue<Boolean> flight_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Integer> flight_normal_time;
	public static final ForgeConfigSpec.ConfigValue<Integer> flight_long_time;
	public static final ForgeConfigSpec.ConfigValue<Boolean> aqua_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Boolean> hell_touch_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Boolean> clense_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Boolean> cure_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Boolean> nullify_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Boolean> launching_is_craftable;
	public static final ForgeConfigSpec.ConfigValue<Boolean> lightning_is_craftable;
		

	/*public static double frailMultiplier;
	@SubscribeEvent
	public static void onModConfigEvent(final ModConfig.ModConfigEvent configEvent) {
		if (configEvent.getConfig().getSpec() == PnsConfig.SPEC) {
			bakeConfig();
		}
	}

	public static void bakeConfig() {
		PnsConfig.frailMultiplier = PnsConfig.frail_multiplier.get();
	}
	*/
	
	
	
	static {
		BUILDER.push("Set up which potions can be crafted");
		fuze_is_craftable = BUILDER.define("fuze potion", true);
		grounding_is_craftable = BUILDER.define("grounding potion", true);
		recall_is_craftable = BUILDER.define("recall potion", true);
		photosynthesis_is_craftable = BUILDER.define("photosynthesis potion", true);
		inferno_is_craftable = BUILDER.define("inferno potion", true);
		absorption_is_craftable = BUILDER.define("absorption potion", true);
		health_boost_is_craftable = BUILDER.define("health boost potion", true);
		haste_is_craftable = BUILDER.define("haste potion", true);
		iron_skin_is_craftable = BUILDER.define("iron skin potion", true);
		diamond_skin_is_craftable = BUILDER.define("diamond skin potion", true);
		reflect_is_craftable = BUILDER.define("projectile immunity potion", true);
		blast_is_craftable = BUILDER.define("blast immunity potion", true);
		undeath_is_craftable = BUILDER.define("undying potion", true);
		frail_is_craftable = BUILDER.define("frailness potion", true);
		flight_is_craftable = BUILDER.define("flight potion", true);
		aqua_is_craftable = BUILDER.define("aqua potion", true);
		hell_touch_is_craftable = BUILDER.define("hells touch potion", true);
		clense_is_craftable = BUILDER.define("clense potion", true);
		cure_is_craftable = BUILDER.comment("clense must be enabled for cure effect").define("cure potion", true);
		nullify_is_craftable = BUILDER.comment("clense must be enabled for nullify effect").define("nullify potion", true);
		launching_is_craftable = BUILDER.define("launching potion", true);
		lightning_is_craftable = BUILDER.define("lightning in a bottle", true);
		BUILDER.pop();
		
		BUILDER.comment("effect level range is [0-5]. Time is set as 100 = 5 seconds").push("values");
		BUILDER.push("fuze effect");
		fuze_normal_time = BUILDER.define("fuze normal time", 400);
		fuze_normal_effect = BUILDER.define("fuze normal level", 2);
		fuze_strong_effect = BUILDER.define("fuze strong level", 5);
		BUILDER.pop();
		BUILDER.push("grounding effect");
		grounding_normal_time = BUILDER.define("grounding normal time", 600);
		grounding_long_time = BUILDER.define("grounding long time", 1200);
		BUILDER.pop();
		BUILDER.push("recall effect");
		recall_normal_time = BUILDER.define("recall normal time", 600);
		recall_long_time = BUILDER.define("recall long time", 6000);
		BUILDER.pop();
		BUILDER.push("photosynthesis effect");
		photosynthesis_normal_time = BUILDER.define("photosynthesis normal time", 1200);
		photosynthesis_long_time = BUILDER.define("photosynthesis long time", 6000);
		BUILDER.pop();
		BUILDER.push("inferno effect");
		inferno_normal_time = BUILDER.define("inferno normal time", 400);
		inferno_long_time = BUILDER.define("inferno long time", 800);
		BUILDER.pop();
		BUILDER.push("absorption effect");
		absorption_normal_time = BUILDER.define("absorption normal time", 1200);
		absorption_long_time = BUILDER.define("absorption long time", 12000);
		absorption_normal_effect = BUILDER.define("absorption normal level", 2);
		absorption_strong_effect = BUILDER.define("absorption strong level", 4);
		BUILDER.pop();
		BUILDER.push("health boost effect");
		health_boost_normal_time = BUILDER.define("health boost normal time", 1200);
		health_boost_long_time = BUILDER.define("health boost long time", 12000);
		health_boost_normal_effect = BUILDER.define("health boost normal level", 2);
		health_boost_strong_effect = BUILDER.define("health boost strong level", 4);
		BUILDER.pop();
		BUILDER.push("haste effect");
		haste_normal_time = BUILDER.define("haste normal time", 1200);
		haste_long_time = BUILDER.define("haste long time", 12000);
		haste_normal_effect = BUILDER.define("haste normal level", 2);
		haste_strong_effect = BUILDER.define("haste strong level", 4);
		BUILDER.pop();
		BUILDER.push("iron skin effect");
		iron_skin_normal_time = BUILDER.define("iron skin normal time", 1200);
		iron_skin_long_time = BUILDER.define("iron skin long time", 12000);
		BUILDER.pop();
		BUILDER.push("diamond skin effect");
		diamond_skin_normal_time = BUILDER.define("diamond skin normal time", 1200);
		diamond_skin_long_time = BUILDER.define("diamond skin long time", 12000);
		BUILDER.pop();
		BUILDER.push("projectile immunity effect");
		reflect_normal_time = BUILDER.define("reflect normal time", 1200);
		reflect_long_time = BUILDER.define("reflect long time", 6000);
		BUILDER.pop();
		BUILDER.push("blast immunity effect");
		blast_normal_time = BUILDER.define("blast normal time", 1200);
		blast_long_time = BUILDER.define("blast long time", 6000);
		BUILDER.pop();
		BUILDER.push("undying effect");
		undeath_normal_time = BUILDER.define("undying normal time", 1200);
		undeath_long_time = BUILDER.define("undying long time", 6000);
		BUILDER.pop();
		BUILDER.push("frailness effect");
		frail_normal_time = BUILDER.define("frailness normal time", 600);
		frail_long_time = BUILDER.define("frailness long time", 1200);
		//frail_multiplier = BUILDER.comment("multiplier for damage taken while under frailness effect").define("frailness multiplier", 2.0);
		BUILDER.pop();
		BUILDER.push("flight effect");
		flight_normal_time = BUILDER.define("flight normal time", 600);
		flight_long_time = BUILDER.define("flight long time", 1200);
		BUILDER.pop();
		BUILDER.pop();
		
		SPEC = BUILDER.build();
	}

    public static void loadConfig(final ForgeConfigSpec config, final String path) {
        final CommentedFileConfig file = (CommentedFileConfig)CommentedFileConfig.builder(new File(path)).preserveInsertionOrder().sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig((CommentedConfig)file);
  
    }
}