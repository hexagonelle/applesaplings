package net.hexagonelle.applesaplings.worldgen;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.hexagonelle.applesaplings.worldgen.tree.AppleFoliagePlacer;
import net.hexagonelle.applesaplings.worldgen.tree.FloweringLeavesDecorator;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {

	public static ResourceKey<ConfiguredFeature<?,?>> registerKey(String name){
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(AppleSaplings.MODID, name));
	}

	public static <FC extends FeatureConfiguration, F extends ModFeature<FC>> void register(
			BootstapContext<ConfiguredFeature<?,?>> context,
			ResourceKey<ConfiguredFeature<?,?>> key, F feature, FC configuration
	){
		context.register(key, new ConfiguredFeature<>(feature, configuration));
	}

	public static final ResourceKey<ConfiguredFeature<?,?>> APPLE_TREE = registerKey("apple_tree");

	private static IntProvider constIntProvider(int integer){
		return ConstantInt.of(integer);
	}

	public static void bootstrap(BootstapContext<ConfiguredFeature<?,?>> context) {

		List<TreeDecorator> decoratorList = List.of(new FloweringLeavesDecorator(1));


		TreeConfiguration appleTreeBuilder =
			new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(ModBlocks.APPLEWOOD_LOG.get()),
				new StraightTrunkPlacer(3,2,1),

				BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),

				// args are:
				// radius, offset, height,
				// bottomHoleChance, cornerHoleChance,
				// hangingLeavesChance, hangingLeavesExtensionChance
				new AppleFoliagePlacer(
					constIntProvider(3),constIntProvider(0),constIntProvider(3),
					0.2F,0.2F,
					0.2F,0.2F
				),
				new TwoLayersFeatureSize(1,0,2)

			).decorators(decoratorList).build();

		register(context, APPLE_TREE, ModFeature.FLOWERING_TREE, appleTreeBuilder);

	}
}
