package net.hexagonelle.applesaplings.blockentities;

import net.hexagonelle.applesaplings.Constants;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.function.Supplier;

public class BlockEntityRegistry {


	// Create a Deferred Register to hold BlockEntityType under the "applesaplings" namespace
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
		DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Constants.MODID);

	// A method that will register the DeferredRegister<BlockEntityType<?>> to the mod event bus
	public static void register(IEventBus eventBus){
		BLOCK_ENTITY_TYPES.register(eventBus);
	}

	// Creates a hashmap so that we can refer to a RegistryObject by a string
	public static final HashMap<String, RegistryObject<BlockEntityType<?>>> BLOCK_MAP = new HashMap<>();

	// METHODS TO REGISTER BLOCKENTITIES //

	// A method that creates a BlockEntityType and registers it under BLOCK_ENTITY_TYPES with the given ID
	public static <T extends BlockEntityType<?>> RegistryObject<T> registerBlockEntity(
		String blockID,
		Supplier<T> blockEntity
	) {
		return BLOCK_ENTITY_TYPES.register(blockID, blockEntity);
	}

}
