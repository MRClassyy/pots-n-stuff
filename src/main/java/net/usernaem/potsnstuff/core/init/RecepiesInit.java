package net.usernaem.potsnstuff.core.init;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.common.items.crafting.PotionMarbleRecipe;
import net.usernaem.potsnstuff.common.items.crafting.PotionVialRecipe;
import net.usernaem.potsnstuff.common.items.crafting.TippedWeaponRecipe;

public class RecepiesInit {

	public static final class Types {
        public static final RecipeType<PotionVialRecipe> VIAL_TYPE = RecipeType.register(
                PotsNStuff.MOD_ID_STRING + "potion_recipes");

        private Types() {}
    }

    public static final class Serializers {
        
    }

    static void register() {}

    
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, PotsNStuff.MOD_ID_STRING );

	 public static final RegistryObject<RecipeSerializer<PotionVialRecipe>> POTION_VIAL_OBJECT = RECIPE_SERIALIZERS.register("potion_vial_recipe", PotionVialRecipe.Serializer::new);
	 public static final RegistryObject<RecipeSerializer<PotionMarbleRecipe>> POTION_MARBL_OBJECT = RECIPE_SERIALIZERS.register("potion_marble_recipe", PotionMarbleRecipe.Serializer::new);
	 public static final RegistryObject<RecipeSerializer<TippedWeaponRecipe>> TIPPED_WEAPON_OBJECT = RECIPE_SERIALIZERS.register("tipped_weapon_recipe", TippedWeaponRecipe.Serializer::new);
	 	
}
