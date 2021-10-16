package net.usernaem.potsnstuff.common.effects;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class StunEffect extends MobEffect{

	public StunEffect() {
		super(MobEffectCategory.NEUTRAL, 3048814);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifyer) {
		addAttributeModifier(Attributes.MOVEMENT_SPEED, "cc432a5f-df59-43f7-962e-6d4f5b53a827", -1.0f, AttributeModifier.Operation.MULTIPLY_TOTAL);
		
	}
	
	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
