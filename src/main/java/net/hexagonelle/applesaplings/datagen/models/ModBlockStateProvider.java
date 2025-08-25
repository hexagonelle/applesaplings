package net.hexagonelle.applesaplings.datagen.models;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.hexagonelle.applesaplings.blocks.custom.FloweringLeavesBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

import static org.openjdk.nashorn.internal.objects.Global.print;

public class ModBlockStateProvider extends BlockStateProvider {

	public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
		super(output, AppleSaplings.MODID, exFileHelper);
	}

	// return the String corresponding to the path of a Block
	public static String name(RegistryObject<Block> blockRegistryObject){
		return blockRegistryObject.getId().getPath();
	}

	public static String blockPathName(RegistryObject<Block> blockRegistryObject){
		return "block/" + name(blockRegistryObject);
	}
	public static String blockPathNameWithState(RegistryObject<Block> blockRegistryObject, String state){
		return blockPathName(blockRegistryObject) + state;
	}

	private ResourceLocation blockResource(RegistryObject<Block> blockRegistryObject) {
		return modLoc(blockPathName(blockRegistryObject));
	}

	private ResourceLocation blockResourceWithState(RegistryObject<Block> blockRegistryObject, String state) {
		return modLoc(blockPathNameWithState(blockRegistryObject,state));
	}

	// generates data for the item version of a block
	private void blockItem(RegistryObject<Block> blockRegistryObject){
		simpleBlockItem(
			blockRegistryObject.get(),
			new ModelFile.UncheckedModelFile(blockResource(blockRegistryObject))
		);
	}

	// generates data for the item version of a block based on a specific blockstate
	private void blockItem(RegistryObject<Block> blockRegistryObject, String blockState){
		simpleBlockItem(
			blockRegistryObject.get(),
			new ModelFile.UncheckedModelFile(blockResourceWithState(
				blockRegistryObject,
				blockState
			))
		);
	}

	private ModelFile singleTextureModel(
		String name,
		String parent,
		ResourceLocation texture,
		String renderAs
	){
		return models().singleTexture(
			name, mcLoc(parent),
			"all", texture
		).renderType(renderAs);
	}

	private void blockWithItem(
		RegistryObject<Block> blockRegistryObject,
		String parent,
		String renderAs
	){
		simpleBlockWithItem(
			blockRegistryObject.get(),
			singleTextureModel(
				name(blockRegistryObject),
				parent,
				blockTexture(blockRegistryObject.get()),
				renderAs
			)
		);
	}

	private void customPlanks(RegistryObject<Block> blockRegistryObject){
		blockWithItem(blockRegistryObject,"block/oak_planks","solid");
	}

	// creates a model json and texture json for the given LeavesBlock
	private void customLeaves(RegistryObject<Block> blockRegistryObject){
		blockWithItem(blockRegistryObject,"block/leaves","cutout");
	}

	// creates a model json and texture json for the given blockstate of a FruitLeavesBlock
	private ConfiguredModel[] floweringLeavesStates(
			BlockState blockState,
			RegistryObject<Block> blockRegistryObject
	){
		FloweringLeavesBlock floweringLeaves = (FloweringLeavesBlock) blockRegistryObject.get();
		String blockstateValue = blockState.getValue(floweringLeaves.getAgeProperty()).toString();
		ConfiguredModel[] models = new ConfiguredModel[1];
		models[0] = new ConfiguredModel(
			singleTextureModel(
				blockPathNameWithState(blockRegistryObject,blockstateValue), "block/leaves",
				blockResourceWithState(
					blockRegistryObject,
					blockstateValue
				),
				"cutout"
			)
		);

		return models;
	}

	// generates model & texture json files for all the blockstates of a given floweringLeavesBlock
	// as well as its item
	private void floweringLeavesBlock(RegistryObject<Block> blockRegistryObject){

		Function<BlockState,ConfiguredModel[]> function =
			state -> floweringLeavesStates(state,blockRegistryObject);

		getVariantBuilder(blockRegistryObject.get()).forAllStates(function);
		blockItem(blockRegistryObject,"0");
	}

	// generates model (and texture?) json for a sapling block (and its item?)
	private void customSapling(RegistryObject<Block> blockRegistryObject){
		simpleBlock(
			blockRegistryObject.get(),
			models().cross(
				name(blockRegistryObject),
				blockTexture(blockRegistryObject.get())
			).renderType("cutout")
		);
	}

	// generates model (and texture?) json for a log block and its item
	private void customLog(RegistryObject<Block> blockRegistryObject){
		logBlock((RotatedPillarBlock) blockRegistryObject.get());
		blockItem(blockRegistryObject);
	}

	// generates model (and texture?) json for a wood block and its item
	private void customWood(
		RegistryObject<Block> blockRegistryObject,
		RegistryObject<Block> blockRegistryParentTexture
		){
		axisBlock(
			(RotatedPillarBlock) blockRegistryObject.get(),
			blockTexture(blockRegistryParentTexture.get()),
			blockTexture(blockRegistryParentTexture.get())
		);
		blockItem(blockRegistryObject);
	}

	public void customSign(
		RegistryObject<Block> standingSign,
		RegistryObject<Block> wallSign,
		RegistryObject<Block> planks
	){
		signBlock(
			(StandingSignBlock) standingSign.get(),
			(WallSignBlock) wallSign.get(),
			blockTexture(planks.get())
		);
	}

	public void customHangingSign(
		RegistryObject<Block> ceilingHangingSign,
		RegistryObject<Block> wallHangingSign,
		RegistryObject<Block> planks
	){
		ModelFile signModel = models().sign(name(ceilingHangingSign), blockTexture(planks.get()));
		simpleBlock(ceilingHangingSign.get(), signModel);
		simpleBlock(wallHangingSign.get(), signModel);
	}

	public void customStairs(
		RegistryObject<Block> blockRegistryObject,
		RegistryObject<Block> baseBlockRegistryObject
	){
		stairsBlock(
			(StairBlock) blockRegistryObject.get(),
			blockTexture(baseBlockRegistryObject.get())
		);
	}

	public void customSlab(
		RegistryObject<Block> blockRegistryObject,
		RegistryObject<Block> baseBlockRegistryObject
	){
		slabBlock(
			(SlabBlock) blockRegistryObject.get(),
			blockTexture(baseBlockRegistryObject.get()),
			blockTexture(baseBlockRegistryObject.get())
		);
	}

	public void customButton(
		RegistryObject<Block> blockRegistryObject,
		RegistryObject<Block> baseBlockRegistryObject
	){
		buttonBlock(
			(ButtonBlock) blockRegistryObject.get(),
			blockTexture(baseBlockRegistryObject.get())
		);
	}

	public void customPressurePlate(
		RegistryObject<Block> blockRegistryObject,
		RegistryObject<Block> baseBlockRegistryObject
	){
		pressurePlateBlock(
			(PressurePlateBlock) blockRegistryObject.get(),
			blockTexture(baseBlockRegistryObject.get())
		);
	}

	public void customFence(
		RegistryObject<Block> blockRegistryObject,
		RegistryObject<Block> baseBlockRegistryObject
	){
		fenceBlock(
			(FenceBlock) blockRegistryObject.get(),
			blockTexture(baseBlockRegistryObject.get())
		);
	}

	public void customFenceGate(
		RegistryObject<Block> blockRegistryObject,
		RegistryObject<Block> baseBlockRegistryObject
	){
		fenceGateBlock(
			(FenceGateBlock) blockRegistryObject.get(),
			blockTexture(baseBlockRegistryObject.get())
		);
	}

	public void customDoor(
		RegistryObject<Block> blockRegistryObject
	){
		doorBlockWithRenderType(
			(DoorBlock) blockRegistryObject.get(),
			blockResourceWithState(blockRegistryObject, "_bottom"),
			blockResourceWithState(blockRegistryObject, "_top"),
			"cutout"
		);
	}

	public void customTrapdoor(
		RegistryObject<Block> blockRegistryObject
	){
		trapdoorBlockWithRenderType(
			(TrapDoorBlock) blockRegistryObject.get(),
			blockResource(blockRegistryObject),
			true, "cutout"
		);
	}

	@Override
	protected void registerStatesAndModels() {
		customSapling(ModBlocks.APPLE_SAPLING);
		customLeaves(ModBlocks.APPLE_LEAVES);
		floweringLeavesBlock(ModBlocks.FLOWERING_APPLE_LEAVES);
		customLog(ModBlocks.APPLEWOOD_LOG);
		customWood(ModBlocks.APPLEWOOD_WOOD,ModBlocks.APPLEWOOD_LOG);
		customLog(ModBlocks.STRIPPED_APPLEWOOD_LOG);
		customWood(ModBlocks.STRIPPED_APPLEWOOD_WOOD,ModBlocks.STRIPPED_APPLEWOOD_LOG);
		customPlanks(ModBlocks.APPLEWOOD_PLANKS);

		customSign(ModBlocks.APPLEWOOD_SIGN, ModBlocks.APPLEWOOD_WALL_SIGN, ModBlocks.APPLEWOOD_PLANKS);
		customHangingSign(ModBlocks.APPLEWOOD_HANGING_SIGN, ModBlocks.APPLEWOOD_WALL_HANGING_SIGN, ModBlocks.APPLEWOOD_PLANKS);
		customStairs(ModBlocks.APPLEWOOD_STAIRS,ModBlocks.APPLEWOOD_PLANKS);
		customSlab(ModBlocks.APPLEWOOD_SLAB,ModBlocks.APPLEWOOD_PLANKS);
		customButton(ModBlocks.APPLEWOOD_BUTTON,ModBlocks.APPLEWOOD_PLANKS);
		customPressurePlate(ModBlocks.APPLEWOOD_PRESSURE_PLATE,ModBlocks.APPLEWOOD_PLANKS);
		customFence(ModBlocks.APPLEWOOD_FENCE,ModBlocks.APPLEWOOD_PLANKS);
		customFenceGate(ModBlocks.APPLEWOOD_FENCE_GATE,ModBlocks.APPLEWOOD_PLANKS);
		customDoor(ModBlocks.APPLEWOOD_DOOR);
		customTrapdoor(ModBlocks.APPLEWOOD_TRAPDOOR);

	}

}