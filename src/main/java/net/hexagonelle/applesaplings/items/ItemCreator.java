package net.hexagonelle.applesaplings.items;

import net.hexagonelle.applesaplings.blocks.entity.custom.ModBoat;
import net.hexagonelle.applesaplings.items.custom.ModBoatItem;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ItemCreator {

	// A method that creates a new BlockItem, given some RegistryObject<Block>
	public static <T extends Block> Item createBlockItem(Supplier<T> block){
		return new BlockItem(block.get(), new Item.Properties());
	}

	public static Item createSignItem(
		RegistryObject<Block> standingSign,
		RegistryObject<Block> wallSign
	){
		return new SignItem(
			new Item.Properties().stacksTo(16),
			standingSign.get(), wallSign.get()
		);
	}

	public static Item createHangingSignItem(
		RegistryObject<Block> ceilingHangingSign,
		RegistryObject<Block> wallHangingSign
	){
		return new HangingSignItem(
			ceilingHangingSign.get(), wallHangingSign.get(),
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
