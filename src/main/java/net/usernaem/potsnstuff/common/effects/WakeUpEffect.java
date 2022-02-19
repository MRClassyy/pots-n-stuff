package net.usernaem.potsnstuff.common.effects;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class WakeUpEffect extends InstantenousMobEffect{

	public WakeUpEffect() {
		super(MobEffectCategory.NEUTRAL, 9031664);
	}
	
	@Override
	public void applyInstantenousEffect(Entity p_180793_1_, Entity p_180793_2_, LivingEntity entity,
			int p_180793_4_, double p_180793_5_) {
		if (!entity.level.isClientSide && entity instanceof Player) {
			ServerPlayer player = (ServerPlayer)entity;
			BlockPos position = player.getRespawnPosition();
			if(position != null) {
				player.teleportTo(player.getServer().getLevel(player.getRespawnDimension()), position.getX() + 0.5f, position.getY(), position.getZ() + 0.5f,  player.getRespawnAngle(), 0);
			}else {
           	 player.displayClientMessage(new TextComponent("You need a bed in order to wake up"), true);
			}
		}
	}
}
