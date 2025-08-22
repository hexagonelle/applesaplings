package net.hexagonelle.applesaplings.blocks;

import net.hexagonelle.applesaplings.blocks.custom.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.registries.RegistryObject;

// Methods for creating block instances

public class BlockCreator {

	// Create a sapling with the given AbstractTreeGrower and the properties of the vanilla OAK_SAPLING
	public static SaplingBlock createSapling(AbstractTreeGrower treeGrower){
		return new SaplingBlock(treeGrower, BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING));
	}
	// Create a Flowering leaves block with the properties of the vanilla OAK_LEAVES
	public static FloweringLeavesBlock createFloweringLeaves() {
		return new FloweringLeavesBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES));
	}
	// Create a log or wood block with the properties of the given block
	public static Block createWoodOrLogBlock(Block copyFrom, RegistryObject<Block> strippedBlock) {
		return new CustomWood(BlockBehaviour.Properties
			.copy(copyFrom)
			.strength(2.0F)
//					.mapColor(CustomWood.woodMapColor(topMapColor,sideMapColor))
		).setStrippedVersion(strippedBlock.get());
	}
	// Create a stripped log or wood block with the properties of the given block
	public static Block createWoodOrLogBlock(Block copyFrom) {
		return new CustomWood(BlockBehaviour.Properties
			.copy(copyFrom)
			.strength(2.0F)
//					.mapColor(CustomWood.woodMapColor(topMapColor,sideMapColor))
		).setIsStripped(true);
	}
	// Create a planks block with the properties of the vanilla OAK_PLANKS
	public static Block createPlanks() {
		return new CustomWood(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS));
	}
	// Create a planks block with the properties of the vanilla OAK_PLANKS
	public static Block createLeaves() {
		return new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES))
		{
			@Override
			public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
				return true;
			}
			@Override
			public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
				return 60;
			}
			@Override
			public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
				return 30;
			}
		};
	}
	// Create a sign with the given woodType
	public static Block createSign(WoodType woodType){
		return new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), woodType);
	}
	// Create a sign with the given woodType
	public static Block createWallSign(WoodType woodType){
		return new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), woodType);
	}
	// Create a sign with the given woodType
	public static Block createHangingSign(WoodType woodType){
		return new ModCeilingHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), woodType);
	}
	// Create a sign with the given woodType
	public static Block createWallHangingSign(WoodType woodType){
		return new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), woodType);
	}

	public static Block createWoodStairBlock(RegistryObject<Block> baseBlock){
		return new StairBlock(() -> baseBlock.get().defaultBlockState(),
			BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS));
	}

	public static Block createWoodSlabBlock(){
		return new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB));
	}

	public static Block createWoodButton(){
		return new ButtonBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON),
			BlockSetType.OAK, 10, true);
	}

	public static Block createWoodPressurePlate(){
		return new PressurePlateBlock(
			PressurePlateBlock.Sensitivity.EVERYTHING,
			BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK
		);
	}

	public static Block createWoodFenceBlock(){
		return new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE));
	}

	public static Block createWoodFenceGateBlock(){
		return new FenceGateBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_FENCE),
			SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
		);
	}

	public static Block createWoodDoorBlock(){
		return new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).noOcclusion(), BlockSetType.OAK);
	}

	public static Block createWoodTrapDoorBlock() {
		return new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR).noOcclusion(), BlockSetType.OAK);
	}
}
