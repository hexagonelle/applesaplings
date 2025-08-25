package net.hexagonelle.applesaplings.datagen.models;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.hexagonelle.applesaplings.items.ModItems;
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
		super(output, AppleSaplings.MODID, existingFileHelper);
	}

	// returns the itemId of the item.
	private String itemName(RegistryObject<Item> itemRegistryObject){
		return itemRegistryObject.getId().getPath();
	}
	// returns the blockId of the item.
	private String blockName(RegistryObject<Block> blockRegistryObject){
		return blockRegistryObject.getId().getPath();
	}

	// provides a resource location for an item with namespace equal to the given namespace
	// and location equal to the itemPath of the provided itemRegistryObject
	private ResourceLocation itemResource(RegistryObject<Item> itemRegistryObject){
		return modLoc("item/" + itemName(itemRegistryObject));
	}
	// provides a resource location for a block with namespace equal to the given namespace
	// and location equal to the blockPath of the provided blockRegistryObject
	private ResourceLocation blockResource(RegistryObject<Block> blockRegistryObject){
		return modLoc("block/" + blockName(blockRegistryObject));
	}
	// provides a resource location for an item with a block parent,
	// with namespace equal to the given namespace,
	// and location equal to the itemPath of the provided blockRegistryObject
	private ResourceLocation blockItemResource(RegistryObject<Block> blockRegistryObject){
		return modLoc("item/" + blockName(blockRegistryObject));
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
	private ItemModelBuilder simpleItem(RegistryObject<Item> itemRegistryObject){
		return flatItemModel(itemName(itemRegistryObject), itemResource(itemRegistryObject));
	}

	// generates a generic item model with the name of the corresponding block,
	// and a texture located at modid/textures/item/"itemName".png
	private ItemModelBuilder blockItemWithItemTexture(RegistryObject<Block> blockRegistryObject){
		return flatItemModel(blockName(blockRegistryObject), blockItemResource(blockRegistryObject));
	}

	// generates an item form of a standard block with the texture taken from the block texture
	public void blockItemWithBlockTexture(RegistryObject<Block> blockRegistryObject) {
		this.withExistingParent(blockName(blockRegistryObject), blockResource(blockRegistryObject));
	}

	// generates a flat item form of a block with the texture taken from the block texture
	public void blockItemWithFlatBlockTexture(RegistryObject<Block> blockRegistryObject) {
		flatItemModel(blockName(blockRegistryObject), blockResource(blockRegistryObject));
	}

	// The method to generate a trapdoor's block model creates a file
	// modid/models/block/"trapdoorName"_bottom.json
	// which also functions as the trapdoor's item texture.
	public void trapdoorItem(RegistryObject<Block> blockRegistryObject) {
		this.withExistingParent(
			blockName(blockRegistryObject),
			modLoc("block/" + blockName(blockRegistryObject) + "_bottom")
		);
	}

	// use minecraft's built-in fence item model, with the texture of its base block
	public void fenceItem(RegistryObject<Block> blockRegistryObject, RegistryObject<Block> baseBlockRegistryObject) {
		this.mcItemModel(
			blockName(blockRegistryObject),
			"block/fence_inventory",
			blockResource(baseBlockRegistryObject)
		);
	}

	// use minecraft's built-in button item model, with the texture of its base block
	public void buttonItem(RegistryObject<Block> blockRegistryObject, RegistryObject<Block> baseBlockRegistryObject) {
		this.mcItemModel(
			blockName(blockRegistryObject),
			"block/button_inventory",
			blockResource(baseBlockRegistryObject)
		);
	}

	@Override
	protected void registerModels() {
		blockItemWithFlatBlockTexture(ModBlocks.APPLE_SAPLING);
		simpleItem(ModItems.APPLEWOOD_SIGN);
		simpleItem(ModItems.APPLEWOOD_HANGING_SIGN);
		blockItemWithBlockTexture(ModBlocks.APPLEWOOD_STAIRS);
		blockItemWithBlockTexture(ModBlocks.APPLEWOOD_SLAB);
		buttonItem(ModBlocks.APPLEWOOD_BUTTON, ModBlocks.APPLEWOOD_PLANKS);
		blockItemWithBlockTexture(ModBlocks.APPLEWOOD_PRESSURE_PLATE);
		fenceItem(ModBlocks.APPLEWOOD_FENCE, ModBlocks.APPLEWOOD_PLANKS);
		blockItemWithBlockTexture(ModBlocks.APPLEWOOD_FENCE_GATE);
		blockItemWithItemTexture(ModBlocks.APPLEWOOD_DOOR);
		trapdoorItem(ModBlocks.APPLEWOOD_TRAPDOOR);
		simpleItem(ModItems.APPLEWOOD_BOAT);
		simpleItem(ModItems.APPLEWOOD_CHEST_BOAT);

	}
}
