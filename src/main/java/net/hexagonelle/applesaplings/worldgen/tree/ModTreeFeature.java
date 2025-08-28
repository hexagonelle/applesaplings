package net.hexagonelle.applesaplings.worldgen.tree;

import com.mojang.serialization.Codec;
import net.hexagonelle.applesaplings.worldgen.ModFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class ModTreeFeature extends ModFeature<TreeConfiguration> {

	public ModTreeFeature(Codec<TreeConfiguration> pCodec) {
		super(pCodec);
	}

	@Override
	public boolean place(FeaturePlaceContext<TreeConfiguration> pContext) {
		return false;
	}
}

