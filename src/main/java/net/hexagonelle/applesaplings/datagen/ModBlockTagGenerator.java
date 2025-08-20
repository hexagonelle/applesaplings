package net.hexagonelle.applesaplings.datagen;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {

	public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, AppleSaplings.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {
		this.tag(BlockTags.SAPLINGS)
			.add(ModBlocks.APPLE_SAPLING.get());
		this.tag(BlockTags.LEAVES)
			.add(ModBlocks.FLOWERING_APPLE_LEAVES.get())
			.add(ModBlocks.APPLE_LEAVES.get());
		this.tag(BlockTags.LOGS)
			.add(ModBlocks.APPLEWOOD_LOG.get())
			.add(ModBlocks.APPLEWOOD_WOOD.get())
			.add(ModBlocks.STRIPPED_APPLEWOOD_LOG.get())
			.add(ModBlocks.STRIPPED_APPLEWOOD_WOOD.get());
		this.tag(BlockTags.LOGS_THAT_BURN)
			.add(ModBlocks.APPLEWOOD_LOG.get())
			.add(ModBlocks.APPLEWOOD_WOOD.get())
			.add(ModBlocks.STRIPPED_APPLEWOOD_LOG.get())
			.add(ModBlocks.STRIPPED_APPLEWOOD_WOOD.get());
		this.tag(BlockTags.PLANKS)
			.add(ModBlocks.APPLEWOOD_PLANKS.get());
	}
}
