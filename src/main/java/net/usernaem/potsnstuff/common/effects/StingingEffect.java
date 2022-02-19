package net.usernaem.potsnstuff.common.effects;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.phys.Vec3;
import net.usernaem.potsnstuff.core.data.MyPotStuffCapability;

public class StingingEffect extends MobEffect{

	public StingingEffect() {
		super(MobEffectCategory.HARMFUL, 9707828);
	}
	
	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap p_111185_2_, int p_111185_3_) {
		if(!entity.level.isClientSide) {
			entity.getCapability(MyPotStuffCapability.INSTANCE).ifPresent(p->{
				p.setOldPos(entity.position());
			});
		}
		
	}
	
	@Override
	public void applyEffectTick(LivingEntity entity, int amplify) {
		entity.makeStuckInBlock(null, Vec3.ZERO);
		if(!entity.level.isClientSide) {
			
			entity.getCapability(MyPotStuffCapability.INSTANCE).ifPresent(p -> {
				if(!p.compareOldPos(entity.position())) {
					entity.hurt(DamageSource.MAGIC, amplify + 1);
					p.setOldPos(entity.position());
				}
			});
		}
	}

	@Override
	public boolean isDurationEffectTick(int duration, int p_76397_2_) {
		return true;
	}
}
