package net.hexagonelle.applesaplings.items;

import net.hexagonelle.applesaplings.blocks.BlockRegistry;
import net.hexagonelle.applesaplings.entities.custom.ModBoat;
import net.hexagonelle.applesaplings.items.custom.ModBoatItem;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ItemSuppliers {

	public static Supplier<Item> simpleItem(){
		return () -> new Item(new Item.Properties());
	}

	// A method that creates a new BlockItem, given some RegistryObject<Block>
	public static <T extends Block> Item createBlockItem(String blockId){
		return new BlockItem(BlockRegistry.BLOCK_MAP.get(blockId).get(), new Item.Properties());
	}

	public static Item createSignItem(
		String woodType
	){
		return new SignItem(
			new Item.Properties().stacksTo(16),
			BlockRegistry.BLOCK_MAP.get(woodType+"_sign").get(),
			BlockRegistry.BLOCK_MAP.get(woodType+"_wall_sign").get()
		);
	}

	public static Item createHangingSignItem(
		String woodType
	){
		return new HangingSignItem(
			BlockRegistry.BLOCK_MAP.get(woodType+"_hanging_sign").get(),
			BlockRegistry.BLOCK_MAP.get(woodType+"_wall_hanging_sign").get(),
			new Item.Properties().stacksTo(16)
		);
	}

	public static Item createBoatItem(){
		return new ModBoatItem(false, ModBoat.Type.APPLEWOOD, new Item.Properties());
	}
	public static Item createChestBoatItem() {
		return new ModBoatItem(true, ModBoat.Type.APPLEWOOD, new Item.Properties());
	}

}
