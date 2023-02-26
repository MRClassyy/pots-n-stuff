package net.usernaem.potsnstuff.common.items;

import java.util.Map;
import java.util.Random;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.ThrowablePotionItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.usernaem.potsnstuff.common.containers.GloveContainer;
import net.usernaem.potsnstuff.common.containers.PotionBagContainer;
import net.usernaem.potsnstuff.core.init.ItemInit;

public class GloveItem extends TieredItem{
	public static final String INVENTORY_KEY = "inventory";
	private int numOfSlots;
	private int slotPointer = 0;
	private final float attackDamage;
	private int useDuration = 32;
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;
	
	public GloveItem(Tier tier, int damage, int numOfSlots, Item.Properties properties) {
		super(tier, properties);
		this.numOfSlots = numOfSlots;
	    this.attackDamage = (float)damage + tier.getAttackDamageBonus();
	    ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
	    this.defaultModifiers = builder.build();
	}  
	
	public int getInventorySize(ItemStack stack) {
		return numOfSlots;
	}
	
	public IItemHandler getInventory(ItemStack stack) {
    	if(stack.isEmpty())
    		return null;    	
    	ItemStackHandler stackHandler = new ItemStackHandler(getInventorySize(stack));
    	stackHandler.deserializeNBT(stack.getOrCreateTag().getCompound(INVENTORY_KEY));
    	 return stackHandler;
    }
    
    public static void saveInventory(ItemStack stack, IItemHandler itemHandler) {
        if (itemHandler instanceof ItemStackHandler && stack.getItem() instanceof GloveItem) {
            stack.getOrCreateTag().put(INVENTORY_KEY, ((ItemStackHandler) itemHandler).serializeNBT());
        }
    }
    
    public ItemStack getHighlightedItem(ItemStack stack) {
    	IItemHandler stackHandler =  this.getInventory(stack);
    	return stackHandler.getStackInSlot(slotPointer);
    }
    
    public void updateHighlightedItem(ItemStack stack, ItemStack item) {
    	ItemStackHandler stackHandler = (ItemStackHandler) this.getInventory(stack);
    	stackHandler.setStackInSlot(slotPointer, item);
    	saveInventory(stack, stackHandler);
    }

	public float getDamage() {
	   return this.attackDamage;
	}
	
	public boolean hurtEnemy(ItemStack p_43278_, LivingEntity p_43279_, LivingEntity p_43280_) {
		return true;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		if(this.isEnchantable(stack) && this.allowedEnchantment(enchantment))
			return allowedEnchantment(enchantment);
		return false;
	}
	
	//check if book given has only allowed enchantments
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		if(!this.isEnchantable(stack))
			return false;
		Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(book);
		for(Enchantment enchantment : map.keySet()) {
			if(!allowedEnchantment(enchantment))
				return false;
		}
		return true;
	}
	
	@Override
	public boolean isEnchantable(ItemStack p_41456_) {
		if(this.getTier() == Tiers.NETHERITE)
			return true;
		return false;
	}
	
	private boolean allowedEnchantment(Enchantment enchantment) {
		if(enchantment == Enchantments.FIRE_ASPECT)
			return true;
		if(enchantment == Enchantments.MULTISHOT)
			return true;
			return false;

	}
	
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot p_43274_) {
	   return p_43274_ == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_43274_);
	}
	
	
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand handIn) {
		
	      ItemStack itemstack = this.getHighlightedItem(player.getItemInHand(handIn));
	      
		  int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.MULTISHOT, player.getItemInHand(handIn));
		  int j = i == 0 ? 1 : 3;
	      if (!level.isClientSide) {
	    	  
	        	if(player.isCrouching()) {
	        		player.openMenu(new SimpleMenuProvider(
	                    (id, playerInventory, plr) -> new GloveContainer(id, playerInventory),
	                    Component.translatable("container.potsnstuff.potion_bag")
	        				));

	        		return new InteractionResultHolder<>(InteractionResult.SUCCESS, player.getItemInHand(handIn));
	        	}else {  
	      	      	if(player.getCooldowns().isOnCooldown(itemstack.getItem()))
	      	      		return InteractionResultHolder.fail(player.getItemInHand(handIn));
	      	      	
   	    	 		if(itemstack.getItem() instanceof ThrowablePotionItem) {
   	    			  Random random = new Random();
   	    			  level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.SPLASH_POTION_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
		   	    	 	for(i = 0; i< j; i++) {
					   	        ThrownPotion potionentity = new ThrownPotion(level, player);
					   	        potionentity.setItem(itemstack);
					   	        potionentity.shootFromRotation(player, player.getXRot(), player.getYRot() + j, 0.0F, 2.0F, 1.0f);
					   	        level.addFreshEntity(potionentity);
		   	    	 		}
			   	        if(!player.getAbilities().instabuild) {
				   	        itemstack.shrink(1);
			   	        	this.updateHighlightedItem(player.getItemInHand(handIn), itemstack);
			   	        }
			   	        if(itemstack.getCount() > 0)
			   	        	player.getCooldowns().addCooldown(itemstack.getItem(), 11);
			   	        
		   	    	 }else if(itemstack.getItem() instanceof PotionItem) {
		   	    		this.useDuration = itemstack.getItem().getUseDuration(itemstack);
		   	    	 	return ItemUtils.startUsingInstantly(level, player, handIn);
		   	    	 	
		   	    	 }
	        	}
	    	  
	    	
	      }

	      return InteractionResultHolder.fail(player.getItemInHand(handIn));
	   }
	
	   public int getUseDuration(ItemStack p_42933_) {
		      return this.useDuration;
		   }
	   
	   public UseAnim getUseAnimation(ItemStack p_42931_) {
	      return UseAnim.DRINK;
	   }
	   
	   public ItemStack finishUsingItem(ItemStack iStack, Level level, LivingEntity livingEntity) {
		      Player playerentity = livingEntity instanceof Player ? (Player)livingEntity : null;
		      ItemStack itemstack = this.getHighlightedItem(iStack);
		      System.out.println(itemstack.getItem());
		      /*if (playerentity instanceof ServerPlayer) {
		         CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)playerentity, itemStack);
		      }*/

		      if (!level.isClientSide) {
		         for(MobEffectInstance effectinstance : PotionUtils.getMobEffects(itemstack)) {
		            if (effectinstance.getEffect().isInstantenous()) {
		               effectinstance.getEffect().applyInstantenousEffect(playerentity, playerentity, livingEntity, effectinstance.getAmplifier(), 1.0D);
		            } else {
		               livingEntity.addEffect(new MobEffectInstance(effectinstance));
		            }
		         }
		         Random random = new Random();
		         level.playSound((Player)null, playerentity.getX(), playerentity.getY(), playerentity.getZ(), null, SoundSource.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
		         
		         if(this.useDuration < 15) //if the duration is half the regular potion duration, add cooldown
		        	 playerentity.getCooldowns().addCooldown(itemstack.getItem(), 20);
		      }

		      if (playerentity != null) {
		         playerentity.awardStat(Stats.ITEM_USED.get(itemstack.getItem()));
		         if (!playerentity.getAbilities().instabuild) {
		            itemstack.shrink(1);
	   	        	this.updateHighlightedItem(iStack, itemstack);
	   	        	
			        /*if(!playerentity.getInventory().add(new ItemStack(ItemInit.EMPTYVIAL_OBJECT.get())))
			        	playerentity.drop(new ItemStack(ItemInit.EMPTYVIAL_OBJECT.get()), false);*/
		         }
		      }
		      
		      return iStack;
		   }
}
