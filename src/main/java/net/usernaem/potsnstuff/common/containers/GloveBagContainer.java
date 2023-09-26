package net.usernaem.potsnstuff.common.containers;


import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import java.util.HashMap;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.usernaem.potsnstuff.common.items.GloveItem;
import net.usernaem.potsnstuff.common.items.PotionBagItem;
import net.usernaem.potsnstuff.core.init.ConteinerTypeInit;

public class GloveBagContainer  extends AbstractContainerMenu{
	private final ItemStack item;
	private final ItemStack gloveItem;
    private final IItemHandler itemHandler;
    private final IItemHandler gloveItemHandler;
    private int blocked = -1;
    private int potSegmentSize = 0;
    private int arrowSegmentSize = 0;
    private int gloveSegmentSize = 0;
    
	public GloveBagContainer(int id, Inventory playerInventory) {
		 super(ConteinerTypeInit.POTION_BAG_GLOVE.get(), id);
	        HashMap<String, ItemStack> heldItems = getHeldItems(playerInventory.player);
	        this.item = heldItems.get("bag");
	        this.gloveItem = heldItems.get("glove");
	        
	        this.itemHandler = ((PotionBagItem) this.item.getItem()).getInventory(this.item);
	        
	        this.gloveItemHandler = ((GloveItem) this.gloveItem.getItem()).getInventory(this.gloveItem);
	        
	        this.gloveSegmentSize = ((GloveItem) this.gloveItem.getItem()).getInventorySize();

	        // Add backpack slots (3 rows of 9)
	        potSegmentSize = 18;
	        arrowSegmentSize = 6;
	        //potion segment creation
	        for (int i = 0; i < potSegmentSize; ++i) {
		        int x = 8 + 18 * (i % 6);
		        int y = 18 + 18 * (i / 6);
				
	            addSlot(new SlotItemHandler(this.itemHandler, i, x, y));
	        }

	        //arrow segment creation
	        for (int i = 0; i < arrowSegmentSize; ++i) {
		        int x = 134 + 18 * (i % 2);
		        int y = 18 + 18 * (i / 2);
				
	            addSlot(new SlotItemHandler(this.itemHandler, potSegmentSize + i, x, y));
	        }
	        
	        //glove segment creation
	        for(int i = 0; i < gloveSegmentSize; i++) {
		        int x = 182 + 18 * (i / 4);
		        int y = 22 + 18 * (i % 4);
	        	addSlot(new SlotItemHandler(this.gloveItemHandler, i, x, y));
	        }
	        
	        final int rowCount = this.itemHandler.getSlots() / 8;
	        final int yOffset = (rowCount - 4) * 18 + 1;

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

	            if (x == playerInventory.selected && 
	            		(ItemStack.matches(playerInventory.getSelected(), this.item) ||
	            			ItemStack.matches(playerInventory.getSelected(), this.gloveItem))
	            	) {
	                blocked = slot.index;
	            }
	        }
	    }
	
	public static HashMap<String, ItemStack> getHeldItems(Player player) {
		HashMap<String, ItemStack> heldItems = new HashMap<String, ItemStack>();
        // Determine which held item is a backpack (if either)
        if (isPotionBag(player.getMainHandItem())) {
            heldItems.put("bag", player.getMainHandItem());
            heldItems.put("glove", player.getOffhandItem());
        }
        if (isPotionBag(player.getOffhandItem())) {
            heldItems.put("bag", player.getOffhandItem());
            heldItems.put("glove", player.getMainHandItem());
        }
        return heldItems;
    }

    /**
     * Gets the number of bag inventory rows. This assumes 8 slots per row.
     *
     * @return The number of rows of backpack slots
     */
    public int getInventoryRows() {
        return this.gloveItemHandler.getSlots() / 4;
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return true;
    }

    @Override
    public void removed(Player playerIn) {
        super.removed(playerIn);
        
        if(!this.item.hasTag())
        	this.item.setTag(new CompoundTag());
        this.item.getTag().put("inventory", ((ItemStackHandler) this.itemHandler).serializeNBT());
        

        if(!this.gloveItem.hasTag())
        	this.gloveItem.setTag(new CompoundTag());
        this.gloveItem.getTag().put("inventory", ((ItemStackHandler) this.gloveItemHandler).serializeNBT());
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        // This method handles shift-clicking to transfer items quickly. This can easily crash the game if not coded
        // correctly. The first slots (index 0 to whatever) are usually the inventory block/item, while player slots
        // start after those.
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
        int bagSlots = itemHandler.getSlots();
        int gloveSlots = bagSlots + gloveSegmentSize;
        
        //bag part, if possible put items into glove
        if (index < bagSlots) {
            if (!this.moveItemStackTo(stack, bagSlots, gloveSlots, true)) {
                return ItemStack.EMPTY;
            }
            slot.setChanged();    
        }
        //glove part, if possible put item into appropreate bag slot
        else if (index < gloveSlots) {
        	if(stack.getItem() instanceof PotionItem) {
            	if (!this.moveItemStackTo(stack, 0, potSegmentSize, false)) {
                    return ItemStack.EMPTY;
                }
            }else if(stack.getItem() instanceof ArrowItem) {
            	if (!this.moveItemStackTo(stack, potSegmentSize, potSegmentSize + arrowSegmentSize, false)) {
                    return ItemStack.EMPTY;
                }
            }
        //player inventory part, if possible add item into bag or glove
    	}else if(stack.getItem() instanceof PotionItem) {
        	if (!this.moveItemStackTo(stack, 0, potSegmentSize, false) && !this.moveItemStackTo(stack, bagSlots, gloveSlots, false)) {
                return ItemStack.EMPTY;
            }
        }else if(stack.getItem() instanceof ArrowItem) {
        	if (!this.moveItemStackTo(stack, potSegmentSize, potSegmentSize + arrowSegmentSize, false) && !this.moveItemStackTo(stack, bagSlots, gloveSlots, false)) {
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

    private static boolean isBagOrGlove(ItemStack stack) {
        return stack.getItem() instanceof PotionBagItem || stack.getItem() instanceof GloveItem;
    }

    private boolean canTake(int slotId, Slot slot, int button, Player player, ClickType clickType) {
        if (slotId == blocked || slotId <= itemHandler.getSlots() - 1 && isBagOrGlove(this.getCarried())) {
            return false;
        }
        if(!filtered(slotId, this.getCarried().getItem())) 
        	return false;

        // Hotbar swapping via number keys
        if (clickType == ClickType.SWAP) {
            int hotbarId = itemHandler.getSlots() + gloveItemHandler.getSlots() + 27 + button;
            // Block swapping with container
            if (blocked == hotbarId) {
                return false;
            }

            Slot hotbarSlot = getSlot(hotbarId);
            if (slotId <= itemHandler.getSlots() + gloveItemHandler.getSlots() - 1) {
                return !isBagOrGlove(slot.getItem()) && !isBagOrGlove(hotbarSlot.getItem()) && filtered(slotId, hotbarSlot.getItem().getItem());
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
    	if(slotId >= itemHandler.getSlots() && slotId < itemHandler.getSlots() + gloveItemHandler.getSlots())
    		if(!(item instanceof ArrowItem || item instanceof AirItem || item instanceof PotionItem))
    			return false;
    	
    	return true;
    }
	
    
}