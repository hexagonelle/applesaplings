package net.hexagonelle.applesaplings.worldgen;

import com.mojang.serialization.Codec;
import net.hexagonelle.applesaplings.worldgen.tree.ModTreeConfiguration;
import net.hexagonelle.applesaplings.worldgen.tree.ModTreeFeature;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public abstract class ModFeature<FC extends FeatureConfiguration> extends Feature<FC> {

	public static final ModFeature<TreeConfiguration> FLOWERING_TREE =
		register("flowering_tree", new ModTreeFeature(TreeConfiguration.CODEC));

	public ModFeature(Codec<FC> pCodec) {
		super(pCodec);
	}
	private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String key, F value) {
		return Registry.register(BuiltInRegistries.FEATURE, key, value);
	}
}
