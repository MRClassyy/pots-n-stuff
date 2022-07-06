package net.usernaem.potsnstuff.common.items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.stats.Stats;
import net.minecraft.sounds.SoundSource;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.usernaem.potsnstuff.core.init.ItemInit;


public class PotionVialItem extends PotionItem{

	public PotionVialItem() {
		super(new Properties().stacksTo(16).tab(CreativeModeTab.TAB_BREWING));
	}

	   public int getUseDuration(ItemStack p_77626_1_) {
	      return 10;
	   }

	   public UseAnim getUseAnimation(ItemStack p_77661_1_) {
	      return UseAnim.DRINK;
	   }
	   
	   public ItemStack finishUsingItem(ItemStack p_77654_1_, Level p_77654_2_, LivingEntity p_77654_3_) {
		      Player playerentity = p_77654_3_ instanceof Player ? (Player)p_77654_3_ : null;
		      
		      if (playerentity instanceof ServerPlayer) {
		         CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)playerentity, p_77654_1_);
		      }

		      if (!p_77654_2_.isClientSide) {
		         for(MobEffectInstance effectinstance : PotionUtils.getMobEffects(p_77654_1_)) {
		            if (effectinstance.getEffect().isInstantenous()) {
		               effectinstance.getEffect().applyInstantenousEffect(playerentity, playerentity, p_77654_3_, effectinstance.getAmplifier(), 1.0D);
		            } else {
		               p_77654_3_.addEffect(new MobEffectInstance(effectinstance));
		            }
		         }
		         Random random = new Random();
		         p_77654_2_.playSound((Player)null, playerentity.getX(), playerentity.getY(), playerentity.getZ(), null/*SoundEvents.WITCH_DRINK*/, SoundSource.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
		         
		        playerentity.getCooldowns().addCooldown(p_77654_1_.getItem(), 20);
		      }

		      if (playerentity != null) {
		         playerentity.awardStat(Stats.ITEM_USED.get(this));
		         if (!playerentity.getAbilities().instabuild) {
		            p_77654_1_.shrink(1);
		         }
		      }

		      if (playerentity == null || !playerentity.getAbilities().instabuild) {
		         if (p_77654_1_.isEmpty()) {
		            return new ItemStack(ItemInit.EMPTYVIAL_OBJECT.get());
		         }

		         if (playerentity != null) {
		            if(!playerentity.getInventory().add(new ItemStack(ItemInit.EMPTYVIAL_OBJECT.get())))
		            	playerentity.drop(new ItemStack(ItemInit.EMPTYVIAL_OBJECT.get()), false);
		         }
		      }
		      return p_77654_1_;
		   }

	   @Override
	public Component getName(ItemStack p_200295_1_) {
		  return Component.translatable(getDescriptionId(), Component.translatable(PotionUtils.getPotion(p_200295_1_).getName("item.minecraft.potion.effect.")));
		//return super.getName(p_200295_1_);   
		      //return new TranslationTextComponent(this.getDescriptionId(p_200295_1_));
	}
	   @OnlyIn(Dist.CLIENT)
	   public void appendHoverText(ItemStack p_77624_1_, @Nullable Level p_77624_2_, List<Component> p_77624_3_, TooltipFlag p_77624_4_) {
	      PotionUtils.addPotionTooltip(p_77624_1_, p_77624_3_, 1.0F);
	   }
	
	 
	   
	   
	   public static int getVialColor(ItemStack stack) {
		   return PotionUtils.getColor(stack);
		   
	   }

	   public static int getItemColor(ItemStack stack, int tintIndex) {
		   if(tintIndex == 0) {
			   return getVialColor(stack);
		   }
		   return -1;
	   }
	   
	   
	   
	   
}
