package net.hexagonelle.hexsappletrees.datagen;

import net.hexagonelle.hexsappletrees.HexsAppleTrees;
import net.hexagonelle.hexsappletrees.blocks.ModBlocks;
import net.hexagonelle.hexsappletrees.custom.FruitingLeavesBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
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
		super(output, HexsAppleTrees.MODID, exFileHelper);
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
			LeavesBlock fruitingLeaves,
			String modelName,
			String textureName
	){
		ConfiguredModel[] models = new ConfiguredModel[1];
		models[0] =
			new ConfiguredModel(
				models()
					.singleTexture(
						modelName +
							blockState.getValue(
								((FruitingLeavesBlock) fruitingLeaves)
									.getAgeProperty(blockState)
							),
							new ResourceLocation("minecraft:block/leaves"),
							"all",
                            new ResourceLocation(HexsAppleTrees.MODID,
                                    "block/" + textureName +
                                        blockState.getValue(
                                            ((FruitingLeavesBlock) fruitingLeaves)
                                                    .getAgeProperty(blockState)
                                        )
                            )
                    )
					.renderType("cutout")
			);

		return models;
	}

//	getVariantBuilder(ModBlocks.APPLE_LEAVES.get())
//			.partialState()
//				.with(FruitingLeavesBlock.AGE, 0)
//				.addModels(
//			ConfiguredModel.builder().modelFile(
//								new ModelFile.UncheckedModelFile(
//			modLoc(
//			"block/" +
//			ForgeRegistries.BLOCKS.getKey(
//			ModBlocks.APPLE_LEAVES.get()
//														).getPath() + "0"
//			)
//			)
//			).build()
//				).partialState()
//				.with(FruitingLeavesBlock.AGE, 1)
//				.addModels(
//			ConfiguredModel.builder().modelFile(
//								new ModelFile.UncheckedModelFile(
//			modLoc(
//			"block/" +
//			ForgeRegistries.BLOCKS.getKey(
//			ModBlocks.APPLE_LEAVES.get()
//														).getPath() + "1"
//			)
//			)
//			).build()
//				)
//						.partialState()
//				.with(FruitingLeavesBlock.AGE, 2)
//				.addModels(
//			ConfiguredModel.builder().modelFile(
//								new ModelFile.UncheckedModelFile(
//			modLoc(
//			"block/" +
//			ForgeRegistries.BLOCKS.getKey(
//			ModBlocks.APPLE_LEAVES.get()
//														).getPath() + "2")
//			)
//			).build()
//				)
//						.partialState()
//				.with(FruitingLeavesBlock.AGE, 3)
//				.addModels(
//			ConfiguredModel.builder().modelFile(
//								new ModelFile.UncheckedModelFile(
//			modLoc(
//			"block/" +
//			ForgeRegistries.BLOCKS.getKey(
//			ModBlocks.APPLE_LEAVES.get()
//														).getPath() + "3")
//			)
//			).build()
//				)
//						.partialState()
//				.with(FruitingLeavesBlock.AGE, 3)
//				.addModels(
//			ConfiguredModel.builder().modelFile(
//								new ModelFile.UncheckedModelFile(
//			modLoc(
//			"block/" +
//			ForgeRegistries.BLOCKS.getKey(
//			ModBlocks.APPLE_LEAVES.get()
//														).getPath() + "4")
//			)
//			).build()
//				);

	public void fruitingLeavesBlock(
			LeavesBlock fruitingLeavesBlock,
			String modelName,
			String textureName
	){
		Function<BlockState,ConfiguredModel[]> function =
				state -> states(state,fruitingLeavesBlock,modelName,textureName);

		getVariantBuilder(fruitingLeavesBlock).forAllStates(function);
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

	private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
		simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
	}

	@Override
	protected void registerStatesAndModels() {
		saplingBlock(ModBlocks.APPLE_SAPLING);
		fruitingLeavesBlock(
				(LeavesBlock) ModBlocks.APPLE_LEAVES.get(),
				"apple_leaves",
				"apple_leaves"
		);

	}

}
