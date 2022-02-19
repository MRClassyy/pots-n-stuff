package net.usernaem.potsnstuff.common.effects;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.InstantenousMobEffect;

public class AquaEffect extends InstantenousMobEffect{

	public AquaEffect() {
		super(MobEffectCategory.NEUTRAL, 65535);
	}
		
	@Override
	public void applyInstantenousEffect(Entity p_180793_1_, Entity p_180793_2_, LivingEntity entity,
			int p_180793_4_, double p_180793_5_) {
		if(!entity.level.dimensionType().ultraWarm() && !entity.getFeetBlockState().getMaterial().isSolid()) {			
			entity.level.setBlockAndUpdate(entity.blockPosition(), Blocks.WATER.defaultBlockState());
		}
	}
}
