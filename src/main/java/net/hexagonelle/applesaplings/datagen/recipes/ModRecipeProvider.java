package net.hexagonelle.applesaplings.datagen.recipes;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static net.hexagonelle.applesaplings.blocks.ModBlocks.*;

public class ModRecipeProvider extends RecipeProvider {
	public ModRecipeProvider(PackOutput output) {
		super(output);
	}

	private static String getPlanksId(String woodType){
		return woodType + "_planks";
	}
	private static String getLogId(String woodType){
		return woodType + "_log";
	}
	private static String getWoodId(String woodType){
		return woodType + "_wood";
	}

	private static Block getPlanks(String woodType){
		return BLOCK_MAP.get(getPlanksId(woodType)).get();
	}
	private static Block getLog(String woodType){
		return BLOCK_MAP.get(getLogId(woodType)).get();
	}
	private static Block getWood(String woodType){
		return BLOCK_MAP.get(getWoodId(woodType)).get();
	}

	protected static void planksFromLog(
		@NotNull Consumer<FinishedRecipe> writer,
		String woodType
	){
		Block logBlock = getLog(woodType);

		String recipeID = getPlanksId(woodType) + "_from_" + getLogId(woodType);;
		ShapelessRecipeBuilder.shapeless(
			RecipeCategory.BUILDING_BLOCKS,
				getPlanks(woodType), 4
			)
			.unlockedBy(getHasName(logBlock), has(logBlock))
			.requires(logBlock)
			.save(writer,recipeID);
	}

	protected static void planksFromWood(
		@NotNull Consumer<FinishedRecipe> writer,
		String woodType
	){
		Block woodBlock = getWood(woodType);

		String recipeID = getPlanksId(woodType) + "_from_" + getWoodId(woodType);;
		ShapelessRecipeBuilder.shapeless(
				RecipeCategory.BUILDING_BLOCKS,
				getPlanks(woodType), 4
			)
			.unlockedBy(getHasName(woodBlock), has(woodBlock))
			.requires(woodBlock)
			.save(writer,recipeID);
	}

	protected static void woodFromLogs(
		@NotNull Consumer<FinishedRecipe> writer,
		String woodType
	){
		Block logBlock = getLog(woodType);

		String recipeID = getWoodId(woodType) + "_from_" + getLogId(woodType);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, getWood(woodType),3)
			.pattern("AA")
			.pattern("AA")
			.define('A', logBlock)
			.unlockedBy(getHasName(logBlock), has(logBlock))
			.save(writer, recipeID);
	}

	protected static void sign(
		Consumer<FinishedRecipe> writer,
		String woodType
	){
		Block planksBlock = getPlanks(woodType);
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BLOCK_MAP.get(woodType + "_sign").get(), 3)
			.pattern("AAA")
			.pattern("AAA")
			.pattern(" B ")
			.define('A', planksBlock)
			.define('B', Items.STICK)
			.unlockedBy(getHasName(planksBlock),has(planksBlock))
			.group("sign")
			.save(writer);
	}

	protected static void hangingSign(
		Consumer<FinishedRecipe> writer,
		String woodType
	){
		Block strippedLogBlock = BLOCK_MAP.get("stripped_" + woodType + "_log").get();
		ShapedRecipeBuilder.shaped(
				RecipeCategory.DECORATIONS, BLOCK_MAP.get(woodType + "_hanging_sign").get(), 6)
			.pattern("B B")
			.pattern("AAA")
			.pattern("AAA")
			.define('A', strippedLogBlock)
			.define('B', Items.CHAIN)
			.group("hanging_sign")
			.unlockedBy("has_stripped_logs", has(strippedLogBlock))
			.save(writer);
	}

	protected static void slab(
		Consumer<FinishedRecipe> writer,
		String woodType
	){
		Block planks = getPlanks(woodType);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BLOCK_MAP.get(woodType + "_slab").get(), 6)
			.pattern("AAA")
			.define('A', planks)
			.unlockedBy(getHasName(planks), has(planks))
			.save(writer);
	}

	protected static void stairs(
		Consumer<FinishedRecipe> writer,
		String woodType
	){
		Block planks = getPlanks(woodType);

		ShapedRecipeBuilder.shaped(
			RecipeCategory.BUILDING_BLOCKS,
			BLOCK_MAP.get(woodType + "_stairs").get(), 4
		)
			.pattern("A  ")
			.pattern("AA ")
			.pattern("AAA")
			.define('A', planks)
			.unlockedBy(getHasName(planks), has(planks))
			.save(writer);
	}

	protected static void woodButton(
		Consumer<FinishedRecipe> writer,
		String woodType
	){
		Block planks = getPlanks(woodType);

		ShapelessRecipeBuilder.shapeless(
			RecipeCategory.REDSTONE,
			BLOCK_MAP.get(woodType + "_button").get()
		)
			.requires(planks)
			.unlockedBy(getHasName(planks), has(planks))
			.save(writer);
	}

	protected static void woodPressurePlate(
		Consumer<FinishedRecipe> writer,
		String woodType
	){
		Block planks = getPlanks(woodType);

		ShapedRecipeBuilder.shaped(
			RecipeCategory.REDSTONE,
			BLOCK_MAP.get(woodType + "_pressure_plate").get()
		)
			.pattern("AA")
			.define('A', planks)
			.unlockedBy(getHasName(planks), has(planks))
			.save(writer);
	}

	protected static void fence(
		Consumer<FinishedRecipe> writer,
		String woodType
	){
		Block planks = getPlanks(woodType);

		ShapedRecipeBuilder.shaped(
			RecipeCategory.DECORATIONS,
				BLOCK_MAP.get(woodType + "_fence").get(), 3
		)
			.pattern("ABA")
			.pattern("ABA")
			.define('A', planks)
			.define('B', Items.STICK)
			.unlockedBy(getHasName(planks), has(planks))
			.save(writer);
	}

	protected static void fenceGate(
		Consumer<FinishedRecipe> writer,
		String woodType
	){
		Block planks = getPlanks(woodType);

		ShapedRecipeBuilder.shaped(
			RecipeCategory.REDSTONE,
			BLOCK_MAP.get(woodType + "_fence_gate").get()
		)
			.pattern("BAB")
			.pattern("BAB")
			.define('A', planks)
			.define('B', Items.STICK)
			.unlockedBy(getHasName(planks), has(planks))
			.save(writer);
	}

	protected static void woodDoor(
		Consumer<FinishedRecipe> writer,
		String woodType
	){
		Block planks = getPlanks(woodType);

		ShapedRecipeBuilder.shaped(
		RecipeCategory.REDSTONE,
				BLOCK_MAP.get(woodType + "_door").get(), 3
		)
			.pattern("AA")
			.pattern("AA")
			.pattern("AA")
			.define('A', planks)
			.unlockedBy(getHasName(planks), has(planks))
			.save(writer);
	}

	protected static void trapdoor(
		Consumer<FinishedRecipe> writer,
		String woodType
	){
		Block planks = getPlanks(woodType);

		ShapedRecipeBuilder.shaped(
			RecipeCategory.REDSTONE,
				BLOCK_MAP.get(woodType + "_trapdoor").get(), 2
		)
			.pattern("AAA")
			.pattern("AAA")
			.define('A', planks)
			.unlockedBy(getHasName(planks), has(planks))
			.save(writer);
	}

	protected static void woodenBoat(
		Consumer<FinishedRecipe> writer,
		String woodType
	){

		String recipeID = woodType + "boat_from_" + getPlanksId(woodType);
		ShapedRecipeBuilder.shaped(
			RecipeCategory.TRANSPORTATION,
			BLOCK_MAP.get(woodType + "_boat").get()
		)
			.pattern("A A")
			.pattern("AAA")
			.define('A', getPlanks(woodType))
			.group("boat")
			.unlockedBy("in_water", insideOf(Blocks.WATER))
			.save(writer, recipeID);
	}

	protected static void chestBoat(
		Consumer<FinishedRecipe> writer,
		String woodType
	){
		ShapelessRecipeBuilder.shapeless(
			RecipeCategory.TRANSPORTATION,
				BLOCK_MAP.get(woodType + "_chest_boat").get()
		)
			.requires(Blocks.CHEST)
			.requires(BLOCK_MAP.get(woodType + "_boat").get())
			.group("chest_boat")
			.unlockedBy("has_boat", has(ItemTags.BOATS)).save(writer);
	}

	protected static void woodTypesRecipes(
		Consumer<FinishedRecipe> writer,
		String woodType
	){
		woodFromLogs(writer,woodType);
		woodFromLogs(writer,woodType);
		planksFromLog(writer,woodType);
		planksFromWood(writer,woodType);
		planksFromLog(writer,woodType);
		planksFromLog(writer,woodType);
		sign(writer,woodType);
		hangingSign(writer,woodType);
		slab(writer,woodType);
		stairs(writer,woodType);
		woodButton(writer,woodType);
		woodPressurePlate(writer,woodType);
		fence(writer,woodType);
		fenceGate(writer,woodType);
		woodDoor(writer,woodType);
		trapdoor(writer,woodType);
		woodenBoat(writer,woodType);
		chestBoat(writer,woodType);
	}

	@Override
	protected void buildRecipes(@NotNull Consumer<FinishedRecipe> writer) {
		ShapelessRecipeBuilder
			.shapeless(RecipeCategory.MISC, BLOCK_MAP.get("apple_sapling").get(), 1)
			.unlockedBy(getHasName(Items.APPLE), has(Items.APPLE))
			.requires(Items.OAK_SAPLING)
			.requires(Items.APPLE)
			.save(writer);

		woodTypesRecipes(writer,"applewood");
	}

}
