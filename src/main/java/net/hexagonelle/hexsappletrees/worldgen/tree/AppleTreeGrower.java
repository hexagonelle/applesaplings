package net.hexagonelle.hexsappletrees.worldgen.tree;

import net.hexagonelle.hexsappletrees.worldgen.ModConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class AppleTreeGrower extends AbstractTreeGrower {


	@Override
	protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean b) {
		return ModConfiguredFeatures.APPLE_SAPLING_RESOURCE_KEY;
	}
}
