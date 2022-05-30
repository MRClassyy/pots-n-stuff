package net.usernaem.potsnstuff.common.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.block.state.BlockState;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.common.blocks.entity.util.InventoryBlockEntity;
import net.usernaem.potsnstuff.core.init.BlockEntityInit;

public class PotionBagBlockEntity extends InventoryBlockEntity{
	
	public static final Component TITLE = new TranslatableComponent(
        "container." + PotsNStuff.MOD_ID_STRING + ".potion_bag_block");


	public PotionBagBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.POTION_BAG_ENTITY.get(), pos, state, 24);
	}

}
