package net.hexagonelle.applesaplings.datagen;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
	public ModItemTagGenerator(
			PackOutput packOutput,
			CompletableFuture<HolderLookup.Provider> providerCompletableFuture,
			CompletableFuture<TagLookup<Block>> tagLookupCompletableFuture,
			@Nullable ExistingFileHelper existingFileHelper) {

		super(packOutput, providerCompletableFuture, tagLookupCompletableFuture, AppleSaplings.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {
		this.copy(BlockTags.LOGS_THAT_BURN,ItemTags.LOGS_THAT_BURN);
	}
}
