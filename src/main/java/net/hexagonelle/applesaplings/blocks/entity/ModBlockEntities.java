package net.hexagonelle.applesaplings.blocks.entity;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.hexagonelle.applesaplings.blocks.entity.custom.ModHangingSignBlockEntity;
import net.hexagonelle.applesaplings.blocks.entity.custom.ModSignBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlockEntities {

	// Create a Deferred Register to hold BlockEntityType under the "applesaplings" namespace
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
		DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AppleSaplings.MODID);

	// A method that will register the DeferredRegister<BlockEntityType<?>> to the mod event bus
	public static void register(IEventBus eventBus){
		BLOCK_ENTITIES.register(eventBus);
	}

	// A method that creates a BlockEntityType and registers it under BLOCK_ENTITIES with the given ID
	private static <T extends BlockEntityType<?>> RegistryObject<T> registerBlockEntity(
		String blockID,
		Supplier<T> blockEntity
	) {
		return BLOCK_ENTITIES.register(blockID, blockEntity);
	}

	public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
		registerBlockEntity("mod_sign",
			() -> BlockEntityCreator.createSignBlockEntity(ModBlocks.APPLEWOOD_SIGN,ModBlocks.APPLEWOOD_WALL_SIGN));
	public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN =
		registerBlockEntity("mod_hanging_sign",
			() -> BlockEntityCreator.createHangingSignBlockEntity(ModBlocks.APPLEWOOD_HANGING_SIGN,ModBlocks.APPLEWOOD_WALL_HANGING_SIGN));

}
