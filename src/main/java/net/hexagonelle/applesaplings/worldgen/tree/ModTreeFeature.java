package net.hexagonelle.applesaplings.worldgen.tree;

import com.mojang.serialization.Codec;
import net.hexagonelle.applesaplings.worldgen.ModFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class ModTreeFeature extends ModFeature<ModTreeConfiguration> {


	public ModTreeFeature(Codec<ModTreeConfiguration> pCodec) {
		super(pCodec);
	}

	@Override
	public boolean place(FeaturePlaceContext<ModTreeConfiguration> pContext) {
		return false;
	}
}

