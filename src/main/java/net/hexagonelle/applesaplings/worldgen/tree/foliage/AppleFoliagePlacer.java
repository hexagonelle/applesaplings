package net.hexagonelle.applesaplings.worldgen.tree.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;

//// CURRENTLY UNUSED ////

public class AppleFoliagePlacer extends CherryFoliagePlacer {
	public static final Codec<AppleFoliagePlacer> CODEC =
		RecordCodecBuilder.create(
			foliagePlacerInstance -> foliagePlacerParts(foliagePlacerInstance)
				.and(foliagePlacerInstance.group(
					IntProvider.codec(4, 16).fieldOf("height")
						.forGetter(placerInstance -> placerInstance.height),
					Codec.floatRange(0.0F, 1.0F).fieldOf("wide_bottom_layer_hole_chance")
						.forGetter(placerInstance -> placerInstance.wideBottomLayerHoleChance),
					Codec.floatRange(0.0F, 1.0F).fieldOf("corner_hole_chance")
						// error in vanilla cherry foliage placer? this original read
						// placerInstance.wideBottomLayerHoleChance
						.forGetter(placerInstance -> placerInstance.cornerHoleChance),
					Codec.floatRange(0.0F, 1.0F).fieldOf("hanging_leaves_chance")
						.forGetter(placerInstance -> placerInstance.hangingLeavesChance),
					Codec.floatRange(0.0F, 1.0F).fieldOf("hanging_leaves_extension_chance")
						.forGetter(placerInstance -> placerInstance.hangingLeavesExtensionChance)
				)).apply(foliagePlacerInstance, AppleFoliagePlacer::new)
		);

	private final IntProvider height;
	private final float wideBottomLayerHoleChance;
	private final float cornerHoleChance;
	private final float hangingLeavesChance;
	private final float hangingLeavesExtensionChance;

	public AppleFoliagePlacer(
		IntProvider radius, IntProvider offset, IntProvider height,
		float bottomHoleChance, float cornerHoleChance,
		float hangingLeavesChance, float leavesExtendChance) {
		super(
			radius, offset, height,
			bottomHoleChance, cornerHoleChance,
			hangingLeavesChance, leavesExtendChance);
		this.height = height;
		this.wideBottomLayerHoleChance = bottomHoleChance;
		this.cornerHoleChance = cornerHoleChance;
		this.hangingLeavesChance = hangingLeavesChance;
		this.hangingLeavesExtensionChance = leavesExtendChance;
	}


	@Override
	protected @NotNull FoliagePlacerType<?> type() {
		return ModFoliagePlacers.APPLE_FOLIAGE_PLACER.get();
	}

	@Override
	protected void createFoliage(
		@NotNull LevelSimulatedReader level,
		@NotNull FoliageSetter blockSetter,
		@NotNull RandomSource randomSource,
		@NotNull TreeConfiguration config,
		int maxHeight, FoliageAttachment attachment,
		int foliageHeight, int foliageRadius, int offset
	){
		this.placeLeavesRow(
			level, blockSetter,
			randomSource, config,
			attachment.pos().above(0),
			2, 2,
			attachment.doubleTrunk());
		this.placeLeavesRow(
			level, blockSetter,
			randomSource, config,
			attachment.pos().above(1),
			2, 2,
			attachment.doubleTrunk());
		this.placeLeavesRow(
			level, blockSetter,
			randomSource, config,
			attachment.pos().above(2),
			2, 2,
			attachment.doubleTrunk());
	}

	@Override
	public int foliageHeight(
		@NotNull RandomSource randomSource,
		int height,
		@NotNull TreeConfiguration pConfig
	){
		return this.height.sample(randomSource);
	}

	@Override
	protected boolean shouldSkipLocation(
		@NotNull RandomSource randomSource,
		int localX, int localY, int localZ,
		int range, boolean isLarge
	){
		return false;
	}

}
