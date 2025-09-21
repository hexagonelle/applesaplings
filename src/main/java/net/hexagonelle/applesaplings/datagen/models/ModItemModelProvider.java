package net.hexagonelle.applesaplings.datagen.models;

import net.hexagonelle.applesaplings.Constants;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

	// constructor
	public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, Constants.MODID, existingFileHelper);
	}

	// HELPER METHODS //

	// provides a resource location for an item with namespace equal to the given namespace
	// and location equal to the itemPath of the provided itemRegistryObject
	private ResourceLocation itemResource(String itemId){
		return modLoc("item/" + itemId);
	}
	// provides a resource location for a block with namespace equal to the given namespace
	// and location equal to the blockPath of the provided blockRegistryObject
	private ResourceLocation blockResource(String blockId){
		return modLoc("block/" + blockId);
	}
	// provides a resource location for an item with a block parent,
	// with namespace equal to the given namespace,
	// and location equal to the itemPath of the provided blockRegistryObject
	private ResourceLocation blockItemResource(String blockId){
		return modLoc("item/" + blockId);
	}

	private ItemModelBuilder flatItemModel(
		String name,
		ResourceLocation texture
	){
		return singleTexture(name,new ResourceLocation("item/generated"),"layer0",texture);
	}

	private ItemModelBuilder mcItemModel(
		String name,
		String mcModelName,
		ResourceLocation texture
	){
		return singleTexture(name,mcLoc(mcModelName),"texture",texture);
	}

	// generates a generic item model with the name of the item,
	// and a texture located at modid/textures/item/"itemName".png
	private ItemModelBuilder simpleItem(String itemId){
		return flatItemModel(itemId, itemResource(itemId));
	}

	// generates a generic item model with the name of the corresponding block,
	// and a texture located at modid/textures/item/"itemName".png
	private ItemModelBuilder blockItemWithItemTexture(String blockId){
		return flatItemModel(blockId, blockItemResource(blockId));
	}

	// generates an item form of a standard block with the texture taken from the block texture
	public void blockItemWithBlockTexture(String blockId) {
		this.withExistingParent(blockId, blockId);
	}

	// generates a flat item form of a block with the texture taken from the block texture
	public void blockItemWithFlatBlockTexture(String blockId) {
		flatItemModel(blockId, blockResource(blockId));
	}

	// ITEM MODELS FOR SPECIFIC BLOCKS //

	// The method to generate a trapdoor's block model creates a file
	// modid/models/block/"trapdoorName"_bottom.json
	// which also functions as the trapdoor's item texture.
	public void woodeTrapdoorItem(String woodType) {
		String blockId = woodType + "_trapdoor";
		this.withExistingParent(
			blockId,
			modLoc("block/" + blockId + "_bottom")
		);
	}

	// use minecraft's built-in fence item model, with the texture of its base block
	public void fenceItem(String woodType) {
		this.mcItemModel(
			woodType + "_fence",
			"block/fence_inventory",
			blockResource(woodType + "_planks")
		);
	}

	// use minecraft's built-in button item model, with the texture of its base block
	public void woodButtonItem(String woodType) {
		this.mcItemModel(
			woodType + "_button",
			"block/button_inventory",
			blockResource(woodType + "_planks")
		);
	}

	public void woodTypeItems(
		String woodType,
		String saplingIdPrefix
	){
		blockItemWithFlatBlockTexture(saplingIdPrefix + "_sapling");
		simpleItem(woodType + "_sign");
		simpleItem(woodType + "_hanging_sign");
		blockItemWithBlockTexture(woodType + "_stairs");
		blockItemWithBlockTexture(woodType + "_slab");
		woodButtonItem(woodType);
		blockItemWithBlockTexture(woodType + "_pressure_plate");
		fenceItem(woodType);
		blockItemWithBlockTexture(woodType + "_fence_gate");
		blockItemWithItemTexture(woodType + "_door");
		woodeTrapdoorItem(woodType);
		simpleItem(woodType + "boat");
		simpleItem(woodType + "chest_boat");
	}

	@Override
	protected void registerModels() {
		woodTypeItems("applewood","apple");
	}
}
