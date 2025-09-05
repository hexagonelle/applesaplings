package net.hexagonelle.examplemod.creativetabs;

import net.hexagonelle.examplemod.items.ItemRegistry;

public class ModCreativeTabs {

	static{
		CreativeTabRegistry.registerCreativeTab(
			"apple_saplings",
			() -> CreativeTabSuppliers.createCreativeTab(
				"apple_saplings", ItemRegistry.ITEM_MAP.get("iron_stick")
			)
		);
	}

}
