package net.usernaem.potsnstuff.common.datagen;

import net.minecraft.data.DataGenerator;
import net.usernaem.potsnstuff.core.init.BlockEntityInit;
import net.usernaem.potsnstuff.core.init.BlockInit;

public class LootTables extends BaseLootTableProvider{

	public LootTables(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
	}
	
	@Override
    protected void addTables() {
		lootTables.put(BlockInit.POTION_BAG_BLOCK.get(), createStandardTable("potion_bag", BlockInit.POTION_BAG_BLOCK.get(), BlockEntityInit.POTION_BAG_ENTITY.get()));
		
	}
}
