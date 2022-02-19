package net.usernaem.potsnstuff.common.effects;

import java.util.Random;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class DisorientEffect extends MobEffect{

	public DisorientEffect() {
		super(MobEffectCategory.NEUTRAL, 13887840);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplitude) {
		Random random = new Random();
		Vec3 moveVec3 = new Vec3(random.nextDouble(2) - 1, 0, random.nextDouble(2) - 1);
		entity.moveRelative((1 + amplitude) * 0.5f, moveVec3);
	}
	
	@Override
	public boolean isDurationEffectTick(int duration, int p_76397_2_) {
		return duration % 20 == 0;
	}
	
	
}
