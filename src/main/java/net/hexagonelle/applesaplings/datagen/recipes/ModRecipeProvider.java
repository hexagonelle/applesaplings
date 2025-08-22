package net.hexagonelle.applesaplings.datagen.recipes;

import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
	public ModRecipeProvider(PackOutput output) {
		super(output);
	}

	private void LogOrWoodToPlanks(
		@NotNull Consumer<FinishedRecipe> writer,
		RegistryObject<Block> logOrWood,
		RegistryObject<Block> planks
	){

		String recipeID = logOrWood.getId().getPath() + "_to_" + planks.getId().getPath();

		ShapelessRecipeBuilder
			.shapeless(
				RecipeCategory.MISC,
				planks.get(), 4
			).unlockedBy(
				getHasName(logOrWood.get()),
				has(logOrWood.get())
			)
			.requires(logOrWood.get())
			.save(writer,recipeID);
	}

	private void LogToWood(
		@NotNull Consumer<FinishedRecipe> writer,
		RegistryObject<Block> logBlock,
		RegistryObject<Block> woodBlock
	){

		String recipeID = logBlock.getId().getPath() + "_to_" + woodBlock.getId().getPath();

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, woodBlock.get(),3)
			.pattern("AA")
			.pattern("AA")
			.define('A', logBlock.get())
			.unlockedBy(getHasName(logBlock.get()), has(logBlock.get()))
			.save(writer,recipeID);
	}

	@Override
	protected void buildRecipes(@NotNull Consumer<FinishedRecipe> writer) {
		ShapelessRecipeBuilder
			.shapeless(
				RecipeCategory.MISC,
				ModBlocks.APPLE_SAPLING.get(),
				1
			).unlockedBy(
				getHasName(ModBlocks.APPLE_SAPLING.get()),
				has(ModBlocks.APPLE_SAPLING.get())
			)
			.requires(Items.OAK_SAPLING)
			.requires(Items.APPLE)
			.save(writer);

		LogOrWoodToPlanks(writer,ModBlocks.APPLEWOOD_LOG,ModBlocks.APPLEWOOD_PLANKS);
		LogOrWoodToPlanks(writer,ModBlocks.APPLEWOOD_WOOD,ModBlocks.APPLEWOOD_PLANKS);
		LogOrWoodToPlanks(writer,ModBlocks.STRIPPED_APPLEWOOD_LOG,ModBlocks.APPLEWOOD_PLANKS);
		LogOrWoodToPlanks(writer,ModBlocks.STRIPPED_APPLEWOOD_WOOD,ModBlocks.APPLEWOOD_PLANKS);
		LogToWood(writer,ModBlocks.APPLEWOOD_LOG,ModBlocks.APPLEWOOD_WOOD);
		LogToWood(writer,ModBlocks.STRIPPED_APPLEWOOD_LOG,ModBlocks.STRIPPED_APPLEWOOD_WOOD);

	}
}
