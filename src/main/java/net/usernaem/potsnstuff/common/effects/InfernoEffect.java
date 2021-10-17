package net.usernaem.potsnstuff.common.effects;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;

public class InfernoEffect extends MobEffect{

	public InfernoEffect() {
		super(MobEffectCategory.HARMFUL, 16038940);
	}
	
	@Override
	public void applyEffectTick(LivingEntity entity, int amplify) {
		if(amplify != 0) {
			entity.removeEffect(MobEffects.FIRE_RESISTANCE);
		}
		if(!entity.isOnFire() && !entity.isInWaterRainOrBubble()) {
			entity.setSecondsOnFire(5);
		}
	}

	@Override
	public boolean isDurationEffectTick(int duration, int p_76397_2_) {
		return true;
	}
}
