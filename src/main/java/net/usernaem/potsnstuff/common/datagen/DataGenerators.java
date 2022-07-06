package net.usernaem.potsnstuff.common.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.usernaem.potsnstuff.PotsNStuff;

@Mod.EventBusSubscriber(modid = PotsNStuff.MOD_ID_STRING, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

	 @SubscribeEvent
	    public static void gatherData(GatherDataEvent event) {
		 DataGenerator generator = event.getGenerator();
	        if (event.includeServer()) {
	        	generator.addProvider(true, new LootTables(generator));
	        }
	 }
}
