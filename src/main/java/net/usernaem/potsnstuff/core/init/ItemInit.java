package net.usernaem.potsnstuff.core.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.common.items.DiamondNuggetItem;
import net.usernaem.potsnstuff.common.items.EmptyMarbleItem;
import net.usernaem.potsnstuff.common.items.EmptyVialItem;
import net.usernaem.potsnstuff.common.items.PotionBagItem;
import net.usernaem.potsnstuff.common.items.PotionMarbleItem;
import net.usernaem.potsnstuff.common.items.PotionVialItem;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PotsNStuff.MOD_ID_STRING );
	
	public static final RegistryObject<DiamondNuggetItem> DIAMOND_NUGGET_OBJECT = ITEMS.register("diamond_nugget", () -> new DiamondNuggetItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	
	public static final RegistryObject<EmptyVialItem> EMPTYVIAL_OBJECT = ITEMS.register("vial_empty", () -> new EmptyVialItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	
	public static final RegistryObject<EmptyMarbleItem> EMPTYMARBL_OBJECT = ITEMS.register("marble_empty", () -> new EmptyMarbleItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	
	public static final RegistryObject<PotionVialItem> POTVIAL_OBJECT = ITEMS.register("potion_vial", () -> new PotionVialItem());
	
	public static final RegistryObject<PotionMarbleItem> POTMARBL_OBJECT = ITEMS.register("potion_marble", () -> new PotionMarbleItem());
	
	public static final RegistryObject<PotionBagItem> POTBAG_OBJECT = ITEMS.register("potion_bag", () -> new PotionBagItem());
}
