package net.usernaem.potsnstuff.common.items;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.usernaem.potsnstuff.common.containers.PotionBagContainer;


public class PotionBagItem extends Item{

	public PotionBagItem() {
		super(new Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC));
	}
	
	public int getInventorySize(ItemStack stack) {
		return 24;
	}

    public IItemHandler getInventory(ItemStack stack) {
    	if(stack.isEmpty())
    		return null;
    	ItemStackHandler stackHandler = new ItemStackHandler(getInventorySize(stack));
    	stackHandler.deserializeNBT(stack.getOrCreateTag().getCompound("inventory"));
    	 return stackHandler;
    }
    
    /*public void saveInventory(ItemStack stack, IItemHandler itemHandler) {
        if (itemHandler instanceof ItemStackHandler) {
            stack.getOrCreateTag().put("inv", ((ItemStackHandler) itemHandler).serializeNBT());
        }
    }*/

    
    
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        if (!worldIn.isClientSide) {
        	
            playerIn.openMenu(new SimpleMenuProvider(
                    (id, playerInventory, player) -> new PotionBagContainer(id, playerInventory),
                    new TranslatableComponent("container.potsnstuff.potion_bag")
            ));
        }
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, playerIn.getItemInHand(handIn));
    }
}
