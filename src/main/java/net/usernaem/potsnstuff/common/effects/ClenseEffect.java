package net.usernaem.potsnstuff.common.effects;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.usernaem.potsnstuff.core.init.EffectInit;

public class ClenseEffect extends InstantenousMobEffect{

	public ClenseEffect() {
		super(MobEffectCategory.BENEFICIAL, 16646133);
	}
	
	@Override
	public void applyEffectTick(LivingEntity entity, int p_19468_) {
		 if (!entity.level.isClientSide) {
			 if(entity.hasEffect(EffectInit.RECALL_OBJECT.get())) {
				 MobEffect eff =(entity.getEffect(EffectInit.RECALL_OBJECT.get()).getEffect());
				 eff.applyInstantenousEffect(null, null, entity, 0, 0);
			 }
			 entity.removeAllEffects(); 
			 }
	}
	
	@Override
	public void applyInstantenousEffect(Entity p_180793_1_, Entity p_180793_2_, LivingEntity entity,
			int p_180793_4_, double p_180793_5_) {

		 if (!entity.level.isClientSide) {
			 if(entity.hasEffect(EffectInit.RECALL_OBJECT.get())) {
				 MobEffect eff =(entity.getEffect(EffectInit.RECALL_OBJECT.get()).getEffect());
				 eff.applyInstantenousEffect(p_180793_1_, p_180793_2_, entity, p_180793_4_, p_180793_5_);
			 }
			 entity.removeAllEffects(); 
			 }
	}
	
}
