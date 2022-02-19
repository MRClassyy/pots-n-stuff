package net.usernaem.potsnstuff.common.effects;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.InstantenousMobEffect;

public class NullifyEffect extends InstantenousMobEffect{

	public NullifyEffect() {
		super(MobEffectCategory.HARMFUL, 16744429);
	}

	@Override
	public void applyInstantenousEffect(Entity p_180793_1_, Entity p_180793_2_, LivingEntity entity,
			int p_180793_4_, double p_180793_5_) {
		 if (!entity.level.isClientSide) {
			  ArrayList<MobEffectInstance> tmpArrayList = new ArrayList<>(entity.getActiveEffects());
	    	  Iterator<MobEffectInstance> iterator = tmpArrayList.iterator();
			 while (iterator.hasNext()) {
	             MobEffectInstance e = iterator.next();
				 if (e.getEffect().getCategory() == MobEffectCategory.BENEFICIAL) {
					 	 entity.removeEffect(e.getEffect());
					 	 iterator.remove();
		          }
			 }
		 }
	}
}
