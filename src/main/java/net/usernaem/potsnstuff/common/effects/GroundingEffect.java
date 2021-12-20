package net.usernaem.potsnstuff.common.effects;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.phys.Vec3;

public class GroundingEffect extends MobEffect {

	public GroundingEffect() {
		super(MobEffectCategory.NEUTRAL, 7829367);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifyer) { 
		if(!entity.isOnGround()) {
			entity.move(MoverType.SELF, new Vec3(0, -0.2 * (2*(amplifyer + 1)), 0));
		}
	}

	
	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
