package net.usernaem.potsnstuff.client.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.common.items.PotionMarbleItem;
import net.usernaem.potsnstuff.common.items.PotionVialItem;
import net.usernaem.potsnstuff.core.init.ItemInit;

@EventBusSubscriber(modid = PotsNStuff.MOD_ID_STRING, bus = Bus.MOD, value = {Dist.CLIENT})
public class ColorHandlers {

	@SubscribeEvent
	public static void registerItemColor(ColorHandlerEvent.Item eventItem) {
		eventItem.getItemColors().register(PotionVialItem::getItemColor, ItemInit.POTVIAL_OBJECT.get());
		eventItem.getItemColors().register(PotionMarbleItem::getItemColor, ItemInit.POTMARBL_OBJECT.get());
	}

}
