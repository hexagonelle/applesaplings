package net.hexagonelle.applesaplings.blockentities;

import net.hexagonelle.applesaplings.blockentities.custom.ModHangingSignBlockEntity;
import net.hexagonelle.applesaplings.blockentities.custom.ModSignBlockEntity;
import net.hexagonelle.applesaplings.blocks.BlockRegistry;
import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BlockEntitySuppliers {

	public static BlockEntityType<ModSignBlockEntity> createSignBlockEntity(
		String woodType
	){
		return BlockEntityType.Builder.of(
			ModSignBlockEntity::new,
			ModBlocks.BLOCK_MAP.get(woodType + "_sign").get(),
			ModBlocks.BLOCK_MAP.get(woodType + "_wall_sign").get()
		).build(null);
	}

	public static BlockEntityType<ModHangingSignBlockEntity> createHangingSignBlockEntity(
		String woodType
	){
		return BlockEntityType.Builder.of(
			ModHangingSignBlockEntity::new,
			ModBlocks.BLOCK_MAP.get(woodType + "_hanging_sign").get(),
			ModBlocks.BLOCK_MAP.get(woodType + "_wall_hanging_sign").get()
		).build(null);
	}

}
