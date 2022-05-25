package net.usernaem.potsnstuff.common.items;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.usernaem.potsnstuff.common.containers.PotionBagContainer;
import net.usernaem.potsnstuff.core.init.BlockInit;


public class PotionBagItem extends BlockItem{
	public static final String INVENTORY_KEY = "inventory";
	public PotionBagItem() {
		super(BlockInit.POTION_BAG_BLOCK.get(),new Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC));
	}
	
	public int getInventorySize(ItemStack stack) {
		return 24;
	}

    public IItemHandler getInventory(ItemStack stack) {
    	if(stack.isEmpty())
    		return null;    	
    	ItemStackHandler stackHandler = new ItemStackHandler(getInventorySize(stack));
    	stackHandler.deserializeNBT(stack.getOrCreateTag().getCompound(INVENTORY_KEY));
    	 return stackHandler;
    }
    
    public static void saveInventory(ItemStack stack, IItemHandler itemHandler) {
        if (itemHandler instanceof ItemStackHandler && stack.getItem() instanceof PotionBagItem) {
            stack.getOrCreateTag().put(INVENTORY_KEY, ((ItemStackHandler) itemHandler).serializeNBT());
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
    	Player player = context.getPlayer();
    	Level level = context.getLevel();
    	if(!level.isClientSide && player.isCrouching()) {
	    	return super.useOn(context);
    	}
	    return InteractionResult.PASS;
    }
    
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        if (!worldIn.isClientSide) {
        	if(!playerIn.isCrouching()) {
        	      	playerIn.openMenu(new SimpleMenuProvider(
                    (id, playerInventory, player) -> new PotionBagContainer(id, playerInventory),
                    new TranslatableComponent("container.potsnstuff.potion_bag")
            ));
        	}
        }
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, playerIn.getItemInHand(handIn));
    }
    
    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
    	super.appendHoverText(itemStack, level, list, flag);
    	if(itemStack.getTag() != null &&  itemStack.getTag().contains(INVENTORY_KEY) && Screen.hasShiftDown()) {
    		CompoundTag compoundtag = itemStack.getTag().getCompound(INVENTORY_KEY);
    		NonNullList<ItemStack> nonnulllist = NonNullList.withSize(getInventorySize(itemStack), ItemStack.EMPTY);
            ContainerHelper.loadAllItems(compoundtag, nonnulllist);
            int i = 0;
            int j = 0;

            for(ItemStack itemstack : nonnulllist) {
               if (!itemstack.isEmpty()) {
                  ++j;
                  if (i <= 4) {
                     ++i;
                     MutableComponent mutablecomponent = itemstack.getHoverName().copy();
                     mutablecomponent.append(" x").append(String.valueOf(itemstack.getCount()));
                     list.add(mutablecomponent);
                  }
               }
            }

            if (j - i > 0) {
               list.add((new TranslatableComponent("container.shulkerBox.more", j - i)).withStyle(ChatFormatting.ITALIC));
            }
    	}
    }
}
