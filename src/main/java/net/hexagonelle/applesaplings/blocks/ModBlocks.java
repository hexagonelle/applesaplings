package net.hexagonelle.applesaplings.blocks;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.items.ItemCreator;
import net.hexagonelle.applesaplings.items.ModItems;
import net.hexagonelle.applesaplings.util.ModWoodTypes;
import net.hexagonelle.applesaplings.worldgen.tree.AppleTreeGrower;
import net.minecraft.world.level.block.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.function.Supplier;

public class ModBlocks {

	// HELPER METHODS //

	// Create a Deferred Register to hold Blocks which will all be registered under the "applesaplings" namespace
	public static final DeferredRegister<Block> BLOCKS =
		DeferredRegister.create(ForgeRegistries.BLOCKS, AppleSaplings.MODID);

	// A method that will register the DeferredRegister<Block> to the mod event bus
	public static void register(IEventBus eventBus){BLOCKS.register(eventBus);}

	// METHODS FOR REGISTERING BLOCKS //

	// A method that creates the corresponding BlockItem and registers both Block and BlockItem under the blockID.
	private static <T extends Block> RegistryObject<T> registerBlock(String blockID, Supplier<T> block){
		return BLOCKS.register(blockID, block);
	}

	// A method that creates the corresponding BlockItem and registers both Block and BlockItem under the blockID.
	private static <T extends Block> RegistryObject<T> registerBlockWithItem(String blockID, Supplier<T> block){
		RegistryObject<T> blockRegistryObject = registerBlock(blockID, block);
		ModItems.ITEMS.register(blockID, () -> ItemCreator.createBlockItem(blockRegistryObject));

		return blockRegistryObject;
	}

	// REGISTER BLOCKS //

	public static final RegistryObject<Block> APPLE_SAPLING =
			registerBlockWithItem("apple_sapling", () -> BlockCreator.createSapling(new AppleTreeGrower()));
	public static final RegistryObject<Block> APPLE_LEAVES =
		registerBlockWithItem("apple_leaves", BlockCreator::createLeaves);
	public static final RegistryObject<Block> FLOWERING_APPLE_LEAVES =
		registerBlockWithItem("flowering_apple_leaves", BlockCreator::createFloweringLeaves);
	public static final RegistryObject<Block> STRIPPED_APPLEWOOD_LOG =
		registerBlockWithItem("stripped_applewood_log", () -> BlockCreator.createWoodOrLogBlock(Blocks.OAK_LOG));
	public static final RegistryObject<Block> STRIPPED_APPLEWOOD_WOOD =
		registerBlockWithItem("stripped_applewood_wood", () -> BlockCreator.createWoodOrLogBlock(Blocks.OAK_WOOD));
	public static final RegistryObject<Block> APPLEWOOD_LOG =
		registerBlockWithItem("applewood_log",
			() -> BlockCreator.createWoodOrLogBlock(Blocks.OAK_LOG,ModBlocks.STRIPPED_APPLEWOOD_LOG));
	public static final RegistryObject<Block> APPLEWOOD_WOOD =
		registerBlockWithItem("applewood_wood",
			() -> BlockCreator.createWoodOrLogBlock(Blocks.OAK_WOOD,ModBlocks.STRIPPED_APPLEWOOD_WOOD));
	public static final RegistryObject<Block> APPLEWOOD_PLANKS =
		registerBlockWithItem("applewood_planks", BlockCreator::createPlanks);
	public static final RegistryObject<Block> APPLEWOOD_SIGN =
		registerBlock("applewood_sign", () -> BlockCreator.createSign(ModWoodTypes.APPLEWOOD));
	public static final RegistryObject<Block> APPLEWOOD_WALL_SIGN =
		registerBlock("applewood_wall_sign", () -> BlockCreator.createWallSign(ModWoodTypes.APPLEWOOD));
	public static final RegistryObject<Block> APPLEWOOD_HANGING_SIGN =
		registerBlock("applewood_hanging_sign", () -> BlockCreator.createHangingSign(ModWoodTypes.APPLEWOOD));
	public static final RegistryObject<Block> APPLEWOOD_WALL_HANGING_SIGN =
		registerBlock("applewood_wall_hanging_sign", () -> BlockCreator.createWallHangingSign(ModWoodTypes.APPLEWOOD));
	public static final RegistryObject<Block> APPLEWOOD_STAIRS =
		registerBlockWithItem("applewood_stairs", () -> BlockCreator.createWoodStairBlock(APPLEWOOD_PLANKS));
	public static final RegistryObject<Block> APPLEWOOD_SLAB =
		registerBlockWithItem("applewood_slab", BlockCreator::createWoodSlabBlock);
	public static final RegistryObject<Block> APPLEWOOD_BUTTON =
		registerBlockWithItem("applewood_button", BlockCreator::createWoodButton);
	public static final RegistryObject<Block> APPLEWOOD_PRESSURE_PLATE =
		registerBlockWithItem("applewood_pressure_plate", BlockCreator::createWoodPressurePlate);
	public static final RegistryObject<Block> APPLEWOOD_FENCE =
		registerBlockWithItem("applewood_fence", BlockCreator::createWoodFenceBlock);
	public static final RegistryObject<Block> APPLEWOOD_FENCE_GATE =
		registerBlockWithItem("applewood_fence_gate", BlockCreator::createWoodFenceGateBlock);
	public static final RegistryObject<Block> APPLEWOOD_DOOR =
		registerBlockWithItem("applewood_door", BlockCreator::createWoodDoorBlock);
	public static final RegistryObject<Block> APPLEWOOD_TRAPDOOR =
		registerBlockWithItem("applewood_trapdoor", BlockCreator::createWoodTrapDoorBlock);


}
