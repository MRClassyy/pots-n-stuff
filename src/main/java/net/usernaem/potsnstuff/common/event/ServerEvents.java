package net.usernaem.potsnstuff.common.event;

import java.util.Iterator;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.common.effects.BombEffect;
import net.usernaem.potsnstuff.core.init.EffectInit;

@EventBusSubscriber(modid = PotsNStuff.MOD_ID_STRING, bus = Bus.FORGE)
public class ServerEvents {

	
	@SubscribeEvent
    public static void onEntityDamage(LivingAttackEvent event) {
        if (event.getSource().isProjectile()) {
           if(event.getEntityLiving().hasEffect(EffectInit.REFLECT_OBJECT.get())) {
        	   event.setCanceled(true);
           }
        }
    }
	@SubscribeEvent
    public static void onExplosionDamage(LivingAttackEvent event) {
        if (event.getSource().isExplosion() || event.getSource().equals(BombEffect.BombDamageSource)) {
           if(event.getEntityLiving().hasEffect(EffectInit.BLAST_OBJECT.get())) {
        	   event.setCanceled(true);
           }
        }
    }
	
	@SubscribeEvent
    public static void onModifiedDamage(LivingHurtEvent event) {
           if(event.getEntityLiving().hasEffect(EffectInit.FRAIL_OBJECT.get())) {
        	   event.setAmount((float) (event.getAmount() * 2.0));
           }
    }
	
	@SubscribeEvent(priority=EventPriority.LOWEST)
	public static void onEntityJump(LivingJumpEvent event) {
		if(event.getEntityLiving().hasEffect(EffectInit.GROUNDED_OBJECT.get())) {
			Vec3 entityV3 = event.getEntityLiving().getDeltaMovement();
			if(entityV3.y>0)
				event.getEntityLiving().setDeltaMovement(entityV3.x, 0, entityV3.z);
		}
	}
	
	@SubscribeEvent
	public static void onPotExplode(ExplosionEvent.Detonate e) {
		if(e.getExplosion().getDamageSource().equals(BombEffect.BombDamageSource)) {
			Iterator<Entity> iter= e.getAffectedEntities().iterator();
			while(iter.hasNext()) {
				if(!(iter.next() instanceof LivingEntity) ) {
					iter.remove();
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if(event.getEntityLiving().hasEffect(EffectInit.UNDEATH_OBJECT.get())) {
			LivingEntity entity = event.getEntityLiving();
			if(entity.getEffect(EffectInit.UNDEATH_OBJECT.get()).getAmplifier() == 0)
				entity.removeEffect(EffectInit.UNDEATH_OBJECT.get());
			entity.setHealth(2);
			entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 4));
			entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 400, 3));
			entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 400, 3));
			event.setCanceled(true);
		}
	}
	//LivingAttackEvent
	
}
