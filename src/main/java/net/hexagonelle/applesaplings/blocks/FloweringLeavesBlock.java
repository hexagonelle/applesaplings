package net.hexagonelle.applesaplings.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.NotNull;

public class FloweringLeavesBlock extends LeavesBlock {
	public static final int MAX_AGE = 4;
	public static Item FRUIT_ITEM = Items.APPLE;
	public static final IntegerProperty AGE = BlockStateProperties.AGE_4;
	private static final float growthSpeed = 3.0f;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public FloweringLeavesBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(
				this.stateDefinition.any()
						.setValue(
								DISTANCE, 1
						).setValue(
								PERSISTENT, false
						).setValue(
								AGE, 0
						).setValue(
								WATERLOGGED, false
						)
		);
	}

	// Returns the age of a given blockstate.
	public int getAge(BlockState blockState) {
		return blockState.getValue(this.getAgeProperty());
	}

	// Returns the total number of growth stages.
	public int getMaxAge(){
		return MAX_AGE;
	}

	// If the leaves are not fully grown, they can be grown further.
	protected boolean growing(BlockState currentBlockState) {
		return this.getAge(currentBlockState) < this.getMaxAge();
	}

	// Return whether this block needs random ticking.
	public boolean isRandomlyTicking(@NotNull BlockState currentBlockState) {
		return this.decaying(currentBlockState) || this.growing(currentBlockState);
	}

	public IntegerProperty getAgeProperty() {
		return AGE;
	}

	// Returns a blockstate equal to
	// what *this* blockstate *would* be,
	// if the AGE property was set to the given age.
	public BlockState getStateForAge(int givenAge) {
		return this.defaultBlockState().setValue(AGE, givenAge);
	}

	@Override
	//Performs a random tick on a block.
	public void randomTick(
			@NotNull BlockState currentBlockState,
			@NotNull ServerLevel serverLevel,
			@NotNull BlockPos blockPosition,
			@NotNull RandomSource random
	){
		// If the leaves should be decaying, remove the block
		// and drop the corresponding resources
		if (this.decaying(currentBlockState)) {
			dropResources(currentBlockState, serverLevel, blockPosition);
			serverLevel.removeBlock(blockPosition, false);
		// If the leaves can be grown further
		} else if (this.growing(currentBlockState)) {
			// and if the random tick allows it
			if (
					ForgeHooks.onCropsGrowPre(
							serverLevel,
							blockPosition,
							currentBlockState,
							random.nextInt((int)(25.0F / growthSpeed) + 1) == 0
					)
			){
				// then increment the blockstate by 1
				serverLevel.setBlock(
					blockPosition,
					this.getStateForAge(getAge(currentBlockState) + 1),
						2
				);
				ForgeHooks.onCropsGrowPost(serverLevel, blockPosition, currentBlockState);
			}
		}
	}

	@Override
	public @NotNull InteractionResult use(
			@NotNull BlockState currentBlockState,
			@NotNull Level serverLevel,
			@NotNull BlockPos blockPosition,
			@NotNull Player player,
			@NotNull InteractionHand handIn,
			@NotNull BlockHitResult hit
	) {
		if (getAge(currentBlockState) == getMaxAge()) {
			ItemEntity droppedFruit =
				new ItemEntity(
					serverLevel,
					blockPosition.getX(),
					blockPosition.getY(),
					blockPosition.getZ(),
					new ItemStack(FRUIT_ITEM, 1));
			droppedFruit.spawnAtLocation(FRUIT_ITEM);
			//ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Items.APPLE));
			// then set blockstate back to 1
			serverLevel.setBlock(
					blockPosition,
					this.getStateForAge(1),
					1
			);
			return InteractionResult.SUCCESS;
		}
		return super.use(currentBlockState, serverLevel, blockPosition, player, handIn, hit);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
		builder.add(DISTANCE, PERSISTENT, WATERLOGGED, AGE);
	}
}
