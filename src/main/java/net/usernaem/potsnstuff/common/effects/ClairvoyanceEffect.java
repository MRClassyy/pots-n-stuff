package net.usernaem.potsnstuff.common.effects;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ClairvoyanceEffect extends MobEffect{
	
	//private Boolean clearall= false;
	public ClairvoyanceEffect() {
		super(MobEffectCategory.BENEFICIAL, 8421504);
	}

	
	@Override
	public void applyEffectTick(LivingEntity entity, int amplification) {
		/*if(entity.level.isClientSide) {
			List<Entity> entities = getEntities(entity.level, entity.getX(), entity.getY(), entity.getZ(), 25);
			List<Entity> ClearEntities = getEntities(entity.level, entity.getX(), entity.getY(), entity.getZ(), 70);
			for(Entity e: ClearEntities) {
				if(e instanceof LivingEntity) {
					if((e.isGlowing())) {
						e.setGlowing(false);
					}
				}
			}
			for(Entity e: entities) {
				if(e instanceof LivingEntity) {
					if(!clearall && (e != (Minecraft.getInstance().player))) {
						e.setGlowing(true);
					}
				}
			}
		}
	}

	public List<Entity> getEntities(Level world, double d, double e,double f, float r) {
	    return world.getEntities(null,  new AABB(d - r, e - r, f - r, d + r, e + r, f + r));
	}
	
	
	
	@Override
	public boolean isDurationEffectTick(int time, int p_76397_2_) {
		if(time<=1)
			clearall = true;
		else 
			clearall = false;
		return time % 20 == 0 || time == 1;*/
	}
	
	
}
