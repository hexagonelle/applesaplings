package net.hexagonelle.applesaplings.creativetabs;

public class ModCreativeTabs {

	static{

		CreativeTabRegistry.registerCreativeTab(
			"applesaplings_tab",
			() -> CreativeTabSuppliers.createCreativeTab(
				"applesaplings_tab",
				"apple_sapling"
			)
		);

	}
}
