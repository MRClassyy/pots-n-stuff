package net.usernaem.potsnstuff.core.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraft.world.phys.Vec3;

public interface MyPotStuffInterface extends INBTSerializable<CompoundTag>{
	
	boolean compareOldPos(Vec3 Pos);
	
	boolean compareDimenString(String dimenString);
	
	Vec3 getAnchorPos();
	
	void setAnchorPos(Vec3 Pos);
	
	Vec3 getOldPos();
	
	void setOldPos(Vec3 Pos);
	
	String getDimensionString();
	
	void setDimensionString(String dimen);
	
	
}
