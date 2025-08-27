package net.hexagonelle.applesaplings.datagen.recipes;

import net.hexagonelle.applesaplings.items.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static net.hexagonelle.applesaplings.blocks.ModBlocks.*;

public class ModRecipeProvider extends RecipeProvider {
	public ModRecipeProvider(PackOutput output) {
		super(output);
	}

	protected static void planksFromLog(
		@NotNull Consumer<FinishedRecipe> writer,
		RegistryObject<Block> logOrWood,
		RegistryObject<Block> planks
	){

		String recipeID = planks.getId().getPath() + "_from_" + logOrWood.getId().getPath();

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planks.get(), 4)
			.unlockedBy(getHasName(logOrWood.get()), has(logOrWood.get()))
			.requires(logOrWood.get())
			.save(writer,recipeID);
	}

	protected static void woodFromLogs(
		@NotNull Consumer<FinishedRecipe> writer,
		RegistryObject<Block> logBlock,
		RegistryObject<Block> woodBlock
	){

		String recipeID = woodBlock.getId().getPath() + "_from_" + logBlock.getId().getPath();

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, woodBlock.get(),3)
			.pattern("AA")
			.pattern("AA")
			.define('A', logBlock.get())
			.unlockedBy(getHasName(logBlock.get()), has(logBlock.get()))
			.save(writer, recipeID);
	}

	protected static void sign(
		Consumer<FinishedRecipe> writer,
		RegistryObject<Item> sign,
		RegistryObject<Block> planks
	){
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, sign.get(), 3)
			.pattern("AAA")
			.pattern("AAA")
			.pattern(" B ")
			.define('A', planks.get())
			.define('B', Items.STICK)
			.unlockedBy(getHasName(planks.get()),has(planks.get()))
			.group("sign")
			.save(writer);
	}

	protected static void hangingSign(
		Consumer<FinishedRecipe> writer,
		RegistryObject<Item> sign,
		RegistryObject<Block> material
	){
		ShapedRecipeBuilder.shaped(
				RecipeCategory.DECORATIONS, sign.get(), 6)
			.pattern("B B")
			.pattern("AAA")
			.pattern("AAA")
			.define('A', material.get())
			.define('B', Items.CHAIN)
			.group("hanging_sign")
			.unlockedBy("has_stripped_logs", has(material.get()))
			.save(writer);
	}

	protected static void slab(
		Consumer<FinishedRecipe> writer,
		RegistryObject<Block> slab,
		RegistryObject<Block> material
	){
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slab.get(), 6)
			.pattern("AAA")
			.define('A', material.get())
			.unlockedBy(getHasName(material.get()), has(material.get()))
			.save(writer);
	}

	protected static void stairs(
		Consumer<FinishedRecipe> writer,
		RegistryObject<Block> stairs,
		RegistryObject<Block> material
	){
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stairs.get(), 4)
			.pattern("A  ")
			.pattern("AA ")
			.pattern("AAA")
			.define('A', material.get())
			.unlockedBy(getHasName(material.get()), has(material.get()))
			.save(writer);
	}

	protected static void button(
		Consumer<FinishedRecipe> writer,
		RegistryObject<Block> button,
		RegistryObject<Block> material
	){
		ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, button.get())
			.requires(material.get())
			.unlockedBy(getHasName(material.get()), has(material.get()))
			.save(writer);
	}

	protected static void pressurePlate(
		Consumer<FinishedRecipe> writer,
		RegistryObject<Block> pressurePlate,
		RegistryObject<Block> material
	){
		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, pressurePlate.get())
			.pattern("AA")
			.define('A', material.get())
			.unlockedBy(getHasName(material.get()), has(material.get()))
			.save(writer);
	}

	protected static void woodenFence(
		Consumer<FinishedRecipe> writer,
		RegistryObject<Block> fence,
		RegistryObject<Block> planks
	){
		ShapedRecipeBuilder.shaped(
				RecipeCategory.DECORATIONS, fence.get(), 3)
			.pattern("ABA")
			.pattern("ABA")
			.define('A', planks.get())
			.define('B', Items.STICK)
			.unlockedBy(getHasName(planks.get()), has(planks.get()))
			.save(writer);
	}

	protected static void woodenFenceGate(
		Consumer<FinishedRecipe> writer,
		RegistryObject<Block> fenceGate,
		RegistryObject<Block> planks
	){
		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, fenceGate.get())
			.pattern("BAB")
			.pattern("BAB")
			.define('A', planks.get())
			.define('B', Items.STICK)
			.unlockedBy(getHasName(planks.get()), has(planks.get()))
			.save(writer);
	}

	protected static void door(
		Consumer<FinishedRecipe> writer,
		RegistryObject<Block> door,
		RegistryObject<Block> material
	){
		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, door.get(), 3)
			.pattern("AA")
			.pattern("AA")
			.pattern("AA")
			.define('A', material.get())
			.unlockedBy(getHasName(material.get()), has(material.get()))
			.save(writer);
	}

	protected static void trapdoor(
		Consumer<FinishedRecipe> writer,
		RegistryObject<Block> trapdoor,
		RegistryObject<Block> material
	){
		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, trapdoor.get(), 2)
			.pattern("AAA")
			.pattern("AAA")
			.define('A', material.get())
			.unlockedBy(getHasName(material.get()), has(material.get()))
			.save(writer);
	}

	protected static void woodenBoat(
		Consumer<FinishedRecipe> writer,
		RegistryObject<Item> boat,
		RegistryObject<Block> planks
	){

		String recipeID = boat.getId().getPath() + "_from_" + planks.getId().getPath();

		ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, boat.get())
			.pattern("A A")
			.pattern("AAA")
			.define('A', planks.get())
			.group("boat")
			.unlockedBy("in_water", insideOf(Blocks.WATER))
			.save(writer, recipeID);
	}

	protected static void chestBoat(
		Consumer<FinishedRecipe> writer,
		RegistryObject<Item> chestBoat,
		RegistryObject<Item> boat
	){
		ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, chestBoat.get())
			.requires(Blocks.CHEST)
			.requires(boat.get())
			.group("chest_boat")
			.unlockedBy("has_boat", has(ItemTags.BOATS)).save(writer);
	}

	@Override
	protected void buildRecipes(@NotNull Consumer<FinishedRecipe> writer) {
		ShapelessRecipeBuilder
			.shapeless(RecipeCategory.MISC, APPLE_SAPLING.get(), 1)
			.unlockedBy(getHasName(APPLE_SAPLING.get()), has(APPLE_SAPLING.get()))
			.requires(Items.OAK_SAPLING)
			.requires(Items.APPLE)
			.save(writer);

		woodFromLogs(writer, APPLEWOOD_LOG, APPLEWOOD_WOOD);
		woodFromLogs(writer, STRIPPED_APPLEWOOD_LOG, STRIPPED_APPLEWOOD_WOOD);
		planksFromLog(writer, APPLEWOOD_LOG, APPLEWOOD_PLANKS);
		planksFromLog(writer, APPLEWOOD_WOOD, APPLEWOOD_PLANKS);
		planksFromLog(writer, STRIPPED_APPLEWOOD_LOG, APPLEWOOD_PLANKS);
		planksFromLog(writer, STRIPPED_APPLEWOOD_WOOD, APPLEWOOD_PLANKS);
		sign(writer,ModItems.APPLEWOOD_SIGN, APPLEWOOD_PLANKS);
		hangingSign(writer, ModItems.APPLEWOOD_HANGING_SIGN,STRIPPED_APPLEWOOD_LOG);
		slab(writer, APPLEWOOD_SLAB, APPLEWOOD_PLANKS);
		stairs(writer, APPLEWOOD_STAIRS, APPLEWOOD_PLANKS);
		button(writer, APPLEWOOD_BUTTON, APPLEWOOD_PLANKS);
		pressurePlate(writer, APPLEWOOD_PRESSURE_PLATE, APPLEWOOD_PLANKS);
		woodenFence(writer, APPLEWOOD_FENCE, APPLEWOOD_PLANKS);
		woodenFenceGate(writer, APPLEWOOD_FENCE_GATE, APPLEWOOD_PLANKS);
		door(writer, APPLEWOOD_DOOR, APPLEWOOD_PLANKS);
		trapdoor(writer, APPLEWOOD_TRAPDOOR, APPLEWOOD_PLANKS);
		woodenBoat(writer, ModItems.APPLEWOOD_BOAT, APPLEWOOD_PLANKS);
		chestBoat(writer, ModItems.APPLEWOOD_CHEST_BOAT, ModItems.APPLEWOOD_BOAT);

	}

}
