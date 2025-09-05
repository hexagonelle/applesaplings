package net.hexagonelle.examplemod.items;

import static net.hexagonelle.examplemod.items.ItemRegistry.registerItem;

public class ModItems {

	static {
		registerItem("iron_stick", ItemSuppliers.simpleItem());
	}

}
