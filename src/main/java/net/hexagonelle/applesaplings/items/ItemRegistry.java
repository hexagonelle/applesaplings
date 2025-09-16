package net.hexagonelle.applesaplings.items;

import net.hexagonelle.applesaplings.Constants;
import net.hexagonelle.applesaplings.creativetabs.CreativeTabRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.function.Supplier;

public class ItemRegistry {

	// Creates the register that holds the items
	public static final DeferredRegister<Item> ITEMS =
		DeferredRegister.create(Registries.ITEM, Constants.MODID);

	// A method that will register the DeferredRegister<Item> to the mod event bus
	public static void register(IEventBus eventBus){ITEMS.register(eventBus);}

	// Creates a hashmap so that we can refer to a RegistryObject by a string
	public static final HashMap<String, RegistryObject<Item>> ITEM_MAP = new HashMap<>();

	// registers an item and places the corresponding RegistryObject in the HashMap by itemId
	public static RegistryObject<Item> registerItem(
		String itemId,
		Supplier<Item> itemSupplier
	){
		return ITEM_MAP.put(itemId, ITEMS.register(itemId,itemSupplier));
	}

	public static void registerBlockItem(
		String itemId
	){
		registerItem(itemId,()-> ItemSuppliers.createBlockItem(itemId));
	}

	public static void registerItemWithModTab(
		String itemId,
		Supplier<Item> itemSupplier,
		String creativeTabKey
	){
		CreativeTabRegistry.ITEM_MODTAB_MAP.put(registerItem(itemId, itemSupplier),creativeTabKey);
	}

	public static void registerBlockItemWithModTab(
		String itemId,
		String creativeTabKey
	){
		registerItemWithModTab(itemId,()-> ItemSuppliers.createBlockItem(itemId),creativeTabKey);
	}

	public static void registerItemWithVanillaTab(
		String itemId,
		Supplier<Item> itemSupplier,
		CreativeModeTab creativeTab
	){
		CreativeTabRegistry.ITEM_VANILLATAB_MAP.put(registerItem(itemId, itemSupplier),creativeTab);
	}

	public static void registerBlockItemWithVanillaTab(
		String itemId,
		CreativeModeTab creativeTab
	){
		registerItemWithVanillaTab(itemId,()-> ItemSuppliers.createBlockItem(itemId),creativeTab);
	}

	public static void registerSign(String woodType, String creativeTabKey){
		registerItemWithModTab(woodType+"_sign",() -> ItemSuppliers.createSignItem(woodType),creativeTabKey);
	}

	public static void registerHangingSign(String woodType, String creativeTabKey){
		registerItemWithModTab(
			woodType+"_hanging_sign",
			() -> ItemSuppliers.createHangingSignItem(woodType),creativeTabKey);
	}

}
