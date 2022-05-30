package net.usernaem.potsnstuff.common.effects;

import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.ItemStack;
import net.usernaem.potsnstuff.core.config.EffectConfig;

public class CorrosionEffect extends InstantenousMobEffect{

	public CorrosionEffect() {
		super(MobEffectCategory.HARMFUL, 9436681);
	}
	
	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		for(ItemStack slot: entity.getArmorSlots()) {
			if(!(slot.getItem() instanceof AirItem))
				slot.hurtAndBreak(EffectConfig.CORROSION_VALUE.get() * (amplifier + 1), entity, (p) -> p.broadcastBreakEvent(LivingEntity.getEquipmentSlotForItem(slot)));
		}
	}
	@Override
	public void applyInstantenousEffect(Entity p_19462_, Entity p_19463_, LivingEntity entity, int amplifier,
			double p_19466_) {
		for(ItemStack slot: entity.getArmorSlots()) {
			if(!(slot.getItem() instanceof AirItem))
				slot.hurtAndBreak(EffectConfig.CORROSION_VALUE.get() * (amplifier + 1), entity, (p) -> p.broadcastBreakEvent(LivingEntity.getEquipmentSlotForItem(slot)));
		}
	}
}
