package net.usernaem.potsnstuff.common.blocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import net.usernaem.potsnstuff.common.blocks.entity.PotionBagBlockEntity;
import net.usernaem.potsnstuff.common.containers.PotionBagBlockContainer;
import net.usernaem.potsnstuff.common.items.PotionBagItem;
import net.usernaem.potsnstuff.core.enums.blockStates.AttachFloorWall;
import net.usernaem.potsnstuff.core.init.BlockInit;
import net.usernaem.potsnstuff.core.util.CalculateClass;

public class PotionBagBlock extends Block implements EntityBlock{
	   public static final ResourceLocation CONTENTS = new ResourceLocation("contents");
	   public static final EnumProperty<AttachFloorWall> ATTACH_FLOOR_WALL = EnumProperty.create("floor_wall", AttachFloorWall.class);
	
	   public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	   public static final EnumProperty<AttachFloorWall> FACE = PotionBagBlock.ATTACH_FLOOR_WALL;
		private static final Optional<VoxelShape> SHAPE = Stream.of(
				Block.box(2, 0, 5, 14, 14, 11)
				).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR));		
		private static final Optional<VoxelShape> SHAPE_SIDE = Stream.of(
				Block.box(2, 1, 9, 14, 15, 16)
				).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR));
	//private static final Map<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);
	private static final Map<Direction, VoxelShape> SHAPES = new HashMap<>();
	private static final Map<Direction, VoxelShape> SHAPES_WALL = new HashMap<>();
	
	public PotionBagBlock(Properties properties) {
		super(properties);
		registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH).setValue(FACE, AttachFloorWall.FLOOR));
		runCalculation(SHAPE.orElse(Shapes.block()), SHAPE_SIDE.orElse(Shapes.block()));
	}
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, FACE);
	}
	
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos,
			CollisionContext context) {
		if(state.getValue(FACE) == AttachFloorWall.FLOOR) {
			return SHAPES.get(state.getValue(FACING));
		}else
			return SHAPES_WALL.get(state.getValue(FACING));
	}
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		for(Direction direction : context.getNearestLookingDirections()) {
	         BlockState blockstate;
	         if (direction.getAxis() == Direction.Axis.Y) {
	            blockstate = this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(FACE, AttachFloorWall.FLOOR);
	         } else {
	            blockstate = this.defaultBlockState().setValue(FACING, direction.getOpposite()).setValue(FACE, AttachFloorWall.WALL);
	         }

	         if (blockstate.canSurvive(context.getLevel(), context.getClickedPos())) {
	            return blockstate;
	         }
	      }
	      return null;
		//return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(FACE, AttachFloorWall.FLOOR);
	}
	protected void runCalculation(VoxelShape shape, VoxelShape shape_side) {
		for (Direction direction : Direction.values()) {
			SHAPES.put(direction, CalculateClass.calculateShapes(direction, shape));
			SHAPES_WALL.put(direction, CalculateClass.calculateShapes(direction, shape_side));
		}
	}

	public BlockState updateShape(BlockState p_57503_, Direction direction, BlockState blockstate, LevelAccessor p_57506_, BlockPos p_57507_, BlockPos p_57508_) {
	   return getConnectedDirection(p_57503_).getOpposite() == direction && !this.canSurvive(p_57503_, p_57506_, p_57507_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_57503_, direction, blockstate, p_57506_, p_57507_, p_57508_);
	}
	
	public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
	     return canAttach(levelReader, pos, getConnectedDirection(state).getOpposite());
	}
	 protected static Direction getConnectedDirection(BlockState p_53201_) {
	      switch((AttachFloorWall)p_53201_.getValue(FACE)) {
	      case FLOOR:
	         return Direction.UP;
	      default:
	         return p_53201_.getValue(FACING);
	      }
	   }

	   public static boolean canAttach(LevelReader levelReader, BlockPos pos, Direction direction) {
	      BlockPos blockpos = pos.relative(direction);
	      return levelReader.getBlockState(blockpos).isFaceSturdy(levelReader, blockpos, direction.getOpposite());
	   }
	   
	   public void playerWillDestroy(Level p_56212_, BlockPos p_56213_, BlockState p_56214_, Player p_56215_) {
		      BlockEntity blockentity = p_56212_.getBlockEntity(p_56213_);
		      if (blockentity instanceof PotionBagBlockEntity) {
		    	  PotionBagBlockEntity potionbagblockentity = (PotionBagBlockEntity)blockentity;
		         if (!p_56212_.isClientSide && p_56215_.isCreative() && !potionbagblockentity.isEmpty()) {
		            ItemStack itemstack = new ItemStack(BlockInit.POTION_BAG_BLOCK.get());
		            PotionBagItem.saveInventory(itemstack, potionbagblockentity.inventory);
		            if (potionbagblockentity.hasCustomName()) {
		               itemstack.setHoverName(potionbagblockentity.getCustomName());
		            }

		            ItemEntity itementity = new ItemEntity(p_56212_, (double)p_56213_.getX() + 0.5D, (double)p_56213_.getY() + 0.5D, (double)p_56213_.getZ() + 0.5D, itemstack);
		            itementity.setDefaultPickUpDelay();
		            p_56212_.addFreshEntity(itementity);
		         } else {
		         }
		      }

		      super.playerWillDestroy(p_56212_, p_56213_, p_56214_, p_56215_);
		   
	   }
	   
	@Override
	public void setPlacedBy(Level p_56206_, BlockPos p_56207_, BlockState p_56208_, LivingEntity p_56209_, ItemStack itemStack) {
		BlockEntity blockentity = p_56206_.getBlockEntity(p_56207_);
		Item item = itemStack.getItem();
		if (blockentity instanceof PotionBagBlockEntity && item instanceof PotionBagItem) {
			if (itemStack.hasCustomHoverName()) {
				((PotionBagBlockEntity)blockentity).setCustomName(itemStack.getHoverName());
			}
			((PotionBagBlockEntity)blockentity).copyInventory((ItemStackHandler)((PotionBagItem)item).getInventory(itemStack));
		}
	}
	   
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {

		if(!level.isClientSide && level.getBlockEntity(pos) instanceof final PotionBagBlockEntity bag) {
			final MenuProvider container = new SimpleMenuProvider(PotionBagBlockContainer.getServerContainer(bag, pos), PotionBagBlockEntity.TITLE); 
			NetworkHooks.openGui((ServerPlayer)player, container, pos);
		}
		
		return InteractionResult.SUCCESS;
	}
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new PotionBagBlockEntity(pos, state);
	}
	
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
            BlockEntityType<T> beType) {
        return level.isClientSide ? null
                : (level0, pos, state0, blockEntity) -> ((PotionBagBlockEntity) blockEntity).tick();
    }
}
