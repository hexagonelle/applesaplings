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
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {

	public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, AppleSaplings.MODID, existingFileHelper);
	}

	private ResourceLocation key(RegistryObject<Item> itemRegistryObject){
		return ForgeRegistries.ITEMS.getKey(itemRegistryObject.get());
//			itemRegistryObject.getId().getPath();
	}

	private final String itemNamespace = AppleSaplings.MODID + ":item/";

	// return the String corresponding to the path of a Block
	private String name(RegistryObject<Item> itemRegistryObject){
		itemRegistryObject.getId();
		return key(itemRegistryObject).getPath();
	}

	private ItemModelBuilder generatedItem(String path, ResourceLocation texture){
		return withExistingParent(path, new ResourceLocation("item/generated"))
			.texture("layer0", texture);
	}

	private ItemModelBuilder simpleItem(RegistryObject<Item> itemRegistryObject){
		return this.generatedItem(name(itemRegistryObject),key(itemRegistryObject));
	}

	private ItemModelBuilder blockItem(RegistryObject<Block> item){
		return generatedItem(ModBlockStateProvider.name(item), ModBlockStateProvider.key(item));
	}

	public void evenSimplerBlockItem(RegistryObject<Block> block) {
		this.withExistingParent(
			AppleSaplings.MODID + ":" + ModBlockStateProvider.name(block),
			modLoc("block/" + ModBlockStateProvider.name(block))
		);
	}

	public void trapdoorItem(RegistryObject<Block> block) {
		this.withExistingParent(
			ModBlockStateProvider.name(block),
			modLoc("block/" + ModBlockStateProvider.name(block) + "_bottom"));
	}

	public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
		this.withExistingParent(ModBlockStateProvider.name(block), mcLoc("block/fence_inventory"))
			.texture("texture", new ResourceLocation(
				AppleSaplings.MODID,
				"block/" + ModBlockStateProvider.name(baseBlock)
			));
	}

	public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
		this.withExistingParent(ModBlockStateProvider.name(block), mcLoc("block/button_inventory"))
			.texture("texture",  new ResourceLocation(
				AppleSaplings.MODID, "block/" + ModBlockStateProvider.name(baseBlock)
			));
	}

	@Override
	protected void registerModels() {
		simpleItem(ModItems.APPLEWOOD_SIGN);
		simpleItem(ModItems.APPLEWOOD_HANGING_SIGN);
		blockItem(ModBlocks.APPLE_SAPLING);

		blockItem(ModBlocks.APPLEWOOD_DOOR);
		fenceItem(ModBlocks.APPLEWOOD_FENCE, ModBlocks.APPLEWOOD_PLANKS);
		buttonItem(ModBlocks.APPLEWOOD_BUTTON, ModBlocks.APPLEWOOD_PLANKS);

		evenSimplerBlockItem(ModBlocks.APPLEWOOD_STAIRS);
		evenSimplerBlockItem(ModBlocks.APPLEWOOD_SLAB);
		evenSimplerBlockItem(ModBlocks.APPLEWOOD_PRESSURE_PLATE);
		evenSimplerBlockItem(ModBlocks.APPLEWOOD_FENCE_GATE);

		trapdoorItem(ModBlocks.APPLEWOOD_TRAPDOOR);

	}
}
