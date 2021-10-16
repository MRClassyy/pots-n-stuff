package net.usernaem.potsnstuff.common.effects;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.InstantenousMobEffect;

public class LightningEffect extends InstantenousMobEffect{

	public LightningEffect() {
		super(MobEffectCategory.HARMFUL, 1118481);
	}
	@Override
	public void applyEffectTick(LivingEntity entity, int p_76394_2_) {
		LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, entity.level);
        lightning.setPos(entity.getX(), entity.getY(), entity.getZ());
        entity.level.addFreshEntity(lightning);

	}
	
	@Override
	public void applyInstantenousEffect(Entity p_180793_1_, Entity p_180793_2_, LivingEntity entity,
			int p_180793_4_, double p_180793_5_) {
		LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, entity.level);
        lightning.setPos(entity.getX(), entity.getY(), entity.getZ());
        entity.level.addFreshEntity(lightning);
	}
	
}
