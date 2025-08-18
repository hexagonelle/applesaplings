package net.hexagonelle.applesaplings.datagen;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.hexagonelle.applesaplings.custom.FruitingLeavesBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {

	public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
		super(output, AppleSaplings.MODID, exFileHelper);
	}

	public String getPathString(RegistryObject<Block> blockRegistryObject){
		return ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath();
	}

	private void leavesBlock(RegistryObject<Block> blockRegistryObject){

		simpleBlockWithItem(
				blockRegistryObject.get(),
				models()
						.singleTexture(
							getPathString(blockRegistryObject),
							new ResourceLocation("minecraft:block/leaves"),
							"all",
							blockTexture(blockRegistryObject.get())
						)
						.renderType("cutout")
				);

	}

	private ConfiguredModel[] states(
			BlockState blockState,
			FruitingLeavesBlock fruitingLeaves,
			String modelName,
			String textureName
	){
		ConfiguredModel[] models = new ConfiguredModel[1];
		models[0] =
			new ConfiguredModel(
				models()
					.singleTexture(
							"block/" + modelName + blockState.getValue(fruitingLeaves.getAgeProperty()),
							new ResourceLocation("minecraft:block/leaves"),
							"all",
                            new ResourceLocation(
									AppleSaplings.MODID,
                                    "block/" + textureName + blockState.getValue(fruitingLeaves.getAgeProperty())
							)
                    )
					.renderType("cutout")
			);

		return models;
	}

	public void fruitingLeavesBlock(RegistryObject<Block> blockRegistryObject){
		String modelName = ForgeRegistries.BLOCKS
				.getKey(blockRegistryObject.get())
				.getPath();
        @NotNull FruitingLeavesBlock fruitingLeaves = (FruitingLeavesBlock) blockRegistryObject.get();

		// Function<BlockState,ConfiguredModel[]> defines a function
		// whose input is a BlockState
		// and whose output is an array of ConfiguredModel objects.
		// the syntax "state -> ..."
		// indicates that whatever is before the arrow is the input variable
		// and whatever comes after the arrow should be something
		// that evaluates to the output type
		Function<BlockState,ConfiguredModel[]> function =
				state -> states(state, fruitingLeaves, modelName, modelName);

		getVariantBuilder(fruitingLeaves).forAllStates(function);
		blockItem(blockRegistryObject);
	}

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

	// generates data for the item version of a block,
	// assuming that the block's data has already been generated
	// by some other method here.
	private void blockItem(RegistryObject<Block> blockRegistryObject){
		simpleBlockItem(
				blockRegistryObject.get(),
				new ModelFile.UncheckedModelFile(
						AppleSaplings.MODID +
								":block/" +
								ForgeRegistries.BLOCKS
										.getKey(blockRegistryObject.get())
										.getPath()
				)
		);
	}

	private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
		simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
	}

	@Override
	protected void registerStatesAndModels() {
		saplingBlock(ModBlocks.APPLE_SAPLING);
		fruitingLeavesBlock(ModBlocks.APPLE_LEAVES);

	}

}
