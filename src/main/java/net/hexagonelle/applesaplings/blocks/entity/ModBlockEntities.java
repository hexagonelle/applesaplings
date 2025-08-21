package net.hexagonelle.applesaplings.blocks.entity;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlockEntities {

	// Create a Deferred Register to hold BlockEntityType which will all be registered under the "applesaplings" namespace
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
		DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AppleSaplings.MODID);

	public static void register(IEventBus eventBus){
		BLOCK_ENTITIES.register(eventBus);
	}

	// A method that creates the corresponding BlockItem and registers both Block and BlockItem under the blockID.
	private static <T extends BlockEntityType<?>> RegistryObject<T> registerBlockEntity(
		String blockID,
		Supplier<T> blockEntity
	) {
		return BLOCK_ENTITIES.register(blockID, blockEntity);
	}

	public static final RegistryObject<BlockEntityType<SignBlockEntity>> APPLEWOOD_SIGN =
		registerBlockEntity("applewood_sign",
			() -> BlockEntityCreator.createSignBlockEntity(ModBlocks.APPLEWOOD_SIGN,ModBlocks.APPLEWOOD_WALL_SIGN));
	public static final RegistryObject<BlockEntityType<HangingSignBlockEntity>> APPLEWOOD_HANGING_SIGN =
		registerBlockEntity("applewood_hanging_sign",
			() -> BlockEntityCreator.createHangingSignBlockEntity(ModBlocks.APPLEWOOD_HANGING_SIGN,ModBlocks.APPLEWOOD_WALL_HANGING_SIGN));

}
