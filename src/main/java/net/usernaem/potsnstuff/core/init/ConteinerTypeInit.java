package net.usernaem.potsnstuff.core.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.common.containers.PotionBagBlockContainer;
import net.usernaem.potsnstuff.common.containers.PotionBagContainer;


public class ConteinerTypeInit {
	
    public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister.create(
			ForgeRegistries.MENU_TYPES, PotsNStuff.MOD_ID_STRING);
    
    public static final RegistryObject<MenuType<PotionBagContainer>> POTION_BAG = CONTAINER_TYPES
			.register("potion_bag", () ->  new MenuType<>(PotionBagContainer::new));
    
    public static final RegistryObject<MenuType<PotionBagBlockContainer>> POTION_BAG_BLOCK = CONTAINER_TYPES
			.register("potion_bag_block", () ->  new MenuType<>(PotionBagBlockContainer::new));
    
  
}
