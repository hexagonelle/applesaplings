package net.hexagonelle.applesaplings.blocks;

import net.hexagonelle.applesaplings.Constants;
import net.hexagonelle.applesaplings.worldgen.tree.AppleTreeGrower;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;

import static net.hexagonelle.applesaplings.blocks.BlockRegistry.*;

public class ModBlocks {

	// REGISTER BLOCKS //

	// Creates the register that holds the blocks
	public static final DeferredRegister<Block> BLOCKS =
		DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MODID);

	// A method that will register the DeferredRegister<Block> to the mod event bus
	public static void register(IEventBus eventBus){BLOCKS.register(eventBus);}

	// Creates a hashmap so that we can refer to a RegistryObject by a string
	public static final HashMap<String, RegistryObject<Block>> BLOCK_MAP = new HashMap<>();

	public static void init(){
		registerNewWoodType(
			"applewood",
			"apple",
			"apple",
			new AppleTreeGrower(),
			"applesaplings_tab"
		);
	}

}
