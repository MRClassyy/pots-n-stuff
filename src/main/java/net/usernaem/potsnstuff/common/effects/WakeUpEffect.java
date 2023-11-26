package net.usernaem.potsnstuff.common.effects;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import net.usernaem.potsnstuff.core.data.MyPotStuffCapability;

public class WakeUpEffect extends MobEffect{

	public WakeUpEffect() {
		super(MobEffectCategory.NEUTRAL, 9031664);
	}
	
	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap p_111185_2_, int p_111185_3_) {
		if(!entity.level.isClientSide) {
			entity.getCapability(MyPotStuffCapability.INSTANCE).ifPresent(p->{
				p.setSleepPos(entity.position());
				p.setCanSleep(true);
			});
		}
		
	}
	
	@Override
	public void applyEffectTick(LivingEntity entity, int p_19468_) {
		if (!entity.level.isClientSide && entity instanceof Player) {
			ServerPlayer player = (ServerPlayer)entity;
			entity.getCapability(MyPotStuffCapability.INSTANCE).ifPresent(p -> {
				if(!p.compareSleepPos(entity.position())) {
					player.displayClientMessage(Component.translatable("effect.potsnstuff.wakeup_moved"), true);
					p.setCanSleep(false);
				}
			});
		}
	}
	
	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap p_19470_, int p_19471_) {
		if (!entity.level.isClientSide && entity instanceof Player) {
			ServerPlayer player = (ServerPlayer)entity;
			entity.getCapability(MyPotStuffCapability.INSTANCE).ifPresent(p -> {
				BlockPos position = player.getRespawnPosition();
				if(position != null) {
					if(p.getCanSleep())
						player.teleportTo(player.getServer().getLevel(player.getRespawnDimension()), position.getX() + 0.5f, position.getY(), position.getZ() + 0.5f,  player.getRespawnAngle(), 0);
				}else {
		           	 player.displayClientMessage(Component.translatable("effect.potsnstuff.wakeup_no_bed"), true);
				}
			});
		}		
	}
	

	@Override
	public boolean isDurationEffectTick(int duration, int p_76397_2_) {
		return true;
	}
}
