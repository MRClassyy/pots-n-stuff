package net.usernaem.potsnstuff.common.potions;

import javax.annotation.Nullable;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class PotionRecipeSetup {
	
	public static void addPotionRecipes(Potion basePotion,Item baseItem, Potion mainPotion,@Nullable Potion longPotion,@Nullable Potion strongPotion){
        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(basePotion, baseItem, mainPotion));
		if(longPotion != null)
			BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(mainPotion, Items.REDSTONE, longPotion));
		if(strongPotion != null)
			BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(mainPotion, Items.GLOWSTONE_DUST, strongPotion));

    }
	
	private static class BetterBrewingRecipe extends BrewingRecipe implements IBrewingRecipe{
        private final Potion bottleInput;
        private final Item itemInput;
        private final ItemStack output;

        public BetterBrewingRecipe(Potion bottleInputIn, Item itemInputIn, Potion outputIn){
        	super(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), bottleInputIn)), Ingredient.of(itemInputIn), PotionUtils.setPotion(new ItemStack(Items.POTION), outputIn));
            this.bottleInput = bottleInputIn;
            this.itemInput = itemInputIn;
            this.output = PotionUtils.setPotion(new ItemStack(Items.POTION), outputIn);
        }

        // checks the item where the water bottle would go
        @Override
        public boolean isInput(ItemStack input) {
            return PotionUtils.getPotion(input).equals(this.bottleInput);
        }

        // checks the item where the nether wort would go
        @Override
        public boolean isIngredient(ItemStack ingredient) {
            return ingredient.getItem().equals(this.itemInput);
        }

        // gets the output potion. Very important to call copy because ItemStacks are mutable
        @Override
        public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
            if (isInput(input) && isIngredient(ingredient)){
                return this.output.copy();
            } else {
                return ItemStack.EMPTY;
            }
        }
 }
	
}
