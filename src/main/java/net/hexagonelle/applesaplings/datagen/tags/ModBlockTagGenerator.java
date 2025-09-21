package net.hexagonelle.applesaplings.datagen.tags;

import net.hexagonelle.applesaplings.Constants;
import net.hexagonelle.applesaplings.blocks.BlockRegistry;
import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static net.minecraft.tags.BlockTags.*;

public class ModBlockTagGenerator extends BlockTagsProvider {

	public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, Constants.MODID, existingFileHelper);
	}

	private void assignTags(TagKey<Block> tagKey, String... blockIds){
		Stream.of(blockIds).forEach(
			blockId -> {
				Block block = ModBlocks.BLOCK_MAP.get(blockId).get();
				this.tag(tagKey).add(block);
			}
		);
	}

	private void assignWoodTypeTags(String saplingPrefix, String woodType){
		String saplingString = saplingPrefix + "_sapling";
		assignTags(SAPLINGS,saplingString);
		assignTags(LEAVES,saplingPrefix + "_leaves");
		assignTags(LOGS,
			woodType + "_log",
			woodType + "_wood",
			"stripped_" + woodType + "_log",
			"stripped_" + woodType + "_wood"
		);
		assignTags(ModBlockTags.STRIPPED_LOGS,
			"stripped_" + woodType + "_log",
			"stripped_" + woodType + "_wood"
		);
		assignTags(LOGS_THAT_BURN,
			woodType + "_log",
			woodType + "_wood",
			"stripped_" + woodType + "_log",
			"stripped_" + woodType + "_wood"
		);
		assignTags(PLANKS,woodType + "_planks");

		assignTags(SIGNS,woodType + "_sign", woodType + "_wall_sign");
		assignTags(STANDING_SIGNS,woodType + "_sign");
		assignTags(WALL_SIGNS, woodType + "_wall_sign");
		assignTags(ALL_HANGING_SIGNS,woodType + "_hanging_sign", woodType + "_wall_hanging_sign");
		assignTags(CEILING_HANGING_SIGNS,woodType + "_hanging_sign");
		assignTags(WALL_HANGING_SIGNS,woodType + "_wall_hanging_sign");

		assignTags(WOODEN_STAIRS, woodType + "_stairs");
		assignTags(WOODEN_SLABS, woodType + "_slab");
		assignTags(WOODEN_BUTTONS, woodType + "_button");
		assignTags(WOODEN_PRESSURE_PLATES, woodType + "_pressure_plate");
		assignTags(WOODEN_FENCES,woodType + "_fence");
		assignTags(FENCE_GATES, woodType + "_fence_gate");
		assignTags(WOODEN_DOORS, woodType + "_door");
		assignTags(WOODEN_TRAPDOORS, woodType + "_trapdoor");
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {

		assignWoodTypeTags("apple","applewood");
		assignTags(LEAVES, "flowering_apple_leaves");

	}
}
