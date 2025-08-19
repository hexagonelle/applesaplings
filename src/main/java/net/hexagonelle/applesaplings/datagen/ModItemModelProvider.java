package net.hexagonelle.applesaplings.datagen;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
	public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, AppleSaplings.MODID, existingFileHelper);
	}

	private ItemModelBuilder blockItem(RegistryObject<Block> item){
		String path = item.getId().getPath();
		return withExistingParent(
				path,
				new ResourceLocation("item/generated")
		).texture(
				"layer0",
				new ResourceLocation(AppleSaplings.MODID,"block/" + path)
		);
	}

//	private ItemModelBuilder simpleItem(RegistryObject<Item> item){
//		String path = item.getId().getPath();
//
//		return withExistingParent(path,
//			new ResourceLocation("item/generated")).texture(
//					"layer0",
//					new ResourceLocation(AppleSaplings.MODID,"item/" + path)
//		);
//	}

	@Override
	protected void registerModels() {
		blockItem(ModBlocks.APPLE_SAPLING);
	}
}
