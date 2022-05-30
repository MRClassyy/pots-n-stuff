package net.usernaem.potsnstuff.core.enums.blockStates;

import net.minecraft.util.StringRepresentable;

public enum AttachFloorWall implements StringRepresentable {
		   FLOOR("floor"),
		   WALL("wall");

		   private final String name;

		   private AttachFloorWall(String p_61311_) {
		      this.name = p_61311_;
		   }

		   public String getSerializedName() {
		      return this.name;
		   }
}

