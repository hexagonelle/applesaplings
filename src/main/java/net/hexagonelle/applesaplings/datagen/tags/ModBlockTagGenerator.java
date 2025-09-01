package net.hexagonelle.applesaplings.datagen.tags;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static net.hexagonelle.applesaplings.blocks.ModBlocks.*;
import static net.minecraft.tags.BlockTags.*;

public class ModBlockTagGenerator extends BlockTagsProvider {

	public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, AppleSaplings.MODID, existingFileHelper);
	}

	@SafeVarargs
	private void assignTags(TagKey<Block> tagKey, RegistryObject<Block>... blockRegistryObjects){
		Stream.of(blockRegistryObjects).forEach(block -> this.tag(tagKey).add(block.get()));
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {

		assignTags(SAPLINGS, APPLE_SAPLING);
		assignTags(LEAVES,
			FLOWERING_APPLE_LEAVES,
			APPLE_LEAVES
		);
		assignTags(LOGS,
			APPLEWOOD_LOG,
			APPLEWOOD_WOOD,
			STRIPPED_APPLEWOOD_LOG,
			STRIPPED_APPLEWOOD_WOOD
		);
		assignTags(ModBlockTags.STRIPPED_LOGS,
			STRIPPED_APPLEWOOD_LOG,
			STRIPPED_APPLEWOOD_WOOD
		);
		assignTags(LOGS_THAT_BURN,
			APPLEWOOD_LOG,
			APPLEWOOD_WOOD,
			STRIPPED_APPLEWOOD_LOG,
			STRIPPED_APPLEWOOD_WOOD
		);
		assignTags(PLANKS, APPLEWOOD_PLANKS);
		assignTags(SIGNS,
			APPLEWOOD_SIGN,
			APPLEWOOD_WALL_SIGN
		);
		assignTags(STANDING_SIGNS, APPLEWOOD_SIGN);
		assignTags(WALL_SIGNS, APPLEWOOD_WALL_SIGN);
		assignTags(ALL_HANGING_SIGNS,
			APPLEWOOD_HANGING_SIGN,
			APPLEWOOD_WALL_HANGING_SIGN
		);
		assignTags(CEILING_HANGING_SIGNS, APPLEWOOD_HANGING_SIGN);
		assignTags(WOODEN_STAIRS, APPLEWOOD_STAIRS);
		assignTags(WOODEN_SLABS, APPLEWOOD_SLAB);
		assignTags(WOODEN_BUTTONS, APPLEWOOD_BUTTON);
		assignTags(WOODEN_PRESSURE_PLATES, APPLEWOOD_PRESSURE_PLATE);
		assignTags(WOODEN_FENCES,APPLEWOOD_FENCE);
		assignTags(FENCE_GATES, APPLEWOOD_FENCE_GATE);
		assignTags(WOODEN_DOORS, APPLEWOOD_DOOR);
		assignTags(WOODEN_TRAPDOORS, APPLEWOOD_TRAPDOOR);

	}
}
