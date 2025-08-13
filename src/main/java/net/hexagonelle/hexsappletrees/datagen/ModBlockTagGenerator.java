package net.hexagonelle.hexsappletrees.datagen;

import net.hexagonelle.hexsappletrees.HexsAppleTrees;
import net.hexagonelle.hexsappletrees.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {

	public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, HexsAppleTrees.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(BlockTags.SAPLINGS)
				.add(ModBlocks.APPLE_SAPLING.get());
		this.tag(BlockTags.LEAVES)
				.add(ModBlocks.APPLE_LEAVES.get());
	}
}
