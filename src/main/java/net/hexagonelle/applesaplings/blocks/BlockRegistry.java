package net.hexagonelle.applesaplings.blocks;

import net.hexagonelle.applesaplings.Constants;
import net.hexagonelle.applesaplings.items.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.function.Supplier;

import static net.hexagonelle.applesaplings.blocks.ModBlocks.*;

public class BlockRegistry {

	// METHODS TO REGISTER BLOCKS //

	// registers an block and places the corresponding RegistryObject in the HashMap by blockId
	public static void
	registerBlock(
		String blockId,
		Supplier<Block> blockSupplier
	){
		RegistryObject<Block> blockRegistryObject = BLOCKS.register(blockId,blockSupplier);
		BLOCK_MAP.put(blockId, blockRegistryObject);
	}

	public static void registerBlockWithItem(
		String blockId,
		Supplier<Block> blockSupplier
	){
		registerBlock(blockId, blockSupplier);
		ItemRegistry.registerBlockItem(blockId);
	}

	public static void registerBlockWithItemWithModTab(
		String blockId,
		Supplier<Block> blockSupplier,
		String creativeTabKey
	){
			registerBlock(blockId, blockSupplier);
			ItemRegistry.registerBlockItemWithModTab(blockId,creativeTabKey);
	}

	public static void registerBlockWithItemWithVanillaTab(
		String blockId,
		Supplier<Block> blockSupplier,
		CreativeModeTab creativeTab
	){
		registerBlock(blockId, blockSupplier);
		ItemRegistry.registerBlockItemWithVanillaTab(blockId,creativeTab);
	}

	public static void registerSapling(
		String saplingIdPrefix,
		AbstractTreeGrower treeGrower,
		String creativeTabKey
	){
		registerBlockWithItemWithModTab(
			saplingIdPrefix + "_sapling",
			() -> BlockSuppliers.createSapling(treeGrower), creativeTabKey
		);
	}

	public static void registerLeaves(
		String leavesIdPrefix,
		String creativeTabKey
	){
		registerBlockWithItemWithModTab(
			leavesIdPrefix + "_leaves",
			BlockSuppliers::createLeaves, creativeTabKey);
	}

	public static void registerStrippedLog(
		String woodType,
		String creativeTabKey
	){
		registerBlockWithItemWithModTab("stripped_" + woodType + "_log", BlockSuppliers::createStrippedLogBlock,
			creativeTabKey);
	}

	public static void registerStrippedWood(
		String woodType,
		String creativeTabKey
	){
		registerBlockWithItemWithModTab("stripped_" + woodType + "_wood", BlockSuppliers::createStrippedWoodBlock,
			creativeTabKey);
	}

	public static void registerLog(
		String woodType,
		String creativeTabKey
	){
		String logName = woodType + "_log";
		registerStrippedLog(woodType,creativeTabKey);
		registerBlockWithItemWithModTab(
			logName,() -> BlockSuppliers.createLogBlock("stripped_" + logName),
			creativeTabKey
		);
	}

	public static void registerWood(
		String woodType,
		String creativeTabKey
	){
		String woodName = woodType + "_wood";
		registerStrippedWood(woodType,creativeTabKey);
		registerBlockWithItemWithModTab(
			woodName,() -> BlockSuppliers.createWoodBlock("stripped_" + woodName),
			creativeTabKey
		);
	}

	public static void registerPlanks(
		String woodType,
		String creativeTabKey
	){
		registerBlockWithItemWithModTab(
			woodType + "_planks", BlockSuppliers::createPlanks,
			creativeTabKey
		);
	}

	public static void registerWallSign(
		String woodType
	){
		registerBlock(woodType + "_wall_sign", () -> BlockSuppliers.createWallSign(woodType));
	}

	public static void registerSign(
		String woodType,
		String creativeTabKey
	){
		registerBlock(woodType + "_sign", () -> BlockSuppliers.createSign(woodType));
		registerWallSign(woodType);
		ItemRegistry.registerSign(woodType,creativeTabKey);
	}

	public static void registerWallHangingSign(
		String woodType
	){
		registerBlock(woodType + "_wall_hanging_sign", () -> BlockSuppliers.createWallHangingSign(woodType));
	}

	public static void registerHangingSign(
		String woodType,
		String creativeTabKey
	){
		registerBlock(woodType + "_hanging_sign", () -> BlockSuppliers.createHangingSign(woodType));
		registerWallHangingSign(woodType);
		ItemRegistry.registerHangingSign(woodType,creativeTabKey);
	}

	public static void registerWoodStairs(
		String woodType,
		String creativeTabKey
	){
		registerBlockWithItemWithModTab(
			woodType + "_stairs", () -> BlockSuppliers.createWoodStairBlock(woodType),
			creativeTabKey
		);
	}

	public static void registerWoodSlab(
		String woodType,
		String creativeTabKey
	){
		registerBlockWithItemWithModTab(
			woodType + "_slab", BlockSuppliers::createWoodSlabBlock,
			creativeTabKey
		);
	}

	public static void registerWoodButton(
		String woodType,
		String creativeTabKey
	){
		registerBlockWithItemWithModTab(
			woodType + "_button", BlockSuppliers::createWoodButton,
			creativeTabKey
		);
	}

	public static void registerWoodPressurePlate(
		String woodType,
		String creativeTabKey
	){
		registerBlockWithItemWithModTab(
			woodType + "_pressure_plate", BlockSuppliers::createWoodPressurePlate,
			creativeTabKey
		);
	}

	public static void registerFence(
		String woodType,
		String creativeTabKey
	){
		registerBlockWithItemWithModTab(
			woodType + "_fence", BlockSuppliers::createWoodFenceBlock,
			creativeTabKey
		);
	}

	public static void registerFenceGate(
		String woodType,
		String creativeTabKey
	){
		registerBlockWithItemWithModTab(
			woodType + "_fence_gate", BlockSuppliers::createWoodFenceGateBlock,
			creativeTabKey
		);
	}

	public static void registerWoodDoor(
		String woodType,
		String creativeTabKey
	){
		registerBlockWithItemWithModTab(
			woodType + "_door", BlockSuppliers::createWoodDoorBlock,
			creativeTabKey
		);
	}

	public static void registerWoodTrapdoor(
		String woodType,
		String creativeTabKey
	){
		registerBlockWithItemWithModTab(
			woodType + "_trapdoor", BlockSuppliers::createWoodTrapDoorBlock,
			creativeTabKey
		);
	}

	public static void registerNewWoodType(
		String woodType,
		String saplingIdPrefix,
		String leavesIdPrefix,
		AbstractTreeGrower treeGrower,
		String creativeTabKey
	){
		registerSapling(saplingIdPrefix,treeGrower,creativeTabKey);
		registerLeaves(leavesIdPrefix,creativeTabKey);

		registerStrippedLog(woodType,creativeTabKey);
		registerStrippedWood(woodType,creativeTabKey);
		registerLog(woodType,creativeTabKey);
		registerWood(woodType,creativeTabKey);
		registerPlanks(woodType,creativeTabKey);

		registerSign(woodType,creativeTabKey);
		registerHangingSign(woodType,creativeTabKey);

		registerWoodStairs(woodType,creativeTabKey);
		registerWoodSlab(woodType,creativeTabKey);

		registerWoodButton(woodType,creativeTabKey);
		registerWoodPressurePlate(woodType,creativeTabKey);

		registerFence(woodType,creativeTabKey);
		registerFenceGate(woodType,creativeTabKey);
		registerWoodDoor(woodType,creativeTabKey);
		registerWoodTrapdoor(woodType,creativeTabKey);
	}

}
