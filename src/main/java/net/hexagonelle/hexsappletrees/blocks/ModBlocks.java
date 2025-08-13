package net.hexagonelle.hexsappletrees.blocks;

import net.hexagonelle.hexsappletrees.HexsAppleTrees;
import net.hexagonelle.hexsappletrees.custom.FruitingLeavesBlock;
import net.hexagonelle.hexsappletrees.items.ModItems;
import net.hexagonelle.hexsappletrees.worldgen.tree.AppleTreeGrower;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ModBlocks {
	// Create a Deferred Register to hold Blocks which will all be registered under the "hexsappletrees" namespace
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HexsAppleTrees.MODID);

	// A method that creates a new BlockItem with the id internalName, given some RegistryObject<Block>
	// Why write "Supplier<T>" here? Why not RegistryObject<Block>, since that's the type we're passing in?
	private static <T extends Block> RegistryObject<Item> registerBlockItem(String internalName, Supplier<T> block){
		return ModItems.ITEMS.register(internalName, () -> new BlockItem(block.get(), new Item.Properties()));
	}

	/* A method that will return a registry item for a block, when given the internal name of the block
	 * and the block registry object
	 */
	private static <T extends Block> RegistryObject<T> registerBlock(String internalName, Supplier<T> block){
		// The BLOCKS.register() method exists for DeferredRegisters,
		// in this case the DeferredRegister "BLOCKS", which holds objects of type "RegistryObject";
		// but specifically those RegistryObjects that map strings to objects of type "Block".
		// It appears to do two things:
		// it registers the block with that DeferredRegister,
		// and also returns a RegistryObject.
		// Does it not just return the RegistryObject that we passed into it?
		// Is that just for performing checks?
		RegistryObject<T> blockRegistryObject = BLOCKS.register(internalName, block);
		// provided that the ITEMS.register() method works the same way,
		// except that the DeferredRegister's RegistryObject's contents are of type "item",
		// then ITEMS.register() will do two things:
		// it will register the item with that DeferredRegister,
		// and also it will return the RegistryObject of type "item".
		// But we don't actually need to check the item we just registered,
		// so the only thing that matters is that the item *was in fact* registered.
		registerBlockItem(internalName, blockRegistryObject);

		return blockRegistryObject;
	}

	// now actually call the methods

	// Creates a new Block with the id "hexsappletrees:example_block", combining the namespace and path
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
