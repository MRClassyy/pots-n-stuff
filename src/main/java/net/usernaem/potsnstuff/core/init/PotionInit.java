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
//import net.usernaem.potsnstuff.core.config.PnsConfig;

public class PotionInit {
	
	
	 public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, PotsNStuff.MOD_ID_STRING);
	 
    public static final RegistryObject<Potion> AQUA = POTIONS.register("aqua", () -> new Potion(new MobEffectInstance(EffectInit.AQUA_OBJECT.get(), 1, 0)));
    public static final RegistryObject<Potion> HELLSTOUCH = POTIONS.register("hells_touch", () -> new Potion(new MobEffectInstance(EffectInit.HT_OBJECT.get(), 1, 0)));
    public static final RegistryObject<Potion> CLENSE = POTIONS.register("clense", () -> new Potion(new MobEffectInstance(EffectInit.CLENSE_OBJECT.get(), 1, 0)));
    public static final RegistryObject<Potion> LONG_CLENSE = POTIONS.register("long_clense", () -> new Potion(new MobEffectInstance(EffectInit.NULLIFY_OBJECT.get(), 1, 0)));
    public static final RegistryObject<Potion> STRONG_CLENSE = POTIONS.register("strong_clense", () -> new Potion(new MobEffectInstance(EffectInit.CURE_OBJECT.get(), 1, 0)));
    public static final RegistryObject<Potion> FUZE = POTIONS.register("fuze", () -> new Potion(new MobEffectInstance(EffectInit.FUSE_OBJECT.get(), 400, 2)));
    public static final RegistryObject<Potion> LONG_FUZE = POTIONS.register("long_fuze", () -> new Potion(new MobEffectInstance(EffectInit.BOMB_OBJECT.get(), 1, 2)));
    public static final RegistryObject<Potion> STRONG_FUZE = POTIONS.register("strong_fuze", () -> new Potion(new MobEffectInstance(EffectInit.FUSE_OBJECT.get(), 400, 5)));
    public static final RegistryObject<Potion> GROUNDING = POTIONS.register("grounding", () -> new Potion(new MobEffectInstance(EffectInit.GROUNDED_OBJECT.get(), 600, 0)));
    public static final RegistryObject<Potion> LONG_GROUNDING = POTIONS.register("long_grounding", () -> new Potion(new MobEffectInstance(EffectInit.GROUNDED_OBJECT.get(), 1200, 0)));
    public static final RegistryObject<Potion> INFERNO = POTIONS.register("inferno", () -> new Potion(new MobEffectInstance(EffectInit.INFERN_OBJECT.get(), 400, 0)));
    public static final RegistryObject<Potion> LONG_INFERNO = POTIONS.register("long_inferno", () -> new Potion(new MobEffectInstance(EffectInit.INFERN_OBJECT.get(), 800, 0)));
    public static final RegistryObject<Potion> STRONG_INFERNO = POTIONS.register("strong_inferno", () -> new Potion(new MobEffectInstance(EffectInit.INFERN_OBJECT.get(), 400, 1)));
    public static final RegistryObject<Potion> LAUNCH = POTIONS.register("launch", () -> new Potion(new MobEffectInstance(EffectInit.LAUNCH_OBJECT.get(), 1, 0)));
    public static final RegistryObject<Potion> STRONG_LAUNCH = POTIONS.register("strong_launch", () -> new Potion(new MobEffectInstance(EffectInit.LAUNCH_OBJECT.get(), 1, 1)));
    public static final RegistryObject<Potion> PHOTOSYNTHESIS = POTIONS.register("photosynthesis", () -> new Potion(new MobEffectInstance(EffectInit.PHOTOSYN_OBJECT.get(), 1200, 0)));
    public static final RegistryObject<Potion> LONG_PHOTOSYNTHESIS = POTIONS.register("long_photosynthesis", () -> new Potion(new MobEffectInstance(EffectInit.PHOTOSYN_OBJECT.get(), 6000, 0)));
    public static final RegistryObject<Potion> STRONG_PHOTOSYNTHESIS = POTIONS.register("strong_photosynthesis", () -> new Potion(new MobEffectInstance(EffectInit.PHOTOSYN_OBJECT.get(), 1200, 1)));
    public static final RegistryObject<Potion> RECALL = POTIONS.register("recall", () -> new Potion(new MobEffectInstance(EffectInit.RECALL_OBJECT.get(), 600, 0)));
    public static final RegistryObject<Potion> LONG_RECALL = POTIONS.register("long_recall", () -> new Potion(new MobEffectInstance(EffectInit.RECALL_OBJECT.get(), 6000, 0)));
    public static final RegistryObject<Potion> ABSORPTION = POTIONS.register("absorption", () -> new Potion(new MobEffectInstance(MobEffects.ABSORPTION, 1200, 1)));
    public static final RegistryObject<Potion> LONG_ABSORPTION = POTIONS.register("long_absorption", () -> new Potion(new MobEffectInstance(MobEffects.ABSORPTION, 6000, 1)));
    public static final RegistryObject<Potion> STRONG_ABSORPTION = POTIONS.register("strong_absorption", () -> new Potion(new MobEffectInstance(MobEffects.ABSORPTION, 1200, 3)));
    public static final RegistryObject<Potion> HEALTH_BOOST = POTIONS.register("health_boost", () -> new Potion(new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 1)));
    public static final RegistryObject<Potion> LONG_HEALTH_BOOST = POTIONS.register("long_health_boost", () -> new Potion(new MobEffectInstance(MobEffects.HEALTH_BOOST, 6000, 1)));
    public static final RegistryObject<Potion> STRONG_HEALTH_BOOST = POTIONS.register("strong_health_boost", () -> new Potion(new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 3)));
    public static final RegistryObject<Potion> HASTE = POTIONS.register("haste", () -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 1)));
    public static final RegistryObject<Potion> LONG_HASTE = POTIONS.register("long_haste", () -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED, 6000, 1)));
    public static final RegistryObject<Potion> STRONG_HASTE = POTIONS.register("strong_haste", () -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 3)));
    public static final RegistryObject<Potion> IRON_SKIN = POTIONS.register("iron_skin", () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 2)));
    public static final RegistryObject<Potion> LONG_IRON_SKIN = POTIONS.register("long_iron_skin", () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 12000, 2)));
    public static final RegistryObject<Potion> STRONG_IRON_SKIN = POTIONS.register("strong_iron_skin", () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 3)));
    public static final RegistryObject<Potion> DIAMOND_SKIN = POTIONS.register("diamond_skin", () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 3)));
    public static final RegistryObject<Potion> LONG_DIAMOND_SKIN = POTIONS.register("long_diamond_skin", () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 12000, 3)));
    public static final RegistryObject<Potion> STRONG_DIAMOND_SKIN = POTIONS.register("strong_diamond_skin", () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 4)));
    public static final RegistryObject<Potion> REFLECT = POTIONS.register("reflection", () -> new Potion(new MobEffectInstance(EffectInit.REFLECT_OBJECT.get(), 1200, 0)));
    public static final RegistryObject<Potion> LONG_REFLECT = POTIONS.register("long_reflection", () -> new Potion(new MobEffectInstance(EffectInit.REFLECT_OBJECT.get(), 6000, 0)));
    public static final RegistryObject<Potion> BLAST = POTIONS.register("blast", () -> new Potion(new MobEffectInstance(EffectInit.BLAST_OBJECT.get(), 1200, 0)));
    public static final RegistryObject<Potion> LONG_BLAST = POTIONS.register("long_blast", () -> new Potion(new MobEffectInstance(EffectInit.BLAST_OBJECT.get(), 6000, 0)));
    public static final RegistryObject<Potion> UNDEATH = POTIONS.register("undeath", () -> new Potion(new MobEffectInstance(EffectInit.UNDEATH_OBJECT.get(), 1200, 0)));
    public static final RegistryObject<Potion> LONG_UNDEATH = POTIONS.register("long_undeath", () -> new Potion(new MobEffectInstance(EffectInit.UNDEATH_OBJECT.get(), 6000, 0)));
    public static final RegistryObject<Potion> STRONG_UNDEATH = POTIONS.register("strong_undeath", () -> new Potion(new MobEffectInstance(EffectInit.UNDEATH_OBJECT.get(), 1200, 1)));
    public static final RegistryObject<Potion> LIGHTNING = POTIONS.register("lightning", () -> new Potion(new MobEffectInstance(EffectInit.LIGHTNING_OBJECT.get(), 1, 0)));
    public static final RegistryObject<Potion> FRAIL = POTIONS.register("frail", () -> new Potion(new MobEffectInstance(EffectInit.FRAIL_OBJECT.get(), 600, 0)));
    public static final RegistryObject<Potion> LONG_FRAIL = POTIONS.register("long_frail", () -> new Potion(new MobEffectInstance(EffectInit.FRAIL_OBJECT.get(), 1200, 0)));
    public static final RegistryObject<Potion> FLIGHT = POTIONS.register("flight", () -> new Potion(new MobEffectInstance(EffectInit.FLIGHT_OBJECT.get(), 1200, 0)));
    public static final RegistryObject<Potion> LONG_FLIGHT = POTIONS.register("long_flight", () -> new Potion(new MobEffectInstance(EffectInit.FLIGHT_OBJECT.get(), 6000, 0)));
    public static final RegistryObject<Potion> FLOATING = POTIONS.register("floating", () -> new Potion(new MobEffectInstance(MobEffects.LEVITATION, 600, 0)));
    public static final RegistryObject<Potion> LONG_FLOATING = POTIONS.register("long_floating", () -> new Potion(new MobEffectInstance(MobEffects.LEVITATION, 1200, 0)));
    
    
    
    
    public static void addPotionRecipes(){
        PotionRecipeSetup.addPotionRecipes(Potions.THICK, Items.BLUE_ICE, AQUA.get(), null, null);
        PotionRecipeSetup.addPotionRecipes(Potions.THICK, Items.MAGMA_BLOCK, HELLSTOUCH.get(), null, null);
        PotionRecipeSetup.addPotionRecipes(Potions.MUNDANE, Items.MILK_BUCKET, CLENSE.get(), LONG_CLENSE.get(), STRONG_CLENSE.get());
        PotionRecipeSetup.addPotionRecipes(Potions.AWKWARD, Items.FIREWORK_STAR, FUZE.get(), LONG_FUZE.get(), STRONG_FUZE.get());
        PotionRecipeSetup.addPotionRecipes(LAUNCH.get(), Items.FERMENTED_SPIDER_EYE, GROUNDING.get(), LONG_GROUNDING.get(), null);
        PotionRecipeSetup.addPotionRecipes(Potions.FIRE_RESISTANCE, Items.FERMENTED_SPIDER_EYE, INFERNO.get(), LONG_INFERNO.get(), STRONG_INFERNO.get());
        PotionRecipeSetup.addPotionRecipes(FLOATING.get(), Items.GLOWSTONE_DUST, LAUNCH.get(), null, STRONG_LAUNCH.get());
        PotionRecipeSetup.addPotionRecipes(Potions.MUNDANE, Items.WHEAT_SEEDS, PHOTOSYNTHESIS.get(), LONG_PHOTOSYNTHESIS.get(), STRONG_PHOTOSYNTHESIS.get());
        PotionRecipeSetup.addPotionRecipes(Potions.AWKWARD, Items.CHORUS_FRUIT, RECALL.get(), LONG_RECALL.get(), null);
        PotionRecipeSetup.addPotionRecipes(Potions.AWKWARD, Items.GOLDEN_APPLE, ABSORPTION.get(), LONG_ABSORPTION.get(), STRONG_ABSORPTION.get());
        PotionRecipeSetup.addPotionRecipes(Potions.AWKWARD, Items.POTATO, HEALTH_BOOST.get(), LONG_HEALTH_BOOST.get(), STRONG_HEALTH_BOOST.get());
        PotionRecipeSetup.addPotionRecipes(Potions.MUNDANE, Items.CARROT, HASTE.get(), LONG_HASTE.get(), STRONG_HASTE.get());
        PotionRecipeSetup.addPotionRecipes(Potions.THICK, Items.IRON_NUGGET, IRON_SKIN.get(), LONG_IRON_SKIN.get(), STRONG_IRON_SKIN.get());
        PotionRecipeSetup.addPotionRecipes(Potions.THICK, ItemInit.DIAMOND_NUGGET_OBJECT.get(), DIAMOND_SKIN.get(), LONG_DIAMOND_SKIN.get(), STRONG_DIAMOND_SKIN.get());
        PotionRecipeSetup.addPotionRecipes(Potions.AWKWARD, Items.BONE_MEAL, REFLECT.get(), LONG_REFLECT.get(),null);
        PotionRecipeSetup.addPotionRecipes(FUZE.get(),Items.FERMENTED_SPIDER_EYE, BLAST.get(), LONG_BLAST.get(), null);
        PotionRecipeSetup.addPotionRecipes(ABSORPTION.get(),Items.EMERALD, UNDEATH.get(), LONG_UNDEATH.get(), STRONG_UNDEATH.get());
        PotionRecipeSetup.addPotionRecipes(Potions.THICK, Items.GOLD_NUGGET, LIGHTNING.get(), null, null);
        PotionRecipeSetup.addPotionRecipes(Potions.AWKWARD, Items.ROTTEN_FLESH, FRAIL.get(), LONG_FRAIL.get(), null);
        PotionRecipeSetup.addPotionRecipes(FLOATING.get(), Items.FEATHER, FLIGHT.get(), LONG_FLIGHT.get(), null);
        PotionRecipeSetup.addPotionRecipes(Potions.SLOW_FALLING, Items.FERMENTED_SPIDER_EYE, FLOATING.get(), LONG_FLOATING.get(), null);    
    }

   
}
