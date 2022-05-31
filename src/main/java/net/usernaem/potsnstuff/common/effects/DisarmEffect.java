package net.usernaem.potsnstuff.common.effects;

import java.util.Map;

import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

public class DisarmEffect extends InstantenousMobEffect{

	public DisarmEffect() {
		super(MobEffectCategory.NEUTRAL, 9950844);
	}
	
	@Override
	public void applyEffectTick(LivingEntity entity, int p_19468_) {

		if(!entity.getLevel().isClientSide()) {
			ItemStack itemStack = entity.getMainHandItem();
			if(isCursed(itemStack))
				return;
			if(itemStack != null && itemStack.getItem() != Items.AIR && !cantDrop(entity)){
				if(entity instanceof Player) {
					((Player) entity).drop(itemStack, false);
				}else {
					entity.spawnAtLocation(itemStack).setPickUpDelay(40);
				}
				entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.AIR));
			}
		}
	}

	@Override
	public void applyInstantenousEffect(Entity p_19462_, Entity p_19463_, LivingEntity entity, int p_19465_,
			double p_19466_) {
		if(!entity.getLevel().isClientSide()) {
			ItemStack itemStack = entity.getMainHandItem();
			if(isCursed(itemStack))
				return;
			System.out.println( EnchantmentHelper.getEnchantments(itemStack));
			System.out.println( EnchantmentHelper.getEnchantments(itemStack).get(Enchantments.VANISHING_CURSE));
			if(itemStack != null && itemStack.getItem() != Items.AIR && !cantDrop(entity)){
				if(entity instanceof Player) {
					((Player) entity).drop(itemStack, false);
				}else {
					entity.spawnAtLocation(itemStack).setPickUpDelay(40);
				}
				entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.AIR));
			}
		}
	}
	private boolean cantDrop(LivingEntity entity) {
		if(entity instanceof AbstractVillager)
			return true;
		return false;
		
	}
	
	private boolean isCursed(ItemStack itemStack) {
	  	Map<Enchantment, Integer> enchantments =  EnchantmentHelper.getEnchantments(itemStack);
	  	if(enchantments.get(Enchantments.VANISHING_CURSE) == null && enchantments.get(Enchantments.BINDING_CURSE) == null)
	  		return false;
	  	return true;
		
	}
}
