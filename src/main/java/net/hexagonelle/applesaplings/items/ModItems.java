package net.hexagonelle.applesaplings.items;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.hexagonelle.applesaplings.blocks.ModBlocks.*;

public class ModItems {
	// Create a Deferred Register to hold Item objects which will all be registered under the "applesaplings" namespace
	public static final DeferredRegister<Item> ITEMS =
		DeferredRegister.create(ForgeRegistries.ITEMS, AppleSaplings.MODID);

	// A method that will register the DeferredRegister to the mod event bus so items get registered
	public static void register(IEventBus eventBus){
		ITEMS.register(eventBus);
	}

	// METHODS FOR REGISTERING ITEMS //

	private static <T extends Item> RegistryObject<T> registerItem(String itemID, Supplier<T> item){
		return ITEMS.register(itemID, item);
	}


	public static final RegistryObject<Item> APPLEWOOD_SIGN = registerItem("applewood_sign",
		() -> ItemCreator.createSignItem(ModBlocks.APPLEWOOD_SIGN, APPLEWOOD_WALL_SIGN)
	);

	public static final RegistryObject<Item> APPLEWOOD_HANGING_SIGN = registerItem("applewood_hanging_sign",
		() -> ItemCreator.createHangingSignItem(ModBlocks.APPLEWOOD_HANGING_SIGN, APPLEWOOD_WALL_HANGING_SIGN)
	);

	public static final RegistryObject<Item> APPLEWOOD_BOAT =
		registerItem("applewood_boat", ItemCreator::createBoatItem);
	public static final RegistryObject<Item> APPLEWOOD_CHEST_BOAT =
		registerItem("applewood_chest_boat", ItemCreator::createChestBoatItem);
}