package net.usernaem.potsnstuff.core.data;

import javax.annotation.Nullable;
import javax.annotation.Nonnull;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.usernaem.potsnstuff.PotsNStuff;

public class MyPotStuffProvider implements ICapabilitySerializable<CompoundTag>{

	public static final ResourceLocation Identifier = new ResourceLocation(PotsNStuff.MOD_ID_STRING,"pnsvals");
	
	private final MyPotStuffInterface backend = new MyPotStuffImplementation();
	private final LazyOptional<MyPotStuffInterface> optionalData = LazyOptional.of(() -> backend);
	
	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		return MyPotStuffCapability.INSTANCE.orEmpty(cap, this.optionalData);
	}

    void invalidate() {
        this.optionalData.invalidate();
    }
    
    
	@Override
	public CompoundTag serializeNBT() {
		return this.backend.serializeNBT();
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		this.backend.deserializeNBT(nbt);
		
	}
	
}
