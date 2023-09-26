package net.usernaem.potsnstuff.client.event;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.client.util.ClientUtils;
import net.usernaem.potsnstuff.client.util.KeyBinding;
import net.usernaem.potsnstuff.common.items.crafting.TippedWeaponRecipe;
import net.usernaem.potsnstuff.core.config.CraftConfig;

@EventBusSubscriber(modid = PotsNStuff.MOD_ID_STRING, bus = Bus.FORGE, value = Dist.CLIENT)
public class ClientEventBusSubscriberForge {
	@SubscribeEvent
    public static void onToolTip(final ItemTooltipEvent event) {
         ItemStack stack = event.getItemStack();
        if (!TippedWeaponRecipe.isValidWeapon(stack)) {
            return;
        }
        if (stack.getTag() == null) {
            return;
        }
        List<MobEffectInstance> effects = (List<MobEffectInstance>)PotionUtils.getAllEffects(stack.getTag().getCompound("Potion"));
        for ( MobEffectInstance i : effects) {
            event.getToolTip().add(Component.translatable(i.getEffect().getDisplayName().getString().concat(ClientUtils.PotAmplifierValue(i.getAmplifier())).concat(timeSetter(i))).withStyle(i.getEffect().getCategory() == MobEffectCategory.HARMFUL ? ChatFormatting.RED : ChatFormatting.BLUE));
        }
        if (!CraftConfig.TIPPED_INFINITE.get()) {
            int charges = stack.getTag().getInt("AttackCharges");
            int maxCount = CraftConfig.TIPPED_COUNT.get();
            if (charges > 0) {
                event.getToolTip().add(Component.translatable("charges left: " + charges + "/" + maxCount).withStyle(ChatFormatting.BLUE));
            }
        }
    }
	
	private static String timeSetter(MobEffectInstance i) {
		if(i.getEffect().isInstantenous())
			return "";
		int realtime = i.getDuration() / 80;
		return " (" + (realtime / 60) + ":" + String.format("%02d",realtime % 60) + ")";
	}
	
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if(KeyBinding.GLOVE_KEY.consumeClick()) {
        	//TODO: add glove opening
        }
    }
}
