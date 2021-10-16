package net.usernaem.potsnstuff.core.init;

import net.minecraft.world.item.Items;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.common.potions.PotionRecipeSetup;
import net.usernaem.potsnstuff.core.config.PnsConfig;

public class PotionInit {
	
	
	 public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, PotsNStuff.MOD_ID_STRING);
	 
    public static final RegistryObject<Potion> AQUA = POTIONS.register("aqua", () -> new Potion(new MobEffectInstance(EffectInit.AQUA_OBJECT.get(), 1, 0)));
    public static final RegistryObject<Potion> HELLSTOUCH = POTIONS.register("hells_touch", () -> new Potion(new MobEffectInstance(EffectInit.HT_OBJECT.get(), 1, 0)));
    public static final RegistryObject<Potion> CLENSE = POTIONS.register("clense", () -> new Potion(new MobEffectInstance(EffectInit.CLENSE_OBJECT.get(), 1, 0)));
    public static final RegistryObject<Potion> LONG_CLENSE = POTIONS.register("long_clense", () -> new Potion(new MobEffectInstance(EffectInit.NULLIFY_OBJECT.get(), 1, 0)));
    public static final RegistryObject<Potion> STRONG_CLENSE = POTIONS.register("strong_clense", () -> new Potion(new MobEffectInstance(EffectInit.CURE_OBJECT.get(), 1, 0)));
    public static final RegistryObject<Potion> FUZE = POTIONS.register("fuze", () -> new Potion(new MobEffectInstance(EffectInit.FUSE_OBJECT.get(), PnsConfig.fuze_normal_time.get(), PnsConfig.fuze_normal_effect.get())));
    public static final RegistryObject<Potion> LONG_FUZE = POTIONS.register("long_fuze", () -> new Potion(new MobEffectInstance(EffectInit.BOMB_OBJECT.get(), 1, PnsConfig.fuze_normal_effect.get())));
    public static final RegistryObject<Potion> STRONG_FUZE = POTIONS.register("strong_fuze", () -> new Potion(new MobEffectInstance(EffectInit.FUSE_OBJECT.get(), PnsConfig.fuze_normal_time.get(), PnsConfig.fuze_strong_effect.get())));
    public static final RegistryObject<Potion> GROUNDING = POTIONS.register("grounding", () -> new Potion(new MobEffectInstance(EffectInit.GROUNDED_OBJECT.get(), PnsConfig.grounding_normal_time.get(), 0)));
    public static final RegistryObject<Potion> LONG_GROUNDING = POTIONS.register("long_grounding", () -> new Potion(new MobEffectInstance(EffectInit.GROUNDED_OBJECT.get(), PnsConfig.grounding_long_time.get(), 0)));
    //not needed- public static final RegistryObject<Potion> STRONG_GROUNDING = POTIONS.register("strong_grounding", () -> new Potion(new EffectInstance(EffectInit.GROUNDED_OBJECT.get(), 600, 1)));
    public static final RegistryObject<Potion> INFERNO = POTIONS.register("inferno", () -> new Potion(new MobEffectInstance(EffectInit.INFERN_OBJECT.get(), PnsConfig.inferno_normal_time.get(), 0)));
    public static final RegistryObject<Potion> LONG_INFERNO = POTIONS.register("long_inferno", () -> new Potion(new MobEffectInstance(EffectInit.INFERN_OBJECT.get(), PnsConfig.inferno_long_time.get(), 0)));
    public static final RegistryObject<Potion> STRONG_INFERNO = POTIONS.register("strong_inferno", () -> new Potion(new MobEffectInstance(EffectInit.INFERN_OBJECT.get(), PnsConfig.inferno_normal_time.get(), 1)));
    public static final RegistryObject<Potion> LAUNCH = POTIONS.register("launch", () -> new Potion(new MobEffectInstance(EffectInit.LAUNCH_OBJECT.get(), 1, 0)));
    public static final RegistryObject<Potion> STRONG_LAUNCH = POTIONS.register("strong_launch", () -> new Potion(new MobEffectInstance(EffectInit.LAUNCH_OBJECT.get(), 1, 1)));
    public static final RegistryObject<Potion> PHOTOSYNTHESIS = POTIONS.register("photosynthesis", () -> new Potion(new MobEffectInstance(EffectInit.PHOTOSYN_OBJECT.get(), PnsConfig.photosynthesis_normal_time.get(), 0)));
    public static final RegistryObject<Potion> LONG_PHOTOSYNTHESIS = POTIONS.register("long_photosynthesis", () -> new Potion(new MobEffectInstance(EffectInit.PHOTOSYN_OBJECT.get(), PnsConfig.photosynthesis_long_time.get(), 0)));
    public static final RegistryObject<Potion> STRONG_PHOTOSYNTHESIS = POTIONS.register("strong_photosynthesis", () -> new Potion(new MobEffectInstance(EffectInit.PHOTOSYN_OBJECT.get(), PnsConfig.photosynthesis_normal_time.get(), 1)));
    public static final RegistryObject<Potion> RECALL = POTIONS.register("recall", () -> new Potion(new MobEffectInstance(EffectInit.RECALL_OBJECT.get(), PnsConfig.recall_normal_time.get(), 0)));
    public static final RegistryObject<Potion> LONG_RECALL = POTIONS.register("long_recall", () -> new Potion(new MobEffectInstance(EffectInit.RECALL_OBJECT.get(), PnsConfig.recall_long_time.get(), 0)));
    //buggy effects, might add a fix in later version
    //public static final RegistryObject<Potion> CLAIRVOYANCE = POTIONS.register("clairvoyance", () -> new Potion(new EffectInstance(EffectInit.CLAIRVOYANCE_OBJECT.get(), 1200, 0)));
    //public static final RegistryObject<Potion> LONG_CLAIRVOYANCE = POTIONS.register("long_clairvoyance", () -> new Potion(new EffectInstance(EffectInit.CLAIRVOYANCE_OBJECT.get(), 6000, 0)));
    //public static final RegistryObject<Potion> STUN = POTIONS.register("stun", () -> new Potion(new EffectInstance(EffectInit.STUN_OBJECT.get(), 300, 0)));
    //public static final RegistryObject<Potion> LONG_STUN = POTIONS.register("long_stun", () -> new Potion(new EffectInstance(EffectInit.STUN_OBJECT.get(), 600, 0)));
    public static final RegistryObject<Potion> ABSORPTION = POTIONS.register("absorption", () -> new Potion(new MobEffectInstance(MobEffects.ABSORPTION, PnsConfig.absorption_normal_time.get(), PnsConfig.absorption_normal_effect.get())));
    public static final RegistryObject<Potion> LONG_ABSORPTION = POTIONS.register("long_absorption", () -> new Potion(new MobEffectInstance(MobEffects.ABSORPTION, PnsConfig.absorption_long_time.get(), PnsConfig.absorption_normal_effect.get())));
    public static final RegistryObject<Potion> STRONG_ABSORPTION = POTIONS.register("strong_absorption", () -> new Potion(new MobEffectInstance(MobEffects.ABSORPTION, PnsConfig.absorption_normal_time.get(), PnsConfig.absorption_strong_effect.get())));
    public static final RegistryObject<Potion> HEALTH_BOOST = POTIONS.register("health_boost", () -> new Potion(new MobEffectInstance(MobEffects.HEALTH_BOOST, PnsConfig.health_boost_normal_time.get(), PnsConfig.health_boost_normal_effect.get())));
    public static final RegistryObject<Potion> LONG_HEALTH_BOOST = POTIONS.register("long_health_boost", () -> new Potion(new MobEffectInstance(MobEffects.HEALTH_BOOST, PnsConfig.health_boost_long_time.get(), PnsConfig.health_boost_normal_effect.get())));
    public static final RegistryObject<Potion> STRONG_HEALTH_BOOST = POTIONS.register("strong_health_boost", () -> new Potion(new MobEffectInstance(MobEffects.HEALTH_BOOST, PnsConfig.health_boost_normal_time.get(), PnsConfig.health_boost_strong_effect.get())));
    public static final RegistryObject<Potion> HASTE = POTIONS.register("haste", () -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED, PnsConfig.haste_normal_time.get(), PnsConfig.haste_normal_effect.get())));
    public static final RegistryObject<Potion> LONG_HASTE = POTIONS.register("long_haste", () -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED, PnsConfig.haste_long_time.get(), PnsConfig.haste_normal_effect.get())));
    public static final RegistryObject<Potion> STRONG_HASTE = POTIONS.register("strong_haste", () -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED, PnsConfig.haste_normal_time.get(), PnsConfig.haste_strong_effect.get())));
    public static final RegistryObject<Potion> IRON_SKIN = POTIONS.register("iron_skin", () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, PnsConfig.iron_skin_normal_time.get(), 2)));
    public static final RegistryObject<Potion> LONG_IRON_SKIN = POTIONS.register("long_iron_skin", () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, PnsConfig.iron_skin_long_time.get(), 2)));
    public static final RegistryObject<Potion> STRONG_IRON_SKIN = POTIONS.register("strong_iron_skin", () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, PnsConfig.iron_skin_normal_time.get(), 3)));
    public static final RegistryObject<Potion> DIAMOND_SKIN = POTIONS.register("diamond_skin", () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, PnsConfig.diamond_skin_normal_time.get(), 3)));
    public static final RegistryObject<Potion> LONG_DIAMOND_SKIN = POTIONS.register("long_diamond_skin", () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, PnsConfig.diamond_skin_long_time.get(), 3)));
    public static final RegistryObject<Potion> STRONG_DIAMOND_SKIN = POTIONS.register("strong_diamond_skin", () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, PnsConfig.diamond_skin_normal_time.get(), 4)));
    public static final RegistryObject<Potion> REFLECT = POTIONS.register("reflection", () -> new Potion(new MobEffectInstance(EffectInit.REFLECT_OBJECT.get(), 1200, 0)));
    public static final RegistryObject<Potion> LONG_REFLECT = POTIONS.register("long_reflection", () -> new Potion(new MobEffectInstance(EffectInit.REFLECT_OBJECT.get(), 6000, 0)));
    public static final RegistryObject<Potion> BLAST = POTIONS.register("blast", () -> new Potion(new MobEffectInstance(EffectInit.BLAST_OBJECT.get(), PnsConfig.blast_normal_time.get(), 0)));
    public static final RegistryObject<Potion> LONG_BLAST = POTIONS.register("long_blast", () -> new Potion(new MobEffectInstance(EffectInit.BLAST_OBJECT.get(), PnsConfig.blast_long_time.get(), 0)));
    public static final RegistryObject<Potion> UNDEATH = POTIONS.register("undeath", () -> new Potion(new MobEffectInstance(EffectInit.UNDEATH_OBJECT.get(), PnsConfig.undeath_normal_time.get(), 0)));
    public static final RegistryObject<Potion> LONG_UNDEATH = POTIONS.register("long_undeath", () -> new Potion(new MobEffectInstance(EffectInit.UNDEATH_OBJECT.get(), PnsConfig.undeath_long_time.get(), 0)));
    public static final RegistryObject<Potion> STRONG_UNDEATH = POTIONS.register("strong_undeath", () -> new Potion(new MobEffectInstance(EffectInit.UNDEATH_OBJECT.get(), PnsConfig.undeath_normal_time.get(), 1)));
    public static final RegistryObject<Potion> LIGHTNING = POTIONS.register("lightning", () -> new Potion(new MobEffectInstance(EffectInit.LIGHTNING_OBJECT.get(), 1, 0)));
    public static final RegistryObject<Potion> FRAIL = POTIONS.register("frail", () -> new Potion(new MobEffectInstance(EffectInit.FRAIL_OBJECT.get(), PnsConfig.frail_normal_time.get(), 0)));
    public static final RegistryObject<Potion> LONG_FRAIL = POTIONS.register("long_frail", () -> new Potion(new MobEffectInstance(EffectInit.FRAIL_OBJECT.get(), PnsConfig.frail_long_time.get(), 0)));
    public static final RegistryObject<Potion> FLIGHT = POTIONS.register("flight", () -> new Potion(new MobEffectInstance(EffectInit.FLIGHT_OBJECT.get(), PnsConfig.flight_normal_time.get(), 0)));
    public static final RegistryObject<Potion> LONG_FLIGHT = POTIONS.register("long_flight", () -> new Potion(new MobEffectInstance(EffectInit.FLIGHT_OBJECT.get(), PnsConfig.flight_long_time.get(), 0)));

    
    
    
    public static void addPotionRecipes(){
        PotionRecipeSetup.addPotionRecipes(Potions.THICK, Items.BLUE_ICE, AQUA.get(), null, null, PnsConfig.aqua_is_craftable.get());
        PotionRecipeSetup.addPotionRecipes(Potions.THICK, Items.MAGMA_BLOCK, HELLSTOUCH.get(), null, null, PnsConfig.hell_touch_is_craftable.get());
        PotionRecipeSetup.addPotionRecipes(Potions.MUNDANE, Items.MILK_BUCKET, CLENSE.get(), PnsConfig.nullify_is_craftable.get() ? LONG_CLENSE.get() : null, PnsConfig.cure_is_craftable.get() ? STRONG_CLENSE.get() : null, PnsConfig.clense_is_craftable.get());
        PotionRecipeSetup.addPotionRecipes(Potions.AWKWARD, Items.FIREWORK_STAR, FUZE.get(), LONG_FUZE.get(), STRONG_FUZE.get(), PnsConfig.fuze_is_craftable.get());
        PotionRecipeSetup.addPotionRecipes(Potions.SLOW_FALLING, Items.FERMENTED_SPIDER_EYE, GROUNDING.get(), LONG_GROUNDING.get(), null, PnsConfig.grounding_is_craftable.get());
        PotionRecipeSetup.addPotionRecipes(Potions.FIRE_RESISTANCE, Items.FERMENTED_SPIDER_EYE, INFERNO.get(), LONG_INFERNO.get(), STRONG_INFERNO.get(), PnsConfig.inferno_is_craftable.get());
        PotionRecipeSetup.addPotionRecipes(GROUNDING.get(), Items.FERMENTED_SPIDER_EYE, LAUNCH.get(), null, STRONG_LAUNCH.get(), PnsConfig.launching_is_craftable.get());
        PotionRecipeSetup.addPotionRecipes(Potions.MUNDANE, Items.WHEAT_SEEDS, PHOTOSYNTHESIS.get(), LONG_PHOTOSYNTHESIS.get(), STRONG_PHOTOSYNTHESIS.get(), PnsConfig.photosynthesis_is_craftable.get());
        PotionRecipeSetup.addPotionRecipes(Potions.AWKWARD, Items.CHORUS_FRUIT, RECALL.get(), LONG_RECALL.get(), null, PnsConfig.recall_is_craftable.get());
        //PotionRecipeSetup.addPotionRecipes(Potions.AWKWARD, Items.QUARTZ, CLAIRVOYANCE.get(), LONG_CLAIRVOYANCE.get(), null);
        //PotionRecipeSetup.addPotionRecipes(Potions.AWKWARD, Items.STRING, STUN.get(), LONG_STUN.get(), null);
        PotionRecipeSetup.addPotionRecipes(Potions.AWKWARD, Items.GOLDEN_APPLE, ABSORPTION.get(), LONG_ABSORPTION.get(), STRONG_ABSORPTION.get(), PnsConfig.absorption_is_craftable.get());
        PotionRecipeSetup.addPotionRecipes(Potions.AWKWARD, Items.GOLDEN_CARROT, HEALTH_BOOST.get(), LONG_HEALTH_BOOST.get(), STRONG_HEALTH_BOOST.get(), PnsConfig.health_boost_is_craftable.get());
        PotionRecipeSetup.addPotionRecipes(Potions.MUNDANE, Items.CARROT, HASTE.get(), LONG_HASTE.get(), STRONG_HASTE.get(), PnsConfig.haste_is_craftable.get());
        PotionRecipeSetup.addPotionRecipes(Potions.THICK, Items.IRON_NUGGET, IRON_SKIN.get(), LONG_IRON_SKIN.get(), STRONG_IRON_SKIN.get(), PnsConfig.iron_skin_is_craftable.get());
        PotionRecipeSetup.addPotionRecipes(Potions.THICK, ItemInit.DIAMOND_NUGGET_OBJECT.get(), DIAMOND_SKIN.get(), LONG_DIAMOND_SKIN.get(), STRONG_DIAMOND_SKIN.get(), PnsConfig.diamond_skin_is_craftable.get());
        PotionRecipeSetup.addPotionRecipes(Potions.AWKWARD, Items.BONE_MEAL, REFLECT.get(), LONG_REFLECT.get(),null, PnsConfig.reflect_is_craftable.get());
        PotionRecipeSetup.addPotionRecipes(FUZE.get(),Items.FERMENTED_SPIDER_EYE, BLAST.get(), LONG_BLAST.get(), null, PnsConfig.blast_is_craftable.get());
        PotionRecipeSetup.addPotionRecipes(ABSORPTION.get(),Items.EMERALD, UNDEATH.get(), LONG_UNDEATH.get(), STRONG_UNDEATH.get(), PnsConfig.undeath_is_craftable.get());
        PotionRecipeSetup.addPotionRecipes(Potions.THICK, Items.GOLD_NUGGET, LIGHTNING.get(), null, null, PnsConfig.lightning_is_craftable.get());
        PotionRecipeSetup.addPotionRecipes(Potions.AWKWARD, Items.ROTTEN_FLESH, FRAIL.get(), LONG_FRAIL.get(), null, PnsConfig.frail_is_craftable.get());
        PotionRecipeSetup.addPotionRecipes(LAUNCH.get(), Items.NETHER_STAR, FLIGHT.get(), LONG_FLIGHT.get(), null, PnsConfig.flight_is_craftable.get());
    }

   
}
