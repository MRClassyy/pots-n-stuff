package net.usernaem.potsnstuff.core.init;

import net.minecraft.world.item.Item;


import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.common.items.PotionBagItem;
import net.usernaem.potsnstuff.common.items.PotionMarbleItem;
import net.usernaem.potsnstuff.common.items.PotionVialItem;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PotsNStuff.MOD_ID_STRING );
	
	public static final RegistryObject<Item> DIAMOND_NUGGET_OBJECT = ITEMS.register("diamond_nugget", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	
	public static final RegistryObject<Item> EMPTYVIAL_OBJECT = ITEMS.register("vial_empty", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	
	public static final RegistryObject<Item> EMPTYMARBL_OBJECT = ITEMS.register("marble_empty", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	
	public static final RegistryObject<PotionVialItem> POTVIAL_OBJECT = ITEMS.register("potion_vial", () -> new PotionVialItem());
	
	public static final RegistryObject<PotionMarbleItem> POTMARBL_OBJECT = ITEMS.register("potion_marble", () -> new PotionMarbleItem());
	
	public static final RegistryObject<PotionBagItem> POTBAG_OBJECT = ITEMS.register("potion_bag", () -> new PotionBagItem());
	
}
