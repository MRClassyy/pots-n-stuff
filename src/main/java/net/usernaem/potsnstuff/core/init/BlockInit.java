package net.usernaem.potsnstuff.core.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.common.blocks.PotionBagBlock;

public class BlockInit {
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PotsNStuff.MOD_ID_STRING );
	
	public static final RegistryObject<Block> POTION_BAG_BLOCK = BLOCKS.register("potion_bag_block", () -> new PotionBagBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak()));
	
}
