package net.hexagonelle.applesaplings.worldgen.tree;

import com.mojang.serialization.Codec;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.jetbrains.annotations.NotNull;

public class FloweringLeavesDecorator extends TreeDecorator {
	public static final Codec<FloweringLeavesDecorator> CODEC =
		Codec
			.floatRange(0.0F, 1.0F)
			.fieldOf("probability")
			.xmap(
				FloweringLeavesDecorator::new,
				decoratorInstance -> decoratorInstance.probability
			).codec();
	private static final Direction WORLDGEN_FACING = Direction.SOUTH;
	private static final Direction[] SPAWN_DIRECTIONS =
		// a Stream of all directions in the xz-plane
		// that is, [NORTH, EAST, SOUTH, WEST]
		Direction.Plane.HORIZONTAL
			.stream()
			// convert to an Array of all directions != NORTH
			// that is, [EAST, SOUTH, WEST]
			.filter(horizontalPlaneDirection -> horizontalPlaneDirection != WORLDGEN_FACING.getOpposite())
			.toArray(Direction[]::new);

	/** Probability to generate flowering leaves */
	private final float probability;

	public FloweringLeavesDecorator(float spawnChance) {
		this.probability = spawnChance;
	}

	protected @NotNull TreeDecoratorType<?> type() {
		return ModTreeDecorators.FLOWERING_LEAVES.get();
	}

	@SuppressWarnings("deprecation")
	public void place(TreeDecorator.Context context) {
		// acquire a source of random values in (0,1).
		RandomSource randomSource = context.random();
		// if the next random value is in (0,probability), then proceed
		if (!(randomSource.nextFloat() >= this.probability)) {
			// get lists of the positions of leaves and logs
			List<BlockPos> leavesList = context.leaves();
			List<BlockPos> logsList = context.logs();

			// the highest that a hive can be placed
			// is just below the lowest leaves
			int hiveUpperBound =  leavesList.get(0).getY() - 1;
			// the lowest that a hive can be placed
			// is just above the lowest log
			int hiveLowerBound =  logsList.get(0).getY();
			// if there are no leaves, the upper bound
			// is just below the highest log
			int hiveUpperBoundNoLeaves = logsList.get(logsList.size() - 1).getY();
			boolean noLeaves = leavesList.isEmpty();

			int chosenHeight;
			// if there are leaves, get the largest possible placement height
			if(!noLeaves){
				chosenHeight = Math.max(hiveUpperBound, hiveLowerBound);
			// if there are no leaves, get whatever height is smaller,
			// a rand (lowerBound,lowerBound+3) or the no-leaves upperBound
			} else {
				chosenHeight = Math.min(hiveLowerBound + randomSource.nextInt(3), hiveUpperBoundNoLeaves);
			}

			List<BlockPos> hivePositionOptions =
				logsList.stream()
					// get all logs at the chosen height
					.filter(logPosition -> logPosition.getY() == chosenHeight)
					// for each log in the list,
					// assign the 3 directions in SPAWN_DIRECTIONS
					// then, for each direction,
					// get the position of the given log,
					// +1 in that direction.
					// replace that logPosition in the list
					// with a stream of the 3 resulting positions
					.flatMap(logPosition -> {
						return Stream.of(SPAWN_DIRECTIONS).map(logPosition::relative);
					// convert the set of streams to a list
					}).collect(Collectors.toList());

			// if hivePositionOptions is NOT empty
			if (!hivePositionOptions.isEmpty()) {
				// shuffle the list
				Collections.shuffle(hivePositionOptions);

				// grab the first element (if any)
				// such that the block at that position is air
				// and the block just NORTH of that is air
				Optional<BlockPos> optional =
					hivePositionOptions.stream().filter(positionOption ->
						context.isAir(positionOption) &&
						context.isAir(positionOption.relative(WORLDGEN_FACING))).findFirst();

				// if there is such a block,
				// set a bee nest there facing north
				if (optional.isPresent()) {
					context.setBlock(
						optional.get(),
						// Blocks.BEE_NEST.defaultBlockState().setValue(BeehiveBlock.FACING, WORLDGEN_FACING)
						Blocks.GOLD_BLOCK.defaultBlockState()
					);

					// if a beehive-type blockEntity is present,
					context.level().getBlockEntity(
						optional.get(),
						BlockEntityType.BEEHIVE
					).ifPresent(
						beehiveBlock -> {
							// grab a rand (2,4)
							int j = 2 + randomSource.nextInt(2);

							// try j times...
							for(int k = 0; k < j; ++k) {
								// ..to store a bee in the hive
								// the bee has no nectar to add to the hive,
								// and the bee has a random time in hive (0,599)
								CompoundTag compoundTag = new CompoundTag();
								compoundTag.putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(EntityType.BEE).toString());
								beehiveBlock.storeBee(compoundTag, randomSource.nextInt(599), false);
							}
						}
					);
				}
			}
		}
	}
}