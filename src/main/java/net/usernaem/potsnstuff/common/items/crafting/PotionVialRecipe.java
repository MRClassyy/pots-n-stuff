package net.usernaem.potsnstuff.common.items.crafting;

import com.google.gson.JsonObject;

import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.core.init.ItemInit;
import net.usernaem.potsnstuff.core.init.RecepiesInit;

public class PotionVialRecipe extends CustomRecipe{
	public static final String NAME = PotsNStuff.MOD_ID_STRING;
	public PotionVialRecipe(ResourceLocation p_i48169_1_) {
		super(p_i48169_1_);
	}

	
	public boolean matches(CraftingContainer inventory, Level world) {
		int width = inventory.getWidth();
		int height = inventory.getHeight();
		if(width == 3 && height == 3) {
			for(int i = 0; i < width; i++) {
				for( int j = 0; j < height; j++) {
					ItemStack itemStack = inventory.getItem(i + j * width);
					if((i == 0 && j == 0) || (i == 0 && j == 2) || (i == 2 && j == 0) || (i == 2 && j == 2)) {
						if(!itemStack.isEmpty())
							return false;
					}
					Item item = itemStack.getItem();
					if(i == 1 && j == 1) {
						if(item != Items.POTION)
							return false;
					}
					if((i == 0 && j == 1) || (i == 1 && j == 0) || (i == 2 && j == 1) || (i == 1 && j == 2)) {
						if(item != ItemInit.EMPTYVIAL_OBJECT.get()) {
							return false;
						}
					}
					
				}
			}
			return true;
		}else
			return false;
	}
	
	public ItemStack assemble(CraftingContainer inventory) {
		ItemStack itemStack = inventory.getItem(1 + inventory.getWidth());
		if(itemStack.getItem() != Items.POTION) {
			return ItemStack.EMPTY;
		}else {
			ItemStack itemstack1 = new ItemStack(ItemInit.POTVIAL_OBJECT.get(), 4);
	         PotionUtils.setPotion(itemstack1, PotionUtils.getPotion(itemStack));
	         PotionUtils.setCustomEffects(itemstack1, PotionUtils.getCustomEffects(itemStack));
	         return itemstack1;
		}	
	}
	
   public boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_) {
      return p_194133_1_ >= 2 && p_194133_2_ >= 2;
   }
	public RecipeSerializer<?> getSerializer() {
      return RecepiesInit.POTION_VIAL_OBJECT.get();
   }
	   
   public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<PotionVialRecipe>{
		@Override
		public PotionVialRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			return new PotionVialRecipe(recipeId);
		}
	
		@Override
		public PotionVialRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
			return new PotionVialRecipe(recipeId);
		}
	
		@Override
		public void toNetwork(FriendlyByteBuf buffer, PotionVialRecipe recipe) {
			
				
		}
		   
		   
		   
   }
	     
	   
}
