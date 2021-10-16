package net.usernaem.potsnstuff.common.effects;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.core.BlockPos;

public class PhotosynthesisEffect extends MobEffect{

	public PhotosynthesisEffect() {
		super(MobEffectCategory.BENEFICIAL, 7582980);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplify) {
		if(!entity.level.isClientSide && entity instanceof Player && isSunBathing(entity)) {
			((Player)entity).getFoodData().eat(amplify + 1, 1);
		}
		
	}
	
	@Override
	public boolean isDurationEffectTick(int duration, int p_76397_2_) {
		
		return duration % 60 == 0;
	}
	
	private boolean isSunBathing(LivingEntity entity) {
		if(entity.level.isDay() && !entity.level.isRaining()) {
			float light = entity.getBrightness();
			BlockPos bPos = new BlockPos(entity.getX(), Math.round(entity.getY()),entity.getZ());
			bPos = entity.getVehicle() instanceof Boat ? bPos.above() : bPos;
			if(light > 0.5f && entity.level.canSeeSky(bPos)) {
				return true;
			}
		}
		return false;
	}
}
