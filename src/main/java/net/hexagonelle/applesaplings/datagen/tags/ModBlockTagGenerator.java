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
	private void vanillaTag(TagKey<Block> tagKey, RegistryObject<Block>... blockRegistryObjects){
		Stream.of(blockRegistryObjects).forEach(block -> this.tag(tagKey).add(block.get()));
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {
		vanillaTag(SAPLINGS, APPLE_SAPLING);
		vanillaTag(LEAVES,
			FLOWERING_APPLE_LEAVES,
			APPLE_LEAVES
		);
		vanillaTag(LOGS,
			APPLEWOOD_LOG,
			APPLEWOOD_WOOD,
			STRIPPED_APPLEWOOD_LOG,
			STRIPPED_APPLEWOOD_WOOD
		);
		vanillaTag(LOGS_THAT_BURN,
			APPLEWOOD_LOG,
			APPLEWOOD_WOOD,
			STRIPPED_APPLEWOOD_LOG,
			STRIPPED_APPLEWOOD_WOOD
		);
		vanillaTag(PLANKS, APPLEWOOD_PLANKS);
		vanillaTag(SIGNS,
			APPLEWOOD_SIGN,
			APPLEWOOD_WALL_SIGN
		);
		vanillaTag(STANDING_SIGNS, APPLEWOOD_SIGN);
		vanillaTag(WALL_SIGNS, APPLEWOOD_WALL_SIGN);
		vanillaTag(ALL_HANGING_SIGNS,
			APPLEWOOD_HANGING_SIGN,
			APPLEWOOD_WALL_HANGING_SIGN
		);
		vanillaTag(CEILING_HANGING_SIGNS, APPLEWOOD_HANGING_SIGN);
		vanillaTag(WOODEN_STAIRS, APPLEWOOD_STAIRS);
		vanillaTag(WOODEN_SLABS, APPLEWOOD_SLAB);
		vanillaTag(WOODEN_BUTTONS, APPLEWOOD_BUTTON);
		vanillaTag(WOODEN_PRESSURE_PLATES, APPLEWOOD_PRESSURE_PLATE);
		vanillaTag(WOODEN_FENCES,APPLEWOOD_FENCE);
		vanillaTag(FENCE_GATES, APPLEWOOD_FENCE_GATE);
		vanillaTag(WOODEN_DOORS, APPLEWOOD_DOOR);
		vanillaTag(WOODEN_TRAPDOORS, APPLEWOOD_TRAPDOOR);

	}
}
