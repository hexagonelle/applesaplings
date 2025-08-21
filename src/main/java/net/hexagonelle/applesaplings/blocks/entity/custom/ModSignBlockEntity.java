package net.hexagonelle.applesaplings.blocks.entity.custom;

import net.hexagonelle.applesaplings.blocks.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ModSignBlockEntity extends SignBlockEntity {

	public ModSignBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(ModBlockEntities.MOD_SIGN.get(), blockPos, blockState);
	}

	@Override
	public @NotNull BlockEntityType<?> getType() {
		return ModBlockEntities.MOD_SIGN.get();
	}

}
