package net.hexagonelle.applesaplings.datagen.models;

import net.hexagonelle.applesaplings.Constants;
import net.hexagonelle.applesaplings.blocks.custom.FloweringLeavesBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

import static net.hexagonelle.applesaplings.blocks.ModBlocks.*;

public class ModBlockStateProvider extends BlockStateProvider {

	// constructor
	public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
		super(output, Constants.MODID, exFileHelper);
	}

	// HELPER METHODS

	public static String blockPathName(String blockId){return "block/" + blockId;}
	public static String blockPathNameWithState(String blockId, String state){
		return blockPathName(blockId) + state;
	}

	private ResourceLocation blockResource(String blockId) {
		return new ResourceLocation(Constants.MODID, blockPathName(blockId));
	}

	private ResourceLocation blockResourceWithState(String blockId, String state) {
		return new ResourceLocation(blockPathNameWithState(blockId,state));
	}

	// generates data for the item version of a block
	private void blockItem(String blockId){
		simpleBlockItem(
			BLOCK_MAP.get(blockId).get(),
			new ModelFile.UncheckedModelFile(blockResource(blockId))
		);
	}

	// generates data for the item version of a block based on a specific blockstate
	private void blockstateItem(String blockId, String blockState){
		simpleBlockItem(
			BLOCK_MAP.get(blockId).get(),
			new ModelFile.UncheckedModelFile(blockResourceWithState(blockId, blockState))
		);
	}

	private ModelFile singleTextureModel(
		String blockId,
		String parent,
		ResourceLocation texture,
		String renderAs
	){
		return models().singleTexture(
			blockId, mcLoc(parent),
			"all", texture
		).renderType(renderAs);
	}

	private void blockWithItem(
		String blockId,
		String parent,
		String renderAs
	){
		Block block = BLOCK_MAP.get(blockId).get();
		simpleBlockWithItem(
			block,
			singleTextureModel(
				blockId, parent,
				blockTexture(block), renderAs
			)
		);
	}

	// MODELS FOR SPECIFIC BLOCK TYPES //


	// generates model (and texture?) json for a sapling block (and its item?)
	private void customSapling(String saplingIdPrefix){
		String blockId = saplingIdPrefix + "_sapling";

		Block block = BLOCK_MAP.get(blockId).get();
		simpleBlock(
			block,
			models()
				.cross(blockId, blockTexture(block))
				.renderType("cutout")
		);
	}

	// creates a model json and texture json for the given LeavesBlock
	private void customLeaves(String leavesIdPrefix){
		blockWithItem(leavesIdPrefix + "_leaves","block/leaves","cutout");
	}

	// creates a model json and texture json for the given blockstate of a FruitLeavesBlock
	private ConfiguredModel[] floweringLeavesStates(
		BlockState blockState,
		String blockId
	){
		FloweringLeavesBlock floweringLeaves = (FloweringLeavesBlock) BLOCK_MAP.get(blockId).get();
		String blockstateValue = String.valueOf(floweringLeaves.getAge(blockState));

		ConfiguredModel[] models = new ConfiguredModel[1];
		models[0] = new ConfiguredModel(
			singleTextureModel(
				blockPathNameWithState(blockId,blockstateValue), "block/leaves",
				blockResourceWithState(blockId, blockstateValue), "cutout"
			)
		);

		return models;
	}

	// generates model & texture json files for all the blockstates of a given floweringLeavesBlock
	// as well as its item
	private void floweringLeavesBlock(String floweringLeavesInfix){
		String blockId = "flowering_" + floweringLeavesInfix + "_leaves";

		Function<BlockState,ConfiguredModel[]> function =
			state -> floweringLeavesStates(state,blockId);

		getVariantBuilder(BLOCK_MAP.get(blockId).get()).forAllStates(function);
		blockstateItem(blockId,"0");
	}

	// generates model (and texture?) json for a log block and its item
	private void customStrippedLog(String woodType){
		String logId = "stripped_" + woodType + "_log";
		logBlock((RotatedPillarBlock) BLOCK_MAP.get(logId).get());
		blockItem(logId);
	}

	// generates model (and texture?) json for a wood block and its item
	private void customStrippedWood(String woodType){
		String logId = "stripped_" + woodType + "_log";
		String woodId = "stripped_" + woodType + "_wood";
		Block logBlock = BLOCK_MAP.get(logId).get();

		axisBlock(
			(RotatedPillarBlock) BLOCK_MAP.get(woodId).get(),
			blockTexture(logBlock), blockTexture(logBlock)
		);
		blockItem(woodId);
	}

	// generates model (and texture?) json for a log block and its item
	private void customLog(String woodType){
		String logId = woodType + "_log";
		logBlock((RotatedPillarBlock) BLOCK_MAP.get(logId).get());
		blockItem(logId);
	}

	// generates model (and texture?) json for a wood block and its item
	private void customWood(String woodType){
		String logId = woodType + "_log";
		String woodId = woodType + "_wood";
		Block logBlock = BLOCK_MAP.get(logId).get();

		axisBlock(
			(RotatedPillarBlock) BLOCK_MAP.get(woodId).get(),
			blockTexture(logBlock), blockTexture(logBlock)
		);
		blockItem(woodId);
	}

	private void customPlanks(String woodType){
		blockWithItem(woodType + "_planks","block/oak_planks","solid");
	}

	public void customSign(String woodType){
		signBlock(
			(StandingSignBlock)  BLOCK_MAP.get(woodType + "_sign").get(),
			(WallSignBlock) BLOCK_MAP.get(woodType + "_wall_sign").get(),
			blockTexture(BLOCK_MAP.get(woodType + "_planks").get())
		);
	}

	public void customHangingSign(String woodType){
		String hangingSignId = woodType + "_hanging_sign";
		ModelFile signModel = models().sign(
			hangingSignId,
			blockTexture(BLOCK_MAP.get(woodType + "_planks").get())
		);

		simpleBlock(BLOCK_MAP.get(hangingSignId).get(), signModel);
		simpleBlock(BLOCK_MAP.get(woodType + "wall_hanging_sign").get(), signModel);
	}

	public void customWoodStairs(String woodType){
		stairsBlock(
			(StairBlock) BLOCK_MAP.get(woodType + "_stairs").get(),
			blockTexture(BLOCK_MAP.get(woodType + "_planks").get())
		);
	}

	public void customWoodSlab(String woodType){
		Block baseBlock = BLOCK_MAP.get(woodType + "_planks").get();
		slabBlock(
			(SlabBlock) BLOCK_MAP.get(woodType + "_slab").get(),
			blockTexture(baseBlock), blockTexture(baseBlock)
		);
	}

	public void customButton(String woodType){
		buttonBlock(
			(ButtonBlock) BLOCK_MAP.get(woodType + "_button").get(),
			blockTexture(BLOCK_MAP.get(woodType + "_planks").get())
		);
	}

	public void customPressurePlate(String woodType){
		pressurePlateBlock(
			(PressurePlateBlock) BLOCK_MAP.get(woodType + "_pressure_plate").get(),
			blockTexture(BLOCK_MAP.get(woodType + "_planks").get())
		);
	}

	public void customFence(String woodType){
		fenceBlock(
			(FenceBlock) BLOCK_MAP.get(woodType + "_fence").get(),
			blockTexture(BLOCK_MAP.get(woodType + "_planks").get())
		);
	}

	public void customFenceGate(String woodType){
		fenceGateBlock(
			(FenceGateBlock) BLOCK_MAP.get(woodType + "_fence_gate").get(),
			blockTexture(BLOCK_MAP.get(woodType + "_planks").get())
		);
	}

	public void customWoodDoor(String woodType){
		String doorId = woodType + "_door";
		doorBlockWithRenderType(
			(DoorBlock) BLOCK_MAP.get(doorId).get(),
			blockResourceWithState(doorId, "_bottom"),
			blockResourceWithState(doorId, "_top"),
			"cutout"
		);
	}

	public void customWoodTrapdoor(String woodType){
		String trapdoorId = woodType + "_trapdoor";
		trapdoorBlockWithRenderType(
			(TrapDoorBlock) BLOCK_MAP.get(trapdoorId).get(),
			blockResource(trapdoorId),
			true, "cutout"
		);
	}

	public void customWoodTypeModels(
		String woodType,
		String saplingNamePrefix,
		String leavesNamePrefix
	){

		customSapling(saplingNamePrefix);
		customLeaves(leavesNamePrefix);

		customStrippedLog(woodType);
		customStrippedWood(woodType);
		customLog(woodType);
		customWood(woodType);
		customPlanks(woodType);

		customSign(woodType);
		customHangingSign(woodType);
		customWoodStairs(woodType);
		customWoodSlab(woodType);
		customButton(woodType);
		customPressurePlate(woodType);
		customFence(woodType);
		customFenceGate(woodType);
		customWoodDoor(woodType);
		customWoodTrapdoor(woodType);
	}

	@Override
	protected void registerStatesAndModels() {
		customWoodTypeModels("applewood","apple","apple");
		floweringLeavesBlock("apple");
	}

}