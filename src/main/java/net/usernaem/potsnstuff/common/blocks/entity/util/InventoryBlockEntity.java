package net.usernaem.potsnstuff.common.blocks.entity.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.usernaem.potsnstuff.common.items.PotionBagItem;

public class InventoryBlockEntity extends BaseContainerBlockEntity {
    public final int size;
    protected int timer;
    protected boolean requiresUpdate;

    public final ItemStackHandler inventory;
    protected LazyOptional<ItemStackHandler> handler;
    
    public InventoryBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int size) {
        super(type, pos, state);
        if (size <= 0) {
            size = 1;
        }
        
        this.size = size;
        this.inventory = createInventory();
        this.handler = LazyOptional.of(() -> this.inventory);
    }
    
    public ItemStack extractItem(int slot) {
        final int count = getItemInSlot(slot).getCount();
        this.requiresUpdate = true;
        return this.handler.map(inv -> inv.extractItem(slot, count, false)).orElse(ItemStack.EMPTY);
    }
    
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? this.handler.cast()
            : super.getCapability(cap, side);
    }

    public LazyOptional<ItemStackHandler> getHandler() {
        return this.handler;
    }

    public ItemStack getItemInSlot(int slot) {
        return this.handler.map(inv -> inv.getStackInSlot(slot)).orElse(ItemStack.EMPTY);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return serializeNBT();
    }
    
    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        load(tag);
    }

    public ItemStack insertItem(int slot, ItemStack stack) {
        final ItemStack copy = stack.copy();
        stack.shrink(copy.getCount());
        this.requiresUpdate = true;
        return this.handler.map(inv -> inv.insertItem(slot, copy, false)).orElse(ItemStack.EMPTY);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.handler.invalidate();
    }
    
    @Override
    public void load(CompoundTag tag) {
        this.inventory.deserializeNBT(tag.getCompound(PotionBagItem.INVENTORY_KEY));
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(pkt.getTag());
    }
    
    public void tick() {
        //this.timer++;
        if (this.requiresUpdate && this.level != null) {
            update();
            this.requiresUpdate = false;
        }
    }

    public void update() {
        requestModelDataUpdate();
        setChanged();
        if (this.level != null) {
            this.level.setBlockAndUpdate(this.worldPosition, getBlockState());
        }
    }
    
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put(PotionBagItem.INVENTORY_KEY, this.inventory.serializeNBT());
    }
    
    private ItemStackHandler createInventory() {
        return new ItemStackHandler(this.size) {
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                InventoryBlockEntity.this.update();
                return super.extractItem(slot, amount, simulate);
            }
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return canPlaceItem(slot, stack);
            }

            @Override
            public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
                InventoryBlockEntity.this.update();
                return super.insertItem(slot, stack, simulate);
            }
        };
    }
    
    public void copyInventory(ItemStackHandler newInv) {
    	if(newInv.getSlots() != this.size) 
    		return;
    	for(int i = 0; i < this.size; i++) {
    		ItemStack itemStack = newInv.getStackInSlot(i);
    		if(!itemStack.isEmpty()) {
    			inventory.insertItem(i, itemStack, false);
    		}
    	}
    }
    
    public boolean canPlaceItemThroughFace(int p_58336_, ItemStack p_58337_, @Nullable Direction p_58338_) {
       return this.canPlaceItem(p_58336_, p_58337_);
    }
    @Override
    public boolean canPlaceItem(int slot, ItemStack itemStack) {
        if(slot< 18 && itemStack.getItem() instanceof PotionItem)
        	return true;
        if(slot >= 18 && slot < size && itemStack.getItem() instanceof ArrowItem)
        	return true;
        return false;
     }
    
	@Override
	public int getContainerSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		for(int i = 0; i < size; i++) {
			if(!inventory.getStackInSlot(i).isEmpty())
				return false;
		}
		return true;
	}

	@Override
	public ItemStack getItem(int slot) {
        return this.handler.map(inv -> inv.getStackInSlot(slot)).orElse(ItemStack.EMPTY);
	}

	@Override
	public ItemStack removeItem(int index, int count) {
		return index >= 0 && index < size && !inventory.getStackInSlot(index).isEmpty() && count > 0 ? inventory.getStackInSlot(index).split(count) : ItemStack.EMPTY;
	}

	@Override
	public ItemStack removeItemNoUpdate(int p_18951_) {
		NonNullList<ItemStack> items = NonNullList.withSize(24, ItemStack.EMPTY);
		for(int i = 0; i < size; i++) {
			items.add(inventory.getStackInSlot(p_18951_));
		}
	      return ContainerHelper.takeItem(items, p_18951_);
	}

	@Override
	public void setItem(int index, ItemStack itemStack) {
	      this.inventory.insertItem(index, itemStack, false);
	      if (itemStack.getCount() > this.getMaxStackSize()) {
	    	  itemStack.setCount(this.getMaxStackSize());
	      }

		
	}

	@Override
	public boolean stillValid(Player p_18946_) {
	      if (this.level.getBlockEntity(this.worldPosition) != this) {
	          return false;
	       } else {
	          return p_18946_.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
	       }
	}

	@Override
	public void clearContent() {
		for(int i = 0; i < size; i++) {
			inventory.setStackInSlot(i, ItemStack.EMPTY);
		}
		
	}

	@Override
	protected Component getDefaultName() {
        return new TranslatableComponent("block.potsnstuff.potion_bag_block");
	}

	@Override
	protected AbstractContainerMenu createMenu(int p_58627_, Inventory p_58628_) {
		return null;
	}
}