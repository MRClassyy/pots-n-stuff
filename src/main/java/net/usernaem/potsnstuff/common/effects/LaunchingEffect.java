package net.usernaem.potsnstuff.common.effects;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.phys.Vec3;

public class LaunchingEffect extends InstantenousMobEffect{
	public LaunchingEffect() {
		super(MobEffectCategory.NEUTRAL, 11974326);
	}
	
	@Override
	public void applyEffectTick(LivingEntity entity, int amplifyer) {
		Vec3 move3d = entity.getDeltaMovement();
		float speed = entity.getMaxHealth();
		float maxWeight = 20.0f * (amplifyer + 1);	//effect strength is affected by amount of health the entity has
		if(speed < 1)
			speed = 1.0f;
		if(speed > maxWeight)
			speed = maxWeight/speed;	//past the threshold, the more health the weaker the effect is
		else 
			speed = 1;
		if(entity instanceof Player)
			entity.hurtMarked = true;
		entity.setDeltaMovement(new Vec3(move3d.x, (1.5*(1 + (0.55f * amplifyer)))*speed, move3d.z));	
	}
	
	@Override
	public void applyInstantenousEffect(Entity p_180793_1_, Entity p_180793_2_, LivingEntity entity,
			int amplifyer, double p_180793_5_) {
		Vec3 move3d = entity.getDeltaMovement();
		float speed = entity.getMaxHealth();
		float maxWeight = 20.0f * (amplifyer + 1);	//effect strength is affected by amount of health the entity has
		if(speed < 1)
			speed = 1.0f;
		if(speed > maxWeight)
			speed = maxWeight/speed;	//past the threshold, the more health the weaker the effect is
		else 
			speed = 1;
		if(entity instanceof Player)
			entity.hurtMarked = true;
		entity.setDeltaMovement(new Vec3(move3d.x, (1.5*(1 + (0.55f * amplifyer)))*speed, move3d.z));
		
	}
	
}
