package net.hexagonelle.applesaplings.blocks;

import net.hexagonelle.applesaplings.worldgen.tree.AppleTreeGrower;

import static net.hexagonelle.applesaplings.blocks.BlockRegistry.*;

public class ModBlocks {

	// REGISTER BLOCKS //

	static {
		registerNewWoodType(
			"applewood",
			"apple",
			"apple",
			new AppleTreeGrower(),
			"applesaplings_tab"
		);
	}

}
