package net.hexagonelle.examplemod.blocks;

import net.hexagonelle.examplemod.Constants;
import net.hexagonelle.examplemod.creativetabs.CreativeTabRegistry;
import net.hexagonelle.examplemod.items.ItemRegistry;
import net.hexagonelle.examplemod.registration.RegistrationProvider;
import net.hexagonelle.examplemod.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.function.Supplier;

public class BlockRegistry {

	public static void init() {}

	// Creates the register that holds the blocks
	public static final RegistrationProvider<Block> BLOCKS =
		RegistrationProvider.get(Registries.BLOCK, Constants.MOD_ID);

	// Creates a hashmap so that we can refer to a RegistryObject by a string
	public static final HashMap<String, RegistryObject<Block>> BLOCK_MAP = new HashMap<>();

	// registers an block and places the corresponding RegistryObject in the HashMap by blockId
	public static RegistryObject<Block> registerBlock(
		String blockId,
		Supplier<Block> blockSupplier
	){
		return BLOCK_MAP.put(blockId, BLOCKS.register(blockId,blockSupplier));
	}

	public static void registerBlockWithItem(
		String blockId,
		Supplier<Block> blockSupplier
	){
		ItemRegistry.registerBlockItem(blockId,registerBlock(blockId, blockSupplier));
	}

	public static void registerBlockWithItemWithModTab(
		String blockId,
		Supplier<Block> blockSupplier,
		String creativeTabKey
	){
		ItemRegistry.registerBlockItemWithModTab(blockId,registerBlock(blockId, blockSupplier),creativeTabKey);
	}

	public static void registerBlockWithItemWithVanillaTab(
		String blockId,
		Supplier<Block> blockSupplier,
		CreativeModeTab creativeTab
	){
		ItemRegistry.registerBlockItemWithVanillaTab(blockId,registerBlock(blockId, blockSupplier),creativeTab);
	}
	
}
