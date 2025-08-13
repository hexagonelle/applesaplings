package net.hexagonelle.hexsappletrees.datagen;

import net.hexagonelle.hexsappletrees.HexsAppleTrees;
import net.hexagonelle.hexsappletrees.blocks.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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
		leavesBlock(ModBlocks.APPLE_LEAVES);
	}

}
