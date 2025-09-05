package net.hexagonelle.examplemod.items;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class ItemSuppliers {

	public static Supplier<Item> simpleItem(){
		return () -> new Item(new Item.Properties());
	}

	// A method that creates a new BlockItem, given some RegistryObject<Block>
	public static <T extends Block> Item createBlockItem(Supplier<T> block){
		return new BlockItem(block.get(), new Item.Properties());
	}

}
