package net.hexagonelle.applesaplings.worldgen.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;

public class AppleFoliagePlacer extends FoliagePlacer {
	public static final Codec<AppleFoliagePlacer> CODEC =
		RecordCodecBuilder.create(
			foliagePlacerInstance ->
				foliagePlacerParts(foliagePlacerInstance)
					.and(Codec.intRange(0, 16)
					.fieldOf("height")
					.forGetter(fp -> fp.height))
					.apply(foliagePlacerInstance, AppleFoliagePlacer::new)
		);

	private final int height;

	public AppleFoliagePlacer(IntProvider pRadius, IntProvider pOffset, int height) {
		super(pRadius, pOffset);
		this.height = height;
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
		return this.height;
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
