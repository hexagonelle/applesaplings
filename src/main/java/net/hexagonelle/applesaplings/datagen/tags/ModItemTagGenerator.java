package net.hexagonelle.applesaplings.datagen.tags;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.items.ModItems;
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
		@Nullable ExistingFileHelper existingFileHelper
	){
		super(packOutput, providerCompletableFuture, tagLookupCompletableFuture, AppleSaplings.MODID, existingFileHelper);
	}

@Override
	protected void addTags(HolderLookup.@NotNull Provider provider){
		this.copy(BlockTags.SAPLINGS,ItemTags.SAPLINGS);
		this.copy(BlockTags.LEAVES,ItemTags.LEAVES);
		this.copy(BlockTags.LOGS,ItemTags.LOGS);
	this.copy(BlockTags.LOGS_THAT_BURN,ItemTags.LOGS_THAT_BURN);
	this.copy(ModBlockTags.STRIPPED_LOGS,ModItemTags.STRIPPED_LOGS);
		this.copy(BlockTags.PLANKS,ItemTags.PLANKS);
		this.tag(ItemTags.SIGNS).add(ModItems.APPLEWOOD_SIGN.get());
		this.tag(ItemTags.HANGING_SIGNS).add(ModItems.APPLEWOOD_HANGING_SIGN.get());
		this.copy(BlockTags.WOODEN_STAIRS,ItemTags.WOODEN_STAIRS);
		this.copy(BlockTags.WOODEN_FENCES,ItemTags.WOODEN_FENCES);
		this.copy(BlockTags.FENCE_GATES,ItemTags.FENCE_GATES);
	}

}
