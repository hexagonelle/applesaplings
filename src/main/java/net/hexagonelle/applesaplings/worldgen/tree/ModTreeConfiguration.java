package net.hexagonelle.applesaplings.worldgen.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

import java.util.List;
import java.util.Optional;

public class ModTreeConfiguration extends TreeConfiguration {

	public static final Codec<ModTreeConfiguration> CODEC =
		RecordCodecBuilder.create(
			p_225468_ -> p_225468_.group(
				BlockStateProvider.CODEC.fieldOf("trunk_provider")
					.forGetter(p_161248_ -> p_161248_.trunkProvider),
				TrunkPlacer.CODEC.fieldOf("trunk_placer")
					.forGetter(p_161246_ -> p_161246_.trunkPlacer),
				BlockStateProvider.CODEC.fieldOf("foliage_provider")
					.forGetter(p_161244_ -> p_161244_.foliageProvider),
				FoliagePlacer.CODEC.fieldOf("foliage_placer")
					.forGetter(p_191357_ -> p_191357_.foliagePlacer),
				BlockStateProvider.CODEC.fieldOf("flowering_foliage_provider")
					.forGetter(p_123691_ -> p_123691_.floweringFoliageProvider),
				FoliagePlacer.CODEC.fieldOf("flowering_foliage_placer")
					.forGetter(p_026450_ -> p_026450_.floweringFoliagePlacer),
				RootPlacer.CODEC.optionalFieldOf("root_placer")
					.forGetter(p_225478_ -> p_225478_.rootPlacer),
				BlockStateProvider.CODEC.fieldOf("dirt_provider")
					.forGetter(p_225476_ -> p_225476_.dirtProvider),
				FeatureSize.CODEC.fieldOf("minimum_size")
					.forGetter(p_225474_ -> p_225474_.minimumSize),
				TreeDecorator.CODEC.listOf().fieldOf("decorators")
					.forGetter(p_225472_ -> p_225472_.decorators),
				Codec.BOOL.fieldOf("ignore_vines").orElse(false)
					.forGetter(p_161232_ -> p_161232_.ignoreVines),
				Codec.BOOL.fieldOf("force_dirt").orElse(false)
					.forGetter(p_225470_ -> p_225470_.forceDirt)
			).apply(p_225468_, ModTreeConfiguration::new));

	public final BlockStateProvider floweringFoliageProvider;
	public final FoliagePlacer floweringFoliagePlacer;

	protected ModTreeConfiguration(
		BlockStateProvider trunkProvider,
		TrunkPlacer trunkPlacer,
		BlockStateProvider foliageProvider,
		FoliagePlacer foliagePlacer,
		BlockStateProvider floweringFoliageProvider,
		FoliagePlacer floweringFoliagePlacer,
		Optional<RootPlacer> rootPlacer,
		BlockStateProvider dirtProvider,
		FeatureSize minSize,
		List<TreeDecorator> decorators,
		boolean ignoreVines,
		boolean forceDirt
	){
		super(
			trunkProvider, trunkPlacer,
			foliageProvider, foliagePlacer,
			rootPlacer,
			dirtProvider,
			minSize,
			decorators,
			ignoreVines,
			forceDirt
		);
		this.floweringFoliageProvider = floweringFoliageProvider;
		this. floweringFoliagePlacer = floweringFoliagePlacer;
	}
}
