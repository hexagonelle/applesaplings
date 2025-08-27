package net.hexagonelle.applesaplings.worldgen;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.hexagonelle.applesaplings.worldgen.tree.AppleFoliagePlacer;
import net.hexagonelle.applesaplings.worldgen.tree.ModTreeConfiguration;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class ModConfiguredFeatures {

	public static ResourceKey<ConfiguredFeature<?,?>> registerKey(String name){
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(AppleSaplings.MODID, name));
	}

	public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
			BootstapContext<ConfiguredFeature<?,?>> context,
			ResourceKey<ConfiguredFeature<?,?>> key, F feature, FC configuration
	){
		context.register(key, new ConfiguredFeature<>(feature, configuration));
	}

	public static final ResourceKey<ConfiguredFeature<?,?>> APPLE_SAPLING_RESOURCE_KEY = registerKey("apple_tree");

	public static void bootstrap(BootstapContext<ConfiguredFeature<?,?>> context) {

		register(
			context,
			APPLE_SAPLING_RESOURCE_KEY,
			Feature.TREE,
			new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(ModBlocks.APPLEWOOD_LOG.get()),
				new StraightTrunkPlacer(3,2,1),

				BlockStateProvider.simple(ModBlocks.FLOWERING_APPLE_LEAVES.get()),
				new AppleFoliagePlacer(ConstantInt.of(3),ConstantInt.of(2),3),

				new TwoLayersFeatureSize(1,0,2)).build()
		);

		register(
			context,
			APPLE_SAPLING_RESOURCE_KEY,
			Feature.TREE,
			new ModTreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(ModBlocks.APPLEWOOD_LOG.get()),
				new StraightTrunkPlacer(3,2,1),

				BlockStateProvider.simple(ModBlocks.FLOWERING_APPLE_LEAVES.get()),
				new AppleFoliagePlacer(ConstantInt.of(3),ConstantInt.of(2),3),

				new TwoLayersFeatureSize(1,0,2)).build()
		);
	}
}
