package net.usernaem.potsnstuff.common.effects;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.InstantenousMobEffect;

public class HellsTouchEffect extends InstantenousMobEffect{

	public HellsTouchEffect() {
		super(MobEffectCategory.NEUTRAL, 16711680);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplitude) {
		if( !entity.getFeetBlockState().getMaterial().isSolid()) {
			if(entity.getFeetBlockState().is(Blocks.WATER)) {
				entity.level.setBlockAndUpdate(entity.blockPosition(), Blocks.OBSIDIAN.defaultBlockState());
				
			}else {
				entity.level.setBlockAndUpdate(entity.blockPosition(), Blocks.LAVA.defaultBlockState());
				
			}
		}
		
	}
	@Override
	public void applyInstantenousEffect(Entity p_180793_1_, Entity p_180793_2_, LivingEntity entity,
			int amplitude, double p_180793_5_) {
		if( !entity.getFeetBlockState().getMaterial().isSolid()) {
			if(entity.getFeetBlockState().is(Blocks.WATER)) {
				entity.level.setBlockAndUpdate(entity.blockPosition(), Blocks.OBSIDIAN.defaultBlockState());
				
			}else {
				entity.level.setBlockAndUpdate(entity.blockPosition(), Blocks.LAVA.defaultBlockState());
				
			}
		}
	}

}
