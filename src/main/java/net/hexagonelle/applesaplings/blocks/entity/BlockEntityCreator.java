package net.hexagonelle.applesaplings.blocks.entity;

import net.hexagonelle.applesaplings.blocks.entity.custom.ModHangingSignBlockEntity;
import net.hexagonelle.applesaplings.blocks.entity.custom.ModSignBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityCreator {

	public static BlockEntityType<ModSignBlockEntity> createSignBlockEntity(
		RegistryObject<Block> standingSign,
		RegistryObject<Block> wallSign
	){
		return BlockEntityType.Builder.of(
			ModSignBlockEntity::new,
			standingSign.get(),
			wallSign.get()
		).build(null);
	}

	public static BlockEntityType<ModHangingSignBlockEntity> createHangingSignBlockEntity(
		RegistryObject<Block> ceilingHangingSign,
		RegistryObject<Block> wallHangingSign
	){
		return BlockEntityType.Builder.of(
			ModHangingSignBlockEntity::new,
			ceilingHangingSign.get(),
			wallHangingSign.get()
		).build(null);
	}

}
