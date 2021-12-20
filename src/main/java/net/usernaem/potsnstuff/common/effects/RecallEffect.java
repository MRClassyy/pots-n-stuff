package net.usernaem.potsnstuff.common.effects;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.phys.Vec3;

public class RecallEffect extends MobEffect{

	private Vec3 posVector3d = null;
	private String dimensionString = null;
	public RecallEffect() {
		super(MobEffectCategory.NEUTRAL, 9332621);
	}

	
	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap p_111185_2_, int p_111185_3_) {
		posVector3d = entity.position();
		dimensionString = entity.level.dimension().toString();

	}
	
	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap p_111187_2_,
			int p_111187_3_) {
		if(posVector3d != null && dimensionString != null && entity.level.dimension().toString().equalsIgnoreCase(dimensionString) && !entity.isPassenger())
			entity.teleportTo(posVector3d.x, posVector3d.y, posVector3d.z);
	}
	
	//both added as a way to stop recall from triggering
	@Override
	public boolean isDurationEffectTick(int p_76397_1_, int p_76397_2_) {
	return false;
	}
	
	@Override
	public void applyInstantenousEffect(Entity p_180793_1_, Entity p_180793_2_, LivingEntity p_180793_3_,
			int p_180793_4_, double p_180793_5_) {
		dimensionString = null;
	}
	public void terminateRecall() {
		dimensionString = null;
	}
}
