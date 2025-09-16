package net.hexagonelle.applesaplings.items;

import static net.hexagonelle.applesaplings.items.ItemRegistry.*;

public class ModItems {

	// REGISTER ITEMS //

	static {
		registerItemWithModTab("applewood_boat", ItemSuppliers::createBoatItem, "apple_saplings");
		registerItemWithModTab("applewood_chest_boat", ItemSuppliers::createChestBoatItem, "apple_saplings");
	}

}