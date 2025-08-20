package net.hexagonelle.applesaplings.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.ToolAction;

import javax.annotation.Nullable;
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

	private Boolean isStripped = false;
	private Block strippedVersion = null;

	public Block setIsStripped(Boolean bool){
		isStripped = bool;
		return this;
	}
	public Boolean getIsStripped(){
		return isStripped;
	}

	public Block setStrippedVersion(Block strippedBlock){
		if (!isStripped) {strippedVersion = strippedBlock;}
		return this;
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

	@Override
	public @Nullable BlockState getToolModifiedState(
		BlockState state,
		UseOnContext context,
		ToolAction toolAction,
		boolean simulate
	) {
		if(context.getItemInHand().getItem() instanceof AxeItem) {
			if(state.is(this) && !isStripped) {
				return strippedVersion.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
			}
		}

		return super.getToolModifiedState(state, context, toolAction, simulate);
	}

}
