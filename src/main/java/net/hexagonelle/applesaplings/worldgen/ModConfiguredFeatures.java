package net.hexagonelle.applesaplings.worldgen;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.worldgen.tree.decorators.FloweringLeavesDecorator;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static net.hexagonelle.applesaplings.blocks.ModBlocks.*;

public class ModConfiguredFeatures {

	// method to create a resource key for a feature
	public static ResourceKey<ConfiguredFeature<?,?>> registerKey(String name){
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(AppleSaplings.MODID, name));
	}

	// method to register a feature
	public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
			BootstapContext<ConfiguredFeature<?,?>> context,
			ResourceKey<ConfiguredFeature<?,?>> key, F feature, FC configuration
	){
		context.register(key, new ConfiguredFeature<>(feature, configuration));
	}

	// HELPER METHODS //

	// an integer provider that always returns the same value
	private static IntProvider constIntProvider(int integer){
		return ConstantInt.of(integer);
	}

	// creates a flowering leaves decorator
	private static List<TreeDecorator> createFloweringDecorator(
		Float spawnChance,
		int spawnAttempts,
		RegistryObject<Block> block
	){
		return List.of(
			new FloweringLeavesDecorator(
				spawnChance,
				spawnAttempts,
				BlockStateProvider.simple(block.get()))
		);
	}

	// creates a builder for flowering trees
	private static TreeConfiguration floweringTreeBuilder(
		RegistryObject<Block> treeTrunk,
		RegistryObject<Block> leavesBlock,
		RegistryObject<Block> floweringLeavesBlock,
		Float floweringChance,
		int floweringRolls
	){
		return new TreeConfiguration.TreeConfigurationBuilder(
			BlockStateProvider.simple(treeTrunk.get()),

			new StraightTrunkPlacer(
				5, // base height
				2, // random height A
				1	 // random height B
			),

			BlockStateProvider.simple(leavesBlock.get()),

			new BlobFoliagePlacer(
				constIntProvider(3), // radius
				constIntProvider(0), // offset
				3														// height
			),

			new TwoLayersFeatureSize(1,0,2)
		).decorators(
			createFloweringDecorator(
				floweringChance,
				floweringRolls,
				floweringLeavesBlock
			)
		).build();
	}

	// tree builders //

	private static final TreeConfiguration appleTreeBuilder = floweringTreeBuilder(
		APPLEWOOD_LOG, APPLE_LEAVES, FLOWERING_APPLE_LEAVES,
		0.1F, 3
	);

	// REGISTER FEATURES //

	// register the key for the apple tree feature
	public static final ResourceKey<ConfiguredFeature<?,?>> APPLE_TREE = registerKey("apple_tree");

	public static void bootstrap(BootstapContext<ConfiguredFeature<?,?>> context) {
		register(context, APPLE_TREE, Feature.TREE, appleTreeBuilder);
	}

}
