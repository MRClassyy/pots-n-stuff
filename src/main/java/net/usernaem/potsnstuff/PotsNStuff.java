package net.usernaem.potsnstuff;


import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.usernaem.potsnstuff.core.data.MyPotStuffCapability;
import net.usernaem.potsnstuff.core.init.ConteinerTypeInit;
import net.usernaem.potsnstuff.core.init.EffectInit;
import net.usernaem.potsnstuff.core.init.ItemInit;
import net.usernaem.potsnstuff.core.init.PotionInit;
import net.usernaem.potsnstuff.core.init.RecepiesInit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.annotation.Nonnull;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(PotsNStuff.MOD_ID_STRING)
public class PotsNStuff
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    
    public static final String MOD_ID_STRING = "potsnstuff";

    public PotsNStuff() {
    	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(MyPotStuffCapability::register);
        ConteinerTypeInit.CONTAINER_TYPES.register(bus);
        ItemInit.ITEMS.register(bus);
        EffectInit.EFFECTS.register(bus);
        RecepiesInit.RECIPE_SERIALIZERS.register(bus);
        PotionInit.POTIONS.register(bus);
		//MinecraftForge.EVENT_BUS.addListener(MyPotStuffCapability::AttachCapabilities);
		MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    	event.enqueueWork(PotionInit::addPotionRecipes);
    }
    @Nonnull
    public static ResourceLocation getId(String path) {
        return new ResourceLocation(MOD_ID_STRING, path);
    }
}
