package net.hexagonelle.hexsappletrees.datagen;

import net.hexagonelle.hexsappletrees.HexsAppleTrees;
import net.hexagonelle.hexsappletrees.blocks.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
	public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, HexsAppleTrees.MODID, existingFileHelper);
	}

	private ItemModelBuilder saplingItem(RegistryObject<Block> item){
		String path = item.getId().getPath();
		return withExistingParent(
				path,
				new ResourceLocation("item/generated")
		).texture(
				"layer0",
				new ResourceLocation(HexsAppleTrees.MODID,"block/" + path)
		);
	}

	private ItemModelBuilder simpleItem(RegistryObject<Item> item){
		String path = item.getId().getPath();

		return withExistingParent(path,
			new ResourceLocation("item/generated")).texture(
					"layer0",
					new ResourceLocation(HexsAppleTrees.MODID,"item/" + path)
		);
	}

	@Override
	protected void registerModels() {
		saplingItem(ModBlocks.APPLE_SAPLING);
	}
}
