package net.usernaem.potsnstuff.common.effects;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class FlightEffect extends MobEffect{

	public FlightEffect() {
		super(MobEffectCategory.BENEFICIAL, 12698049);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap p_111185_2_, int p_111185_3_) {
		if(entity instanceof Player) {
			Player player = (Player) entity;
            if (!player.isCreative() && !player.isSpectator()) {
                player.getAbilities().mayfly = true;
                player.onUpdateAbilities();
            }
		}
	}
	
	@Override
	public void applyEffectTick(LivingEntity entity, int p_76394_2_) {
		if(entity instanceof Player) {
			Player player = (Player) entity;
            if (!player.getAbilities().mayfly && !player.isCreative() && !player.isSpectator() ) {
                player.getAbilities().mayfly = true;
                player.onUpdateAbilities();
            }
		}
	}
	
	@Override
	public boolean isDurationEffectTick(int time, int p_76397_2_) {
		if(time>1)
			return true;
		return false;
	}
	
	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap p_111187_2_,
			int p_111187_3_) {

		if(entity instanceof Player) {
			Player player = (Player) entity;
	        if (!player.isCreative() && !player.isSpectator()) {
	            player.getAbilities().flying = false;
	            player.getAbilities().mayfly = false;
	            player.onUpdateAbilities();
	        }
		}
		
		

	}
}
