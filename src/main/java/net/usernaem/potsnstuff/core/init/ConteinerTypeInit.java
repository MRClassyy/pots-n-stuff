package net.usernaem.potsnstuff.core.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.common.containers.GloveBagBlockContainer;
import net.usernaem.potsnstuff.common.containers.GloveBagContainer;
import net.usernaem.potsnstuff.common.containers.GloveContainer;
import net.usernaem.potsnstuff.common.containers.PotionBagBlockContainer;
import net.usernaem.potsnstuff.common.containers.PotionBagContainer;


public class ConteinerTypeInit {
	
    public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister.create(
			ForgeRegistries.MENU_TYPES, PotsNStuff.MOD_ID_STRING);
    
    public static final RegistryObject<MenuType<PotionBagContainer>> POTION_BAG = CONTAINER_TYPES
			.register("potion_bag", () ->  new MenuType<>(PotionBagContainer::new));
    
    public static final RegistryObject<MenuType<PotionBagBlockContainer>> POTION_BAG_BLOCK = CONTAINER_TYPES
			.register("potion_bag_block", () ->  new MenuType<>(PotionBagBlockContainer::new));
    
    public static final RegistryObject<MenuType<GloveContainer>> GLOVE_ITEM = CONTAINER_TYPES
			.register("glove_item", () ->  new MenuType<>(GloveContainer::new));

    public static final RegistryObject<MenuType<GloveBagContainer>> POTION_BAG_GLOVE = CONTAINER_TYPES
			.register("potion_bag_glove", () ->  new MenuType<>(GloveBagContainer::new));

    public static final RegistryObject<MenuType<GloveBagBlockContainer>> POTION_BAG_BLOCK_GLOVE = CONTAINER_TYPES
			.register("potion_bag_block_glove", () ->  new MenuType<>(GloveBagBlockContainer::new));
}
