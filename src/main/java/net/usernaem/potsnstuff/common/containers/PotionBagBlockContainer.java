package net.usernaem.potsnstuff.common.containers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.usernaem.potsnstuff.common.blocks.entity.PotionBagBlockEntity;
import net.usernaem.potsnstuff.common.items.PotionBagItem;
import net.usernaem.potsnstuff.core.init.BlockInit;
import net.usernaem.potsnstuff.core.init.ConteinerTypeInit;

public class PotionBagBlockContainer extends AbstractContainerMenu{
	//private static PotionBagBlockEntity blockEntity;
	private final ContainerLevelAccess containerLevelAccess;
    private final IItemHandler itemHandler;
    private int potSegmentSize = 0;
    private int arrowSegmentSize = 0;

	//client constructor
	public PotionBagBlockContainer(int id, Inventory playerInventory) {
		this(id, playerInventory, new ItemStackHandler(27), BlockPos.ZERO);
	}

	//server constructor
	public PotionBagBlockContainer(int id, Inventory playerInventory, IItemHandler slots, BlockPos pos) {
		super(ConteinerTypeInit.POTION_BAG_BLOCK.get(), id);
		this.containerLevelAccess = ContainerLevelAccess.create(playerInventory.player.level, pos);
		this.itemHandler = slots;
		
		potSegmentSize = 18;
	    arrowSegmentSize = 6;
	    //potion segment creation
	    for (int i = 0; i < potSegmentSize; ++i) {
		    int x = 8 + 18 * (i % 6);
		    int y = 18 + 18 * (i / 6);
		    addSlot(new SlotItemHandler(slots, i, x, y));
	    }

	    //arrow segment creation
	    for (int i = 0; i < arrowSegmentSize; ++i) {
		    int x = 134 + 18 * (i % 2);
		    int y = 18 + 18 * (i / 2);
				
	        addSlot(new SlotItemHandler(slots, potSegmentSize + i, x, y));
	    }
		
	    final int rowCount = slots.getSlots() / 8;
        final int yOffset = (rowCount - 4) * 18;

        // Player inventory
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                addSlot(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 103 + y * 18 + yOffset));
            }
        }

        // Hotbar
        for (int x = 0; x < 9; ++x) {
            addSlot(new Slot(playerInventory, x, 8 + x * 18, 161 + yOffset));
        }
        		
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(containerLevelAccess, player, BlockInit.POTION_BAG_BLOCK.get());
	}
	
	public static MenuConstructor getServerContainer(PotionBagBlockEntity chest, BlockPos pos) {
		return (id, playerInv, player) -> new PotionBagBlockContainer(id, playerInv, chest.inventory, pos);
	}
	
	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		
		Slot slot = this.getSlot(index);
	       
        if (!slot.mayPickup(player)) {
            return slot.getItem();
        }

        if ( !slot.hasItem()) {
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
            
        } else if(stack.getItem() instanceof PotionItem) {
        	if (!this.moveItemStackTo(stack, 0, potSegmentSize, false)) {
                return ItemStack.EMPTY;
            }
        }else if(stack.getItem() instanceof ArrowItem) {
        	if (!this.moveItemStackTo(stack, potSegmentSize, containerSlots, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (stack.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        slot.onTake(player, newStack);
        return newStack;
        
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
	
    private static boolean isPotionBag(ItemStack stack) {
        return stack.getItem() instanceof PotionBagItem;
    }
	
    private boolean canTake(int slotId, Slot slot, int button, Player player, ClickType clickType) {
        if (slotId <= itemHandler.getSlots() - 1 && isPotionBag(this.getCarried())) {
            return false;
        }
        if(!filtered(slotId, this.getCarried().getItem())) 
        	return false;

        // Hotbar swapping via number keys
        if (clickType == ClickType.SWAP) {
            int hotbarId = itemHandler.getSlots() + 27 + button;
            // Block swapping with container
            /*if (blocked == hotbarId) {
                return false;
            }*/

            Slot hotbarSlot = getSlot(hotbarId);
            if (slotId <= itemHandler.getSlots() - 1) {
                return !isPotionBag(slot.getItem()) && !isPotionBag(hotbarSlot.getItem()) && filtered(slotId, hotbarSlot.getItem().getItem());
            }
        }

        return true;
    }
    
    
    
    private boolean filtered(int slotId, Item item) {
    	if(slotId < itemHandler.getSlots()) {
        	if(slotId >= 18 && !(item instanceof ArrowItem || item instanceof AirItem))
        		return false;
        	if(slotId < 18 && !(item instanceof PotionItem || item instanceof AirItem))
        		return false;
    	}
    	return true;
    }
	
}
