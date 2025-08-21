package net.hexagonelle.applesaplings.items;

import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ItemCreator {

	// A method that creates a new BlockItem, given some RegistryObject<Block>
	public static <T extends Block> Item createBlockItem(Supplier<T> block){
		return new BlockItem(block.get(), new Item.Properties());
	}

	public static Item createSignItem(
		RegistryObject<Block> floorSign,
		RegistryObject<Block> wallSign
	){
		return new SignItem(
			new Item.Properties().stacksTo(16),
			floorSign.get(), wallSign.get()
		);
	}

	public static Item createHangingSignItem(
		RegistryObject<Block> hangingSign,
		RegistryObject<Block> wallHangingSign
	){
		return new HangingSignItem(
			hangingSign.get(), wallHangingSign.get(),
			new Item.Properties().stacksTo(16)
		);
	}

}
