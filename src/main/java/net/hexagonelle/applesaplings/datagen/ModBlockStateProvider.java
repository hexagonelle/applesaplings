package net.hexagonelle.applesaplings.datagen;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.hexagonelle.applesaplings.custom.FruitingLeavesBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {

	public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
		super(output, AppleSaplings.MODID, exFileHelper);
	}

	// return the String corresponding to the path of a Block
	public String getPathString(RegistryObject<Block> blockRegistryObject){
		return Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get())).getPath();
	}

	// generates data for the item version of a block
	private void blockItem(RegistryObject<Block> blockRegistryObject){
		simpleBlockItem(
				blockRegistryObject.get(),
				new ModelFile.UncheckedModelFile(
						AppleSaplings.MODID + ":block/" + getPathString(blockRegistryObject)
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

//	private void leavesBlock(RegistryObject<Block> blockRegistryObject){
//
//		simpleBlockWithItem(
//				blockRegistryObject.get(),
//				models()
//						.singleTexture(
//								getPathString(blockRegistryObject),
//								new ResourceLocation("minecraft:block/leaves"),
//								"all",
//								blockTexture(blockRegistryObject.get())
//						)
//						.renderType("cutout")
//		);
//
//	}

	// creates a model json and texture json for the given blockstate of a FruitLeavesBlock
	private ConfiguredModel[] fruitingLeavesStates(
			BlockState blockState,
			RegistryObject<Block> blockRegistryObject
	){
		String modelName = getPathString(blockRegistryObject);
		FruitingLeavesBlock fruitingLeaves = (FruitingLeavesBlock) blockRegistryObject.get();
		String path = "block/" + modelName + blockState.getValue(fruitingLeaves.getAgeProperty());

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

	// generates model json files for all the blockstates of a given fruitingLeavesBlock
	public void fruitingLeavesBlock(RegistryObject<Block> blockRegistryObject){

		Function<BlockState,ConfiguredModel[]> function =
				state -> fruitingLeavesStates(state,blockRegistryObject);

		getVariantBuilder(blockRegistryObject.get()).forAllStates(function);
		blockItem(blockRegistryObject,"apple_leaves0");
	}

	// generates model json for a sapling block
	private void saplingBlock(RegistryObject<Block> blockRegistryObject){
		simpleBlock(
				blockRegistryObject.get(),
				models()
						.cross(
								getPathString(blockRegistryObject),
								blockTexture(blockRegistryObject.get())
						)
						.renderType("cutout")
		);
	}

	@Override
	protected void registerStatesAndModels() {
		saplingBlock(ModBlocks.APPLE_SAPLING);
		fruitingLeavesBlock(ModBlocks.APPLE_LEAVES);

	}

}