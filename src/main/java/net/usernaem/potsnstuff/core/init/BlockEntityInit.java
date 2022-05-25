package net.usernaem.potsnstuff.core.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.common.blocks.entity.PotionBagBlockEntity;

public class BlockEntityInit {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
            .create(ForgeRegistries.BLOCK_ENTITIES, PotsNStuff.MOD_ID_STRING);
    
    public static final RegistryObject<BlockEntityType<PotionBagBlockEntity>> POTION_BAG_ENTITY = BLOCK_ENTITIES
            .register("potion_bag_block", () -> BlockEntityType.Builder
                    .of(PotionBagBlockEntity::new, BlockInit.POTION_BAG_BLOCK.get()).build(null));
}
