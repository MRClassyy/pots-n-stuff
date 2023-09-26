package net.usernaem.potsnstuff.common.containers;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.usernaem.potsnstuff.common.items.GloveItem;
import net.usernaem.potsnstuff.common.items.PotionBagItem;
import net.usernaem.potsnstuff.core.init.ConteinerTypeInit;

public class GloveContainer   extends AbstractContainerMenu{

	private final ItemStack item;
    private final IItemHandler itemHandler;
    private int blocked = -1;
    private int gloveSlotsSize = 0;
    

	public GloveContainer(int id, Inventory playerInventory) {
		super(ConteinerTypeInit.GLOVE_ITEM.get(), id);
		 this.item = getHeldItem(playerInventory.player);
	        this.itemHandler = ((GloveItem) this.item.getItem()).getInventory(this.item);
	        // Add backpack slots (3 rows of 9)
	        gloveSlotsSize = ((GloveItem) this.item.getItem()).getInventorySize();
	        //potion segment creation
	        for (int i = 0; i < gloveSlotsSize; ++i) {
		        int x = 8 + 18 * (i % 4);
		        int y = 18 + 18 * (i / 4);
				
	            addSlot(new SlotItemHandler(this.itemHandler, i, x, y));
	        }
	        
	        final int rowCount = this.itemHandler.getSlots() / 4;
	        final int yOffset = (rowCount - 4) * 18;

	        // Player inventory
	        for (int y = 0; y < 3; ++y) {
	            for (int x = 0; x < 9; ++x) {
	                addSlot(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 103 + y * 18 + yOffset));
	            }
	        }

	        // Hotbar
	        for (int x = 0; x < 9; ++x) {
	            Slot slot = addSlot(new Slot(playerInventory, x, 8 + x * 18, 161 + yOffset) {
	                @Override
	                public boolean mayPickup(Player playerIn) {
	                    return index != blocked;
	                }
	            });

	            if (x == playerInventory.selected && ItemStack.matches(playerInventory.getSelected(), this.item)) {
	                blocked = slot.index;
	            }
	        }
	}


    public int getInventoryRows() {
        return this.itemHandler.getSlots() / 4;
    }

	public static ItemStack getHeldItem(Player player) {
        // Determine which held item is a backpack (if either)
        if (isGloveItem(player.getMainHandItem())) {
            return player.getMainHandItem();
        }
        if (isGloveItem(player.getOffhandItem())) {
            return player.getOffhandItem();
        }
        return ItemStack.EMPTY;
    }

    private static boolean isGloveItem(ItemStack stack) {
        return stack.getItem() instanceof GloveItem;
    }


	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		Slot slot = this.getSlot(index);
	       
        if (!slot.mayPickup(playerIn)) {
            return slot.getItem();
        }

        if (index == blocked || !slot.hasItem()) {
            return ItemStack.EMPTY;
        }
        //if not arrows or potions just leave
        if(!(slot.getItem().getItem() instanceof ArrowItem) && !(slot.getItem().getItem() instanceof PotionItem)) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getItem();
        ItemStack newStack = stack.copy();
        int containerSlots = itemHandler.getSlots();
        if (index < containerSlots) {
            if (!this.moveItemStackTo(stack, containerSlots, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
            slot.setChanged();
            
        } else if(stack.getItem() instanceof PotionItem || stack.getItem() instanceof ArrowItem) {
        	if (!this.moveItemStackTo(stack, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (stack.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        slot.onTake(playerIn, newStack);
        return newStack;
	}

	@Override
	public boolean stillValid(Player p_38874_) {
		// TODO Auto-generated method stub
		return true;
	}

    @Override
    public void removed(Player playerIn) {
        super.removed(playerIn);
        
        if(!this.item.hasTag())
        	this.item.setTag(new CompoundTag());
        this.item.getTag().put("inventory", ((ItemStackHandler) this.itemHandler).serializeNBT());
    }

	@Override
    public void clicked(int slotId, int dragType, ClickType clickTypeIn, Player player) {
        if (slotId < 0 || slotId > slots.size()) {
            super.clicked(slotId, dragType, clickTypeIn, player);
            return;
        }

        Slot slot = slots.get(slotId);
        if (!canTake(slotId, slot, dragType, player, clickTypeIn)) {
            return;
        }

        super.clicked(slotId, dragType, clickTypeIn, player);
    }
	
	private boolean canTake(int slotId, Slot slot, int button, Player player, ClickType clickType) {
        if (slotId == blocked || slotId <= itemHandler.getSlots() - 1 && isGloveItem(this.getCarried())) {
            return false;
        }
        if(!filtered(slotId, this.getCarried().getItem())) 
        	return false;

        // Hotbar swapping via number keys
        if (clickType == ClickType.SWAP) {
            int hotbarId = itemHandler.getSlots() + 27 + button;
            // Block swapping with container
            if (blocked == hotbarId) {
                return false;
            }

            Slot hotbarSlot = getSlot(hotbarId);
            if (slotId <= itemHandler.getSlots() - 1) {
                return !isGloveItem(slot.getItem()) && !isGloveItem(hotbarSlot.getItem()) && filtered(slotId, hotbarSlot.getItem().getItem());
            }
        }

        return true;
    }
	
	private boolean filtered(int slotId, Item item) {
    	if(slotId < itemHandler.getSlots()) {
        	if(!(item instanceof PotionItem || item instanceof ArrowItem || item instanceof AirItem))
        		return false;
    	}
    	return true;
    }
}
