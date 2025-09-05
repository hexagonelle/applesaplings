package net.hexagonelle.examplemod.creativetabs;

import net.hexagonelle.examplemod.Constants;
import net.hexagonelle.examplemod.registration.RegistrationProvider;
import net.hexagonelle.examplemod.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.function.Supplier;

public class CreativeTabRegistry {
	public static void init() {}

	// Creates the register that holds the items
	public static final RegistrationProvider<CreativeModeTab> CREATIVETABS =
		RegistrationProvider.get(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);

	// Creates a hashmap so that we can refer to a RegistryObject by a string
	public static final HashMap<String, RegistryObject<CreativeModeTab>> CREATIVETAB_MAP = new HashMap<>();

	// registers an item and places the corresponding RegistryObject in the HashMap by itemId
	public static void registerCreativeTab(
		String creativeTabId,
		Supplier<CreativeModeTab> creativeTabSupplier
	){
		CREATIVETAB_MAP.put(creativeTabId, CREATIVETABS.register(creativeTabId,creativeTabSupplier));
	}

	// Map for items and their corresponding mod creative tabs
	public static final HashMap<RegistryObject<Item>,String> ITEM_MODTAB_MAP = new HashMap<>();
	// Map for items and their corresponding vanilla creative tabs
	public static final HashMap<RegistryObject<Item>,CreativeModeTab> ITEM_VANILLATAB_MAP = new HashMap<>();

}
