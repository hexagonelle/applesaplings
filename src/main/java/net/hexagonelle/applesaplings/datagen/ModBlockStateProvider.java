package net.hexagonelle.applesaplings.datagen;

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
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {

	public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
		super(output, AppleSaplings.MODID, exFileHelper);
	}

	private ResourceLocation key(RegistryObject<Block> blockRegistryObject){
		return ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get());
	}

	// return the String corresponding to the path of a Block
	public String name(RegistryObject<Block> blockRegistryObject){
		return key(blockRegistryObject).getPath();
	}

	// generates data for the item version of a block
	private void blockItem(RegistryObject<Block> blockRegistryObject){
		simpleBlockItem(
			blockRegistryObject.get(),
			new ModelFile.UncheckedModelFile(
					AppleSaplings.MODID + ":block/" + name(blockRegistryObject)
			)
		);
	}

	// generates data for the item version of a block based on a specific blockstate
	private void blockItem(RegistryObject<Block> blockRegistryObject, String pathToBlockState){
		simpleBlockItem(
				blockRegistryObject.get(),
				new ModelFile.UncheckedModelFile(
						AppleSaplings.MODID + ":block/" + pathToBlockState
				)
		);
	}

	private void blockWithItem(RegistryObject<Block> blockRegistryObject){
		String path = name(blockRegistryObject);
		ModelFile modelFile =
			models().singleTexture(
				path, new ResourceLocation("minecraft:block/oak_planks"),
				"all", blockTexture(blockRegistryObject.get())
			).renderType("solid");
		simpleBlock(blockRegistryObject.get(), modelFile);
		blockItem(blockRegistryObject);
	}

	// creates a model json and texture json for the given LeavesBlock
	private void leavesBlock(RegistryObject<Block> blockRegistryObject){
		simpleBlockWithItem(
				blockRegistryObject.get(),
				models().singleTexture(
					name(blockRegistryObject), new ResourceLocation("minecraft:block/leaves"),
					"all", blockTexture(blockRegistryObject.get())
				).renderType("cutout")
		);

	}

	// creates a model json and texture json for the given blockstate of a FruitLeavesBlock
	private ConfiguredModel[] floweringLeavesStates(
			BlockState blockState,
			RegistryObject<Block> blockRegistryObject
	){
		String modelName = name(blockRegistryObject);
		FloweringLeavesBlock floweringLeaves = (FloweringLeavesBlock) blockRegistryObject.get();
		String path = "block/" + modelName + blockState.getValue(floweringLeaves.getAgeProperty());

		ConfiguredModel[] models = new ConfiguredModel[1];
		models[0] = new ConfiguredModel(
			models().singleTexture(
				path, new ResourceLocation("minecraft:block/leaves"),
				"all", new ResourceLocation(AppleSaplings.MODID, path)
			)
			.renderType("cutout")
		);

		return models;
	}

	// generates model & texture json files for all the blockstates of a given floweringLeavesBlock
	// as well as its item
	private void floweringLeavesBlock(RegistryObject<Block> blockRegistryObject){

		Function<BlockState,ConfiguredModel[]> function =
			state -> floweringLeavesStates(state,blockRegistryObject);

		getVariantBuilder(blockRegistryObject.get()).forAllStates(function);
		blockItem(blockRegistryObject,"flowering_apple_leaves0");
	}

	// generates model (and texture?) json for a sapling block (and its item?)
	private void saplingBlock(RegistryObject<Block> blockRegistryObject){
		simpleBlock(
			blockRegistryObject.get(),
			models().cross(
				name(blockRegistryObject),
				blockTexture(blockRegistryObject.get())
			).renderType("cutout")
		);
	}

	// generates model (and texture?) json for a log block and its item
	private void logBlock(RegistryObject<Block> blockRegistryObject){
		logBlock((RotatedPillarBlock) blockRegistryObject.get());
		blockItem(blockRegistryObject);
	}

	// generates model (and texture?) json for a wood block and its item
	private void woodBlock(
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

	private void customSignBlock(
		RegistryObject<Block> floorSign,
		RegistryObject<Block> wallSign,
		RegistryObject<Block> planks
	){
		signBlock(
			(StandingSignBlock) floorSign.get(),
			(WallSignBlock) wallSign.get(),
			blockTexture(planks.get())
		);
	}

	private void customHangingSignBlock(
		RegistryObject<Block> hangingSign,
		RegistryObject<Block> wallHangingSign,
		RegistryObject<Block> planks
	){
		ModelFile signModel = models().sign(name(hangingSign), blockTexture(planks.get()));
		simpleBlock(hangingSign.get(), signModel);
		simpleBlock(wallHangingSign.get(), signModel);
	}

	@Override
	protected void registerStatesAndModels() {
		saplingBlock(ModBlocks.APPLE_SAPLING);
		leavesBlock(ModBlocks.APPLE_LEAVES);
		floweringLeavesBlock(ModBlocks.FLOWERING_APPLE_LEAVES);
		logBlock(ModBlocks.APPLEWOOD_LOG);
		woodBlock(ModBlocks.APPLEWOOD_WOOD,ModBlocks.APPLEWOOD_LOG);
		logBlock(ModBlocks.STRIPPED_APPLEWOOD_LOG);
		woodBlock(ModBlocks.STRIPPED_APPLEWOOD_WOOD,ModBlocks.STRIPPED_APPLEWOOD_LOG);
		blockWithItem(ModBlocks.APPLEWOOD_PLANKS);
		customSignBlock(ModBlocks.APPLEWOOD_SIGN, ModBlocks.APPLEWOOD_WALL_SIGN, ModBlocks.APPLEWOOD_PLANKS);
		customHangingSignBlock(ModBlocks.APPLEWOOD_HANGING_SIGN, ModBlocks.APPLEWOOD_WALL_HANGING_SIGN, ModBlocks.APPLEWOOD_PLANKS);

	}

}