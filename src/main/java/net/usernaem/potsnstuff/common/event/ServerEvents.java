package net.usernaem.potsnstuff.common.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.common.effects.BombEffect;
import net.usernaem.potsnstuff.common.effects.ConversionEffect;
import net.usernaem.potsnstuff.common.items.crafting.TippedWeaponRecipe;
import net.usernaem.potsnstuff.core.config.CraftConfig;
import net.usernaem.potsnstuff.core.config.EffectConfig;
import net.usernaem.potsnstuff.core.data.MyPotStuffProvider;
import net.usernaem.potsnstuff.core.init.EffectInit;

@EventBusSubscriber(modid = PotsNStuff.MOD_ID_STRING, bus = Bus.FORGE)
public class ServerEvents {

	
	@SubscribeEvent
    public static void onBeforeDamage(LivingAttackEvent event) {
		if (event.getSource().isProjectile()) {
			if(event.getEntityLiving().hasEffect(EffectInit.REFLECT_OBJECT.get())) {
	           int percent = EffectConfig.ARROW_RESIST.get();
	           if(percent == 100)
	        	   event.setCanceled(true);
	        }
	    }else if (event.getSource().isExplosion() || event.getSource().equals(BombEffect.BombDamageSource)) {
           if(event.getEntityLiving().hasEffect(EffectInit.BLAST_OBJECT.get())) {
        	   int percent = EffectConfig.BLAST_RESIST.get();
        	   if(percent == 100)
        		   event.setCanceled(true);
           }
        }
    }

	@SubscribeEvent
    public static void onTakenDamage(LivingHurtEvent event) {
        if(event.getEntityLiving().hasEffect(EffectInit.FRAIL_OBJECT.get())) {
     	   event.setAmount((float) (event.getAmount() * EffectConfig.FRAIL_MULTIPLY.get()));
        }
		DamageSource dmgSource = event.getSource();
        if(dmgSource == DamageSource.FALL) {
        	if(event.getEntityLiving().hasEffect(EffectInit.LIGHTFOOT_OBJECT.get())) {
        		calculateDamage(EffectConfig.FALL_RESIST.get(), event);
        	}
        }
        else if(dmgSource.isExplosion() || dmgSource.equals(BombEffect.BombDamageSource)) {
           if(event.getEntityLiving().hasEffect(EffectInit.BLAST_OBJECT.get()))
        	   calculateDamage(EffectConfig.BLAST_RESIST.get(), event);
        }
        else if(dmgSource.isProjectile()) {
           if(event.getEntityLiving().hasEffect(EffectInit.REFLECT_OBJECT.get())) {
        	   calculateDamage(EffectConfig.ARROW_RESIST.get(), event);
           }
        }
    }
	
	private static void calculateDamage(int percent, LivingHurtEvent event) {
		if(percent == 100) {
			event.setCanceled(true);
		}else{
			percent = 100 - percent;
			event.setAmount(event.getAmount()/100 * percent);
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
			if(event.getEntityLiving() instanceof Player) {
				if(((Player)event.getEntityLiving()).getFoodData().getFoodLevel() >= 18){
					event.setAmount(event.getAmount() - 1);
				}
			}
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
	public static void onWeaponAttack(final AttackEntityEvent event) {
		Entity entity = event.getTarget();
        Player player = event.getPlayer();
        
        if(player.getAttackStrengthScale(0)< CraftConfig.TIPPED_COOLDOWN.get())
        	return;
        
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity)entity;
            
            if(!TippedWeaponRecipe.isValidWeapon(player.getMainHandItem()))
            	return;
            
            CompoundTag compoundTag = player.getMainHandItem().getTag();
            if(compoundTag != null) {
            	if(!CraftConfig.TIPPED_INFINITE.get()) {
            		int charges = compoundTag.getInt("AttackCharges");
            		
            		if(charges <= 0) {
            			compoundTag.remove("AttackCharges");
            			compoundTag.remove("Potion");
            		} else {
                		addEffects(livingEntity, PotionUtils.getAllEffects(compoundTag.getCompound("Potion")));
            			charges--;
            			if(charges <= 0) {
                			compoundTag.remove("Potion");
                			compoundTag.remove("AttackCharges");
            			}else {
            				compoundTag.putInt("AttackCharges", charges);
            			}
            		}
            	}else
            		addEffects(livingEntity, PotionUtils.getAllEffects(compoundTag.getCompound("Potion")));
            }
        }
        
	}
	public static void addEffects(LivingEntity livingEntity, List<MobEffectInstance> effectList) {
		for(MobEffectInstance e: effectList)
			livingEntity.addEffect(e);
	}

	
	@SubscribeEvent
	public static void AttachCapabilities(final AttachCapabilitiesEvent<Entity> event) {
		if(event.getObject() instanceof LivingEntity) {
			MyPotStuffProvider provider = new MyPotStuffProvider();
			event.addCapability(MyPotStuffProvider.Identifier, provider);
		}
	}
	
}
