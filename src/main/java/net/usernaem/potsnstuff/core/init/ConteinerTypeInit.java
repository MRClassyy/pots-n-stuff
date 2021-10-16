package net.usernaem.potsnstuff.core.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.common.containers.PotionBagContainer;


public class ConteinerTypeInit {
	
    public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister.create(
			ForgeRegistries.CONTAINERS, PotsNStuff.MOD_ID_STRING);
    
    /*public static final RegistryObject<MenuType<PotionBagContainer>> POTION_BAG = CONTAINER_TYPES
			.register("potion_bag", () ->  new MenuType<>(PotionBagContainer::new));*/
    
  
}
