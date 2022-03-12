package net.usernaem.potsnstuff.common.event;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.common.effects.BombEffect;
import net.usernaem.potsnstuff.common.effects.ConversionEffect;
import net.usernaem.potsnstuff.core.data.MyPotStuffProvider;
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

	@SubscribeEvent
    public static void onFallDamage(LivingHurtEvent event) {
           if(event.getEntityLiving().hasEffect(EffectInit.LIGHTFOOT_OBJECT.get())) {
        	   if(event.getSource() == DamageSource.FALL)
        		   event.setCanceled(true);
           }
    }
	
	@SubscribeEvent(priority=EventPriority.LOWEST)
	public static void ConvertDamageEvent(LivingHurtEvent event) {
		if(event.getEntityLiving().hasEffect(EffectInit.CONVERT_OBJECT.get()) && !event.getSource().equals(DamageSource.OUT_OF_WORLD) && !event.getSource().equals(ConversionEffect.ConversionDamageSource)) {
			event.getEntityLiving().setHealth(event.getEntityLiving().getHealth() + event.getAmount());
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent(priority=EventPriority.LOWEST)
	public static void ConvertHealEvent(LivingHealEvent event) {
		if(event.getEntityLiving().hasEffect(EffectInit.CONVERT_OBJECT.get())){
			event.getEntity().hurt(ConversionEffect.ConversionDamageSource, event.getAmount());
			event.setCanceled(true);
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
			if(entity.hasEffect(EffectInit.DBOUND_OBJECT.get())) {
				event.setCanceled(false);
				return;
			}
			int boosted = 1;
			boosted = entity.getEffect(EffectInit.UNDEATH_OBJECT.get()).getAmplifier() + 1;
			entity.setHealth(2 * boosted);
			ArrayList<MobEffectInstance> tmpArrayList = new ArrayList<>(entity.getActiveEffects());
	    	Iterator<MobEffectInstance> iterator = tmpArrayList.iterator();
			while (iterator.hasNext()) {
	            MobEffectInstance e = iterator.next();
				entity.removeEffect(e.getEffect());
				iterator.remove();
			}
			entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, boosted - 1));
			entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0));
			entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0));
			entity.addEffect(new MobEffectInstance(EffectInit.DBOUND_OBJECT.get(),300 * boosted,0));
			entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.TOTEM_USE, SoundSource.NEUTRAL, 1.0F, 1.0f);
			event.setCanceled(true);
		}
	}
	

	
	@SubscribeEvent
	public static void AttachCapabilities(final AttachCapabilitiesEvent<Entity> event) {
		if(event.getObject() instanceof LivingEntity) {
			MyPotStuffProvider provider = new MyPotStuffProvider();
			event.addCapability(MyPotStuffProvider.Identifier, provider);
		}
	}
	
}
