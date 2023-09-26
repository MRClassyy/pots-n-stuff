package net.usernaem.potsnstuff.client.event;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.client.gui.GloveBagBlockContainerScreen;
import net.usernaem.potsnstuff.client.gui.GloveBagContainerScreen;
import net.usernaem.potsnstuff.client.gui.GloveContainerScreen;
import net.usernaem.potsnstuff.client.gui.PotionBagBlockScreen;
import net.usernaem.potsnstuff.client.gui.PotionBagContainerScreen;
import net.usernaem.potsnstuff.client.util.KeyBinding;
import net.usernaem.potsnstuff.core.init.BlockInit;
import net.usernaem.potsnstuff.core.init.ConteinerTypeInit;

@EventBusSubscriber(modid = PotsNStuff.MOD_ID_STRING, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
		
	@SubscribeEvent
	 public static void registerScreens(FMLClientSetupEvent event) {
	        MenuScreens.register(ConteinerTypeInit.POTION_BAG.get(), PotionBagContainerScreen::new);
	        MenuScreens.register(ConteinerTypeInit.POTION_BAG_BLOCK.get(), PotionBagBlockScreen::new);
	        MenuScreens.register(ConteinerTypeInit.GLOVE_ITEM.get(), GloveContainerScreen::new);
	        MenuScreens.register(ConteinerTypeInit.POTION_BAG_GLOVE.get(), GloveBagContainerScreen::new);
	        MenuScreens.register(ConteinerTypeInit.POTION_BAG_BLOCK_GLOVE.get(), GloveBagBlockContainerScreen::new);
	        ItemBlockRenderTypes.setRenderLayer(BlockInit.POTION_BAG_BLOCK.get(), RenderType.cutout());
	 }
	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		
	}
	@SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(KeyBinding.GLOVE_KEY);
    }
}
