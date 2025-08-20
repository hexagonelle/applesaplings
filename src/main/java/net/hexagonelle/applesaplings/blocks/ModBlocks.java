package net.hexagonelle.applesaplings.blocks;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.items.ModItems;
import net.hexagonelle.applesaplings.worldgen.tree.AppleTreeGrower;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ModBlocks {

	// HELPER METHODS //

	// Create a Deferred Register to hold Blocks which will all be registered under the "applesaplings" namespace
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AppleSaplings.MODID);

	// A method that creates a new BlockItem, given some RegistryObject<Block>
	private static <T extends Block> Item createBlockItem(Supplier<T> block){
		return new BlockItem(block.get(), new Item.Properties());
	}

	// A method that creates the corresponding BlockItem and registers both Block and BlockItem under the blockID.
	private static <T extends Block> RegistryObject<T> registerBlock(String blockID, Supplier<T> block){
		RegistryObject<T> blockRegistryObject = BLOCKS.register(blockID, block);
		ModItems.ITEMS.register(blockID, () -> createBlockItem(blockRegistryObject));

		return blockRegistryObject;
	}

	// Create a sapling with the given AbstractTreeGrower and the properties of the vanilla OAK_SAPLING
	private static SaplingBlock createSapling(AbstractTreeGrower treeGrower){
		return new SaplingBlock(treeGrower, BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING));
	}
	// Create a Flowering leaves block with the properties of the vanilla OAK_LEAVES
	private static FloweringLeavesBlock createFloweringLeaves() {
		return new FloweringLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES));
	}
	// Create a log or wood block with the properties of the given block
	private static Block createWoodOrLogBlock(Block copyFrom) {
		return new CustomWood(BlockBehaviour.Properties.copy(copyFrom).strength(2.0F)
//					.mapColor(CustomWood.woodMapColor(topMapColor,sideMapColor))
		);
	}
	// Create a planks block with the properties of the vanilla OAK_PLANKS
	private static Block createPlanks() {
		return new CustomWood(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS));
	}
	// Create a planks block with the properties of the vanilla OAK_PLANKS
	private static Block createLeaves() {
		return new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES));
	}

	// REGISTER BLOCKS //

	public static final RegistryObject<Block> APPLE_SAPLING =
			registerBlock("apple_sapling", () -> createSapling(new AppleTreeGrower()));
	public static final RegistryObject<Block> APPLE_LEAVES =
		registerBlock("apple_leaves", ModBlocks::createLeaves);
	public static final RegistryObject<Block> FLOWERING_APPLE_LEAVES =
		registerBlock("flowering_apple_leaves", ModBlocks::createFloweringLeaves);
	public static final RegistryObject<Block> APPLEWOOD_LOG =
		registerBlock("applewood_log", () -> createWoodOrLogBlock(Blocks.OAK_LOG));
	public static final RegistryObject<Block> APPLEWOOD_WOOD =
		registerBlock("applewood_wood", () -> createWoodOrLogBlock(Blocks.OAK_WOOD));
	public static final RegistryObject<Block> STRIPPED_APPLEWOOD_LOG =
		registerBlock("stripped_applewood_log", () -> createWoodOrLogBlock(Blocks.OAK_LOG));
	public static final RegistryObject<Block> STRIPPED_APPLEWOOD_WOOD =
		registerBlock("stripped_applewood_wood", () -> createWoodOrLogBlock(Blocks.OAK_WOOD));
	public static final RegistryObject<Block> APPLEWOOD_PLANKS =
		registerBlock("applewood_planks", ModBlocks::createPlanks);

	// A method that will register the DeferredRegister<Block> to the mod event bus
	public static void register(IEventBus eventBus){
		BLOCKS.register(eventBus);
	}

}
