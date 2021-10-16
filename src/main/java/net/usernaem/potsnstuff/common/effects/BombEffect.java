package net.usernaem.potsnstuff.common.effects;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.Explosion;

public class BombEffect extends InstantenousMobEffect{

	public static final DamageSource BombDamageSource = new DamageSource("BombDamage");
	public BombEffect() {
		super(MobEffectCategory.HARMFUL, 7033103);
	}
	
	@Override
	public void applyEffectTick(LivingEntity entity, int p_76394_2_) {
		entity.level.explode(null, BombEffect.BombDamageSource, null, entity.getX(), entity.getY()+1.5, entity.getZ(), 3, false, Explosion.BlockInteraction.NONE);
	}
	
	
	@Override
	public void applyInstantenousEffect(Entity p_180793_1_, Entity p_180793_2_, LivingEntity entity,
			int p_180793_4_, double p_180793_5_) {
		entity.level.explode(null, BombEffect.BombDamageSource, null, entity.getX(), entity.getY()+1.5, entity.getZ(), 3, false, Explosion.BlockInteraction.NONE);
	}

}
