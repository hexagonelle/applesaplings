package net.hexagonelle.applesaplings.blockentities;

import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.hexagonelle.applesaplings.blockentities.custom.ModHangingSignBlockEntity;
import net.hexagonelle.applesaplings.blockentities.custom.ModSignBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.hexagonelle.applesaplings.blockentities.BlockEntityRegistry.*;

public class ModBlockEntities {

	public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
		registerBlockEntity("mod_sign",
			() -> BlockEntitySuppliers.createSignBlockEntity("applewood"));
	public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN =
		registerBlockEntity("mod_hanging_sign",
			() -> BlockEntitySuppliers.createHangingSignBlockEntity("applewood"));

}
