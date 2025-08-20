package net.hexagonelle.applesaplings.util;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {

	public static final WoodType APPLEWOOD =
		WoodType.register(
			new WoodType(
				AppleSaplings.MODID + ":applewood",
				BlockSetType.OAK
			)
		);

}
