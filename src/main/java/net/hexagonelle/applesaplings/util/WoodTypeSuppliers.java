package net.hexagonelle.applesaplings.util;

import net.hexagonelle.applesaplings.Constants;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class WoodTypeSuppliers {

	public static WoodType createWoodType(
		String woodTypeId
	){
		return new WoodType(Constants.MODID + ":" + woodTypeId, BlockSetType.OAK);
	}

}
