package net.hexagonelle.applesaplings.worldgen.tree.decorators;

import com.mojang.serialization.Codec;

import java.util.List;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.jetbrains.annotations.NotNull;

public class FloweringLeavesDecorator extends TreeDecorator {
	public static final Codec<FloweringLeavesDecorator> CODEC =
		RecordCodecBuilder.create(builder -> builder.group(
			Codec.floatRange(0.0F, 1.0F).fieldOf("probability")
				.forGetter((instance) -> instance.probability),
			Codec.intRange(1, 16).fieldOf("rolls")
				.forGetter((instance) -> instance.rolls),
			BlockStateProvider.CODEC.fieldOf("floweringLeaves")
				.forGetter((instance) -> instance.floweringLeavesProvider)
		).apply(builder, FloweringLeavesDecorator::new));

	/** Probability to generate flowering leaves on each roll */
	protected final float probability;
	/** Number of attempts to generate flowering leaves per valid position */
	protected final int rolls;
	/** FloweringLeavesBlock to spawn as decorator */
	protected final BlockStateProvider floweringLeavesProvider;

	public FloweringLeavesDecorator(float spawnChance, int spawnAttempts, BlockStateProvider blockProvider) {
		this.probability = spawnChance;
		this.rolls = spawnAttempts;
		this.floweringLeavesProvider = blockProvider;
	}

	// check if the block in a given direction is
	private boolean isAirInDirection(
		TreeDecorator.@NotNull Context context,
		BlockPos blockPos,
		Direction direction
	){
		return context.isAir(blockPos.relative(direction));
	}

	private boolean isAirAdjacent(
		TreeDecorator.@NotNull Context context,
		BlockPos blockPos
	){
		return
			isAirInDirection(context, blockPos, Direction.NORTH) ||
			isAirInDirection(context, blockPos, Direction.EAST) ||
			isAirInDirection(context, blockPos, Direction.SOUTH) ||
			isAirInDirection(context, blockPos, Direction.WEST) ||
			isAirInDirection(context, blockPos, Direction.UP) ||
			isAirInDirection(context, blockPos, Direction.DOWN);
	}


	protected @NotNull TreeDecoratorType<?> type() {
		return ModTreeDecorators.FLOWERING_LEAVES.get();
	}

	public void place(TreeDecorator.@NotNull Context context) {
		RandomSource randomSource = context.random();
		List<BlockPos> leavesList = context.leaves();

		List<BlockPos> spawnOptions =
			leavesList.stream().filter(
				positionOption -> isAirAdjacent(context,positionOption)
			).toList();

			if (!spawnOptions.isEmpty()) {
				spawnOptions.forEach((option) -> {
					for (int k = 0; k < rolls; ++k) {
						if (randomSource.nextFloat() < this.probability) {
							BlockPos blockPos = new BlockPos(option.getX(), option.getY(), option.getZ());
							context.setBlock(
								blockPos,
								this.floweringLeavesProvider.getState(randomSource,blockPos));
						}
					}
				});
			}
	}
}
