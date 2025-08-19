package net.hexagonelle.applesaplings.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;

public class CustomWood extends RotatedPillarBlock{

	public CustomWood(Properties pProperties) {
		super(pProperties);
	}

	public static Function<BlockState, MapColor> woodMapColor(
		MapColor topMapColor,
		MapColor sideMapColor
	) {
		return (blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor;
	}

	@Override
	public boolean isFlammable(BlockState blockstate, BlockGetter level, BlockPos blockPos, Direction direction){
		return true;
	}

	@Override
	public int getFlammability(BlockState blockstate, BlockGetter level, BlockPos blockPos, Direction direction){
		return 5;
	}

	@Override
	public int getFireSpreadSpeed(BlockState blockstate, BlockGetter level, BlockPos blockPos, Direction direction){
		return 5;
	}

}
