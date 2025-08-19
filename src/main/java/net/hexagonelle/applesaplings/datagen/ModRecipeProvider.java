package net.hexagonelle.applesaplings.datagen;

import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
	public ModRecipeProvider(PackOutput output) {
		super(output);
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
	}
}
