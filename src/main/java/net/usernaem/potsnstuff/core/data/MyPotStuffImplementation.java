package net.usernaem.potsnstuff.core.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.phys.Vec3;

public class MyPotStuffImplementation implements MyPotStuffInterface{
	
	private static final String NBT_DIMEN_KEY = "dimenString";
	private static final String NBT_ANCHOR_X_KEY = "anchorX";
	private static final String NBT_ANCHOR_Y_KEY = "anchorY";
	private static final String NBT_ANCHOR_Z_KEY = "anchorZ";
	private static final String NBT_OLD_X_KEY = "oldX";
	private static final String NBT_OLD_Y_KEY = "oldY";
	private static final String NBT_OLD_Z_KEY = "oldZ";

	
	private double AnchorPosX;
	private double AnchorPosY;
	private double AnchorPosZ;
	private double oldPosX;
	private double oldPosY;
	private double oldPosZ;
	private String dimenString = "";
	
	
	public boolean compareOldPos(Vec3 Pos) {
		double X = this.oldPosX - Pos.x;
		double Y = this.oldPosY - Pos.y;
		double Z = this.oldPosZ - Pos.z;
		
		return (X == 0 && Z == 0 && Y >= 0)? true : false;
	}

	
	public boolean compareDimenString(String dimenString) {
		return dimenString.equalsIgnoreCase(this.dimenString);
	}
	
	public Vec3 getAnchorPos() {
		return new Vec3(AnchorPosX, AnchorPosY, AnchorPosZ);
	}
	
	public void setAnchorPos(Vec3 Pos) {
		AnchorPosX = Pos.x;
		AnchorPosY = Pos.y;
		AnchorPosZ = Pos.z;
	}
	
	public Vec3 getOldPos() {
		return new Vec3(oldPosX, 0, oldPosZ);
	}
	
	public void setOldPos(Vec3 Pos) {
		oldPosX = Pos.x;
		oldPosY = Pos.y;
		oldPosZ = Pos.z;
	}
	
	public String getDimensionString() {
		return dimenString;
	}
	
	public void setDimensionString(String dimen) {
		this.dimenString = dimen;
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag tag = new CompoundTag();
		tag.putString(NBT_DIMEN_KEY, this.dimenString);
		
		tag.putDouble(NBT_ANCHOR_X_KEY, this.AnchorPosX);
		tag.putDouble(NBT_ANCHOR_Y_KEY, this.AnchorPosY);
		tag.putDouble(NBT_ANCHOR_Z_KEY, this.AnchorPosZ);
		
		tag.putDouble(NBT_OLD_X_KEY, this.oldPosX);
		tag.putDouble(NBT_OLD_Y_KEY, this.oldPosY);
		tag.putDouble(NBT_OLD_Z_KEY, this.oldPosZ);

		return tag;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		this.dimenString = nbt.getString(NBT_DIMEN_KEY);
		this.AnchorPosX = nbt.getDouble(NBT_ANCHOR_X_KEY);
		this.AnchorPosY = nbt.getDouble(NBT_ANCHOR_Y_KEY);
		this.AnchorPosZ = nbt.getDouble(NBT_ANCHOR_Z_KEY);
		this.oldPosX = nbt.getDouble(NBT_OLD_X_KEY);
		this.oldPosY = nbt.getDouble(NBT_OLD_Y_KEY);
		this.oldPosZ = nbt.getDouble(NBT_OLD_Z_KEY);
		
	}


}
