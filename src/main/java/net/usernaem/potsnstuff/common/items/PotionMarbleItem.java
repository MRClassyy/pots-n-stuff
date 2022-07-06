package net.usernaem.potsnstuff.common.items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ThrowablePotionItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class PotionMarbleItem extends ThrowablePotionItem{

	public PotionMarbleItem() {
		super(new Properties().stacksTo(16).tab(CreativeModeTab.TAB_BREWING));
	}
	public InteractionResultHolder<ItemStack> use(Level p_77659_1_, Player p_77659_2_, InteractionHand p_77659_3_) {
		  Random random = new Random();
	      p_77659_1_.playSound((Player)null, p_77659_2_.getX(), p_77659_2_.getY(), p_77659_2_.getZ(), SoundEvents.SPLASH_POTION_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
	      p_77659_2_.getCooldowns().addCooldown((ThrowablePotionItem)this, 11);
	      ItemStack itemstack = p_77659_2_.getItemInHand(p_77659_3_);
	      if (!p_77659_1_.isClientSide) {
	         ThrownPotion potionentity = new ThrownPotion(p_77659_1_, p_77659_2_);
	         potionentity.setItem(itemstack);
	         potionentity.shootFromRotation(p_77659_2_, p_77659_2_.getXRot(), p_77659_2_.getYRot(), 0.0F, 2.0F, 1.0F);
	         p_77659_1_.addFreshEntity(potionentity);
	      }

	      p_77659_2_.awardStat(Stats.ITEM_USED.get(this));
	      if (!p_77659_2_.getAbilities().instabuild) {
	         itemstack.shrink(1);
	      }

	      return InteractionResultHolder.sidedSuccess(itemstack, p_77659_1_.isClientSide());
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
