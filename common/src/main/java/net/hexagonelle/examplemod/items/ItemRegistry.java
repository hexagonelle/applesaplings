package net.hexagonelle.examplemod.items;

import net.hexagonelle.examplemod.Constants;
import net.hexagonelle.examplemod.creativetabs.CreativeTabRegistry;
import net.hexagonelle.examplemod.registration.RegistrationProvider;
import net.hexagonelle.examplemod.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.function.Supplier;

public class ItemRegistry {
	public static void init() {}

	// Creates the register that holds the items
	public static final RegistrationProvider<Item> ITEMS =
		RegistrationProvider.get(Registries.ITEM, Constants.MOD_ID);

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
		String itemId,
		RegistryObject<Block> blockRegistryObject
	){
		registerItem(itemId,()-> ItemSuppliers.createBlockItem(blockRegistryObject));
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
		RegistryObject<Block> blockRegistryObject,
		String creativeTabKey
	){
		registerItemWithModTab(itemId,()-> ItemSuppliers.createBlockItem(blockRegistryObject),creativeTabKey);
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
		RegistryObject<Block> blockRegistryObject,
		CreativeModeTab creativeTab
	){
		registerItemWithVanillaTab(itemId,()-> ItemSuppliers.createBlockItem(blockRegistryObject),creativeTab);
	}


}
