package net.usernaem.potsnstuff.common.items.crafting;

import com.google.gson.JsonObject;
import java.util.List;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.core.config.CraftConfig;
import net.usernaem.potsnstuff.core.init.RecepiesInit;

public class TippedWeaponRecipe  extends CustomRecipe{
	public static final String NAME = PotsNStuff.MOD_ID_STRING;
	public TippedWeaponRecipe(ResourceLocation p_i48169_1_) {
		super(p_i48169_1_);
	}
	@Override
	public boolean matches(CraftingContainer inventory, Level level) {
		ItemStack itemstack = ItemStack.EMPTY;
	    ItemStack itemstack1 = ItemStack.EMPTY;
	    int size = inventory.getContainerSize();
	    for(int i = 0; i < size; ++i) {
	       ItemStack itemstack2 = inventory.getItem(i);
	       if (!itemstack2.isEmpty()) {
	          if (isValidWeapon(itemstack2)) {
	             if (!itemstack1.isEmpty()) {
	                return false;
	             }

	             itemstack1 = itemstack2;
	          } else {
	             if (!itemstack2.is(Items.LINGERING_POTION) && !itemstack2.is(Items.POTION)) {
	                return false;
	             }

	             if (!itemstack.isEmpty()) {
	                return false;
	             }

	             itemstack = itemstack2;
	          }
	       }
	    }
	    return !itemstack.isEmpty() && !itemstack1.isEmpty();
	}
	
	@Override
	public ItemStack assemble(CraftingContainer inventory) {
		if(!(CraftConfig.CRAFT_TIPPED.get()))
			return ItemStack.EMPTY;
		ItemStack itemstack = ItemStack.EMPTY;
		ItemStack itemstack1 = ItemStack.EMPTY;
		int size = inventory.getContainerSize();
		for(int i = 0; i < size; ++i) {
			ItemStack itemstack2 = inventory.getItem(i);
			if (!itemstack2.isEmpty()) {
				if (isValidWeapon(itemstack2)) {
					itemstack1 = itemstack2.copy();
				} else if (itemstack2.is(Items.LINGERING_POTION) || itemstack2.is(Items.POTION)) {
					itemstack = itemstack2;
				}
			}
		}
        final CompoundTag weapon = itemstack1.getTag();
        final CompoundTag potion = itemstack.getTag();
		if(weapon == null || potion == null)
			return ItemStack.EMPTY;
		
		if(!itemstack.getTag().getAllKeys().isEmpty()) {
			List<MobEffectInstance> effectsList =  PotionUtils.getMobEffects(itemstack);
			if(!effectsList.isEmpty()) {
				//only lingering potions can have effects, only normal potions allowed are water bottles
				if(itemstack.is(Items.POTION))
					return ItemStack.EMPTY;
				if(!CraftConfig.TIPPED_HARM.get()) {
					for(MobEffectInstance e: effectsList) {
						MobEffect effect = e.getEffect();
						if(effect.isInstantenous() && effect.getCategory() == MobEffectCategory.HARMFUL)
							return ItemStack.EMPTY;
					}
				}
				
				final ListTag listTag = new ListTag();
				for(MobEffectInstance e: effectsList) {
                    final CompoundTag compoundTag = new CompoundTag();
                    compoundTag.putInt("Duration", e.getDuration());
                    compoundTag.putInt("Amplifier", e.getAmplifier());
                    compoundTag.putInt("Id", MobEffect.getId(e.getEffect()));
                    listTag.add(compoundTag);
					
                    potion.put("CustomPotionEffects", listTag);
                    potion.putString("Potion", "");
                    weapon.put("Potion", potion);
                    if(!CraftConfig.TIPPED_INFINITE.get()) {
                    	weapon.putInt("AttackCharges", CraftConfig.TIPPED_COUNT.get());
                    }
				}
			}else {
				weapon.remove("Potion");
				weapon.remove("AttackCharges");
				//weapon.putInt("AttackCharges", 0);
			}
		}
		return itemstack1;
	}
	
	public static boolean isValidWeapon(ItemStack itemStack) {
		return itemStack.getItem() instanceof TieredItem && !(itemStack.getItem() instanceof HoeItem);
	}
	
	@Override
	public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
	      return p_43999_ * p_44000_ >= 2;
	}
	
	@Override
	public RecipeSerializer<?> getSerializer() {
		return RecepiesInit.TIPPED_WEAPON_OBJECT.get();
	}
	public static class Serializer implements RecipeSerializer<TippedWeaponRecipe>{
		@Override
		public TippedWeaponRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			return new TippedWeaponRecipe(recipeId);
		}
	
		@Override
		public TippedWeaponRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
			return new TippedWeaponRecipe(recipeId);
		}
	
		@Override
		public void toNetwork(FriendlyByteBuf buffer, TippedWeaponRecipe recipe) {
			
				
		}
		   
	}
}