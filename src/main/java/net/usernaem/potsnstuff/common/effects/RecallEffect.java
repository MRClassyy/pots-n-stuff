package net.usernaem.potsnstuff.common.effects;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;



import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.LazyOptional;
import net.usernaem.potsnstuff.core.data.MyPotStuffCapability;
import net.usernaem.potsnstuff.core.data.MyPotStuffInterface;

public class RecallEffect extends MobEffect{

	public RecallEffect() {
		super(MobEffectCategory.NEUTRAL, 9332621);
	}

	
	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap p_111185_2_, int p_111185_3_) {
		if(!entity.level.isClientSide) {
			entity.getCapability(MyPotStuffCapability.INSTANCE).ifPresent(props ->{
							props.setDimensionString(entity.level.dimension().toString());
							props.setAnchorPos(entity.position());
						});
		}
	}
	
	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap p_111187_2_, int p_111187_3_) {
		Vec3 oldPos;
		String dimen;
		LazyOptional<MyPotStuffInterface> Cap = entity.getCapability(MyPotStuffCapability.INSTANCE);
		oldPos = Cap.map(MyPotStuffInterface::getAnchorPos).orElse(entity.position());
		dimen = Cap.map(MyPotStuffInterface::getDimensionString).orElse("");
		
		if(!entity.level.isClientSide &&  oldPos != null && dimen != null && entity.level.dimension().toString().equalsIgnoreCase(dimen) && !entity.isPassenger()){
			if(entity instanceof Player) {
				((ServerPlayer)entity).stopRiding();
				((ServerPlayer)entity).teleportTo(oldPos.x, oldPos.y, oldPos.z);
			}else {
				entity.teleportTo(oldPos.x, oldPos.y, oldPos.z);
			}
			entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.NEUTRAL, 1.0F, 1.0f);
		}else {
			if(entity instanceof Player) {
				((ServerPlayer)entity).displayClientMessage(new TextComponent("Can't recall to a different dimension"), true);
				entity.hurt(DamageSource.MAGIC, 1.0f);
			}
		}
	}
	
	//both added as a way to stop recall from triggering
	@Override
	public boolean isDurationEffectTick(int p_76397_1_, int p_76397_2_) {
	return false;
	}
	
	@Override
	public void applyInstantenousEffect(Entity p_180793_1_, Entity p_180793_2_, LivingEntity entity,
			int p_180793_4_, double p_180793_5_) {
		entity.getCapability(MyPotStuffCapability.INSTANCE).ifPresent(p -> {
			p.setDimensionString("");
		});
	}
}
