package net.usernaem.potsnstuff.client.event;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.client.gui.PotionBagContainerScreen;
import net.usernaem.potsnstuff.core.init.ConteinerTypeInit;

@EventBusSubscriber(modid = PotsNStuff.MOD_ID_STRING, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
	
	@SubscribeEvent
	 public static void registerScreens(FMLClientSetupEvent event) {
	        MenuScreens.register(ConteinerTypeInit.POTION_BAG.get(), PotionBagContainerScreen::new);
	    }
	
}
