package net.hexagonelle.applesaplings.blocks.custom;

import net.hexagonelle.applesaplings.blockentities.custom.ModHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModCeilingHangingSignBlock extends CeilingHangingSignBlock {
	public ModCeilingHangingSignBlock(Properties properties, WoodType woodType) { super(properties, woodType); }

	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new ModHangingSignBlockEntity(blockPos, blockState);
	}
}
