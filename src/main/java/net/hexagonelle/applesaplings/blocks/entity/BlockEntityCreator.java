package net.hexagonelle.applesaplings.blocks.entity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityCreator {

	public static BlockEntityType<SignBlockEntity> createSignBlockEntity(
		RegistryObject<Block> floorSign,
		RegistryObject<Block> wallSign
	){
		return BlockEntityType.Builder.of(
			SignBlockEntity::new,
			floorSign.get(),
			wallSign.get()
		).build(null);
	}

	public static BlockEntityType<HangingSignBlockEntity> createHangingSignBlockEntity(
		RegistryObject<Block> hangingSign,
		RegistryObject<Block> wallHangingSign
	){
		return BlockEntityType.Builder.of(
			HangingSignBlockEntity::new,
			hangingSign.get(),
			wallHangingSign.get()
		).build(null);
	}

}
