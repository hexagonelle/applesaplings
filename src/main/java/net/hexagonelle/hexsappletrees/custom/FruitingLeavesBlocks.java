package net.hexagonelle.hexsappletrees.custom;

import net.hexagonelle.hexsappletrees.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;

public class FruitingLeavesBlocks extends CropBlock {
	public static final int MAX_AGE = 4;
	public static final IntegerProperty AGE = BlockStateProperties.AGE_4;
	public static final VoxelShape[] SHAPE_BY_AGE =
			new VoxelShape[]{
					Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
					Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
					Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
					Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
					Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};

	public FruitingLeavesBlocks(Properties pProperties) {
		super(pProperties);
	}

	@Override
	protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
		return true;
	}

	@Override
	protected IntegerProperty getAgeProperty(){
		return AGE;
	}

	@Override
	public int getMaxAge(){
		return MAX_AGE;
	}

	@Override
	public void randomTick(BlockState currentBlockState, ServerLevel serverLevel, BlockPos blockPosition, RandomSource random) {
		if (serverLevel.isAreaLoaded(blockPosition, 1)) {
			int currentAge = this.getAge(currentBlockState);
			if (currentAge < this.getMaxAge()) {
				float growthSpeed = getGrowthSpeed(this, serverLevel, blockPosition);
				if (ForgeHooks.onCropsGrowPre(serverLevel, blockPosition, currentBlockState, random.nextInt((int)(25.0F / growthSpeed) + 1) == 0)) {
					serverLevel.setBlock(blockPosition, this.getStateForAge(currentAge + 1), 2);
					ForgeHooks.onCropsGrowPost(serverLevel, blockPosition, currentBlockState);
				}
			}
		}
	}

	@Override
	protected int getBonemealAgeIncrease(Level pLevel) {
		return Mth.nextInt(pLevel.random, 2, 3);
	}

	@Override
	protected ItemLike getBaseSeedId() {
		return ModBlocks.APPLE_LEAVES.get();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
		builder.add(AGE);
	}
}
