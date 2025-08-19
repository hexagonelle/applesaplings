package net.hexagonelle.applesaplings.blocks;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.custom.FruitingLeavesBlock;
import net.hexagonelle.applesaplings.items.ModItems;
import net.hexagonelle.applesaplings.worldgen.tree.AppleTreeGrower;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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

	// A method that creates the corresponding BlockItem and registers both Block and BlockItem under the blockID.
	private static <T extends Block> RegistryObject<Item> registerBlockItem(String blockID, Supplier<T> block){
		return ModItems.ITEMS.register(blockID, () -> new BlockItem(block.get(), new Item.Properties()));
	}

	// A method that creates a new BlockItem, given some RegistryObject<Block>
	// Why write "Supplier<T>" here? Why not RegistryObject<Block>, since that's the type we're passing in?
	private static <T extends Block> Item createBlockItem(Supplier<T> block){
		return new BlockItem(block.get(), new Item.Properties());
	}

	// WARNING
	// EVEN THOUGH THE VARIABLE block HERE IS A Supplier<T> FOR <T extends Block>
	// AND EVEN THOUGH THE FUNCTION registerBlockItem TAKES A Supplier<T> FOR <T extends Block> AS ITS 2ND INPUT
	// THE CODE WILL CRASH UNLESS YOU GIVE A RegistryObject<T> FOR <T extends Block>
	// AS THE SECOND ARGUMENT OF registerBlockItem
	// I DON'T KNOW WHY

	// A SIMILAR ISSUE HAPPENS IF YOU PASS A Supplier<T> block AS THE ARGUMENT
	// OF createBlockItem
	// SUGGESTING THAT THIS HAS SOMETHING TO DO WITH THE FIRST ARGUMENT
	// OF BlockItem(block.get(), new Item.Properties())

	// A method that creates the corresponding BlockItem and registers both Block and BlockItem under the blockID.
	private static <T extends Block> RegistryObject<T> registerBlock(String blockID, Supplier<T> block){
		RegistryObject<T> blockRegistryObject = BLOCKS.register(blockID, block);


		/////////////////////
		// Here, the first line doesn't work
		// The error says "Registry Object not present" ^*
		// Item blockItem = new BlockItem(blockRegistryObject.get(), new Item.Properties());
		// ModItems.ITEMS.register(blockID, () -> blockItem);
		/////////////////////
		// The following gives an error of "No delegate exists for value Block{minecraft:air}"
		// even though block is of type Supplier<T> with <T extends Block>
		// and the argument for createBlockItem IS of type Supplier<T> with <T extends Block>
		/////////////////////
		// ^* but if that's true, why does this work???
		ModItems.ITEMS.register(blockID, () -> createBlockItem(blockRegistryObject));
		/////////////////////
		// No idea why this one doesn't work???
		// RegistryObject<Item> itemRegistryObject = registerBlockItem(blockID, blockRegistryObject);
		/////////////////////
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

	// REGISTER BLOCKS //

	public static final RegistryObject<Block> APPLE_SAPLING =
			registerBlock(
					"apple_sapling",
					// The syntax () -> does something to make this a supplier
					// but I don't actually know what that means
					() -> new SaplingBlock(new AppleTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING))
			);

	public static final RegistryObject<Block> APPLE_LEAVES =
			registerBlock(
					"apple_leaves",
					() -> new FruitingLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES))
			);

	// A method that will register the DeferredRegister<Block> to the mod event bus so blocks get registered
	// Presumably, when the DeferredRegister<Item> gets registered to the mod event bus,
	// it will "remember" that we registered the BlockItems
	// that corresponds to the Blocks we registered here.

	public static void register(IEventBus eventBus){
		BLOCKS.register(eventBus);
	}

}
