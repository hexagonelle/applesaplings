package net.hexagonelle.applesaplings.plugins.jade;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.minecraft.resources.ResourceLocation;

public interface AppleSaplingsJadeIds {

	ResourceLocation FLOWERING_LEAVES_PROGRESS =
		SAPPLINGS("flowering_leaves_progress");

	static ResourceLocation SAPPLINGS(String path) {
		return new ResourceLocation(AppleSaplings.MODID, path);
	}

}
