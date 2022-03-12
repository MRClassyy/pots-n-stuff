package net.usernaem.potsnstuff.common.effects;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.level.Explosion;

public class FuzeEffect extends MobEffect{

	public FuzeEffect() {
		super(MobEffectCategory.HARMFUL, 7033103);
	}
	
	@Override
	public void applyEffectTick(LivingEntity entity, int amplitude) {
		entity.level.explode(null, BombEffect.BombDamageSource, null, entity.getX(), entity.getY()+1.5, entity.getZ(), 1 + amplitude * 0.5f, false, Explosion.BlockInteraction.NONE);

	}
	
	@Override
	public boolean isDurationEffectTick(int duration, int p_76397_2_) {
		return duration == 1;
	}
}
