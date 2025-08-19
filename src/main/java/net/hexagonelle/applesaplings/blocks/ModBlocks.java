package net.hexagonelle.applesaplings.blocks;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.items.ModItems;
import net.hexagonelle.applesaplings.worldgen.tree.AppleTreeGrower;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
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
	// Create a fruiting leaves block with the properties of the vanilla OAK_LEAVES
	private static FruitingLeavesBlock createFruitingLeaves() {
		return new FruitingLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES));
	}
	// Create a log block with the properties of the vanilla OAK_LOG
	private static Block createLogBlock() {
			return new CustomWood(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2.0F)
//					.mapColor(CustomWood.woodMapColor(topMapColor,sideMapColor))
			);
	}

	// REGISTER BLOCKS //

	public static final RegistryObject<Block> APPLE_SAPLING =
			registerBlock("apple_sapling", () -> createSapling(new AppleTreeGrower()));
	public static final RegistryObject<Block> APPLE_LEAVES =
		registerBlock("apple_leaves", ModBlocks::createFruitingLeaves);
	public static final RegistryObject<Block> APPLEWOOD_LOG =
		registerBlock("applewood_log", ModBlocks::createLogBlock);

	// A method that will register the DeferredRegister<Block> to the mod event bus
	public static void register(IEventBus eventBus){
		BLOCKS.register(eventBus);
	}

}
