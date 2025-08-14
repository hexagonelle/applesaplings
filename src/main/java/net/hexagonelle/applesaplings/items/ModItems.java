package net.hexagonelle.applesaplings.items;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.minecraft.world.food.FoodProperties;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraft.world.item.Item;

public class ModItems {
	// Create a Deferred Register to hold Items which will all be registered under the "applesaplings" namespace
	public static final DeferredRegister<Item> ITEMS =
			DeferredRegister.create(ForgeRegistries.ITEMS, AppleSaplings.MODID);

	// Creates a new food item with the id "applesaplings:example_id", nutrition 1 and saturation 2
	public static final RegistryObject<Item> EXAMPLE_ITEM =
			ITEMS.register("example_item", () ->
				new Item(new Item.Properties().food(new FoodProperties.Builder()
				.alwaysEat().nutrition(1).saturationMod(2f).build()))
			);

	// A method that will register the DeferredRegister to the mod event bus so items get registered
	public static void register(IEventBus eventBus){
		ITEMS.register(eventBus);
	}
}