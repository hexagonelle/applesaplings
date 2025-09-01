package net.hexagonelle.applesaplings.datagen.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {

	public static final TagKey<Block> STRIPPED_LOGS = createForgeBlockTag("stripped_logs");


	private static TagKey<Block> createForgeBlockTag(String name) {
		return BlockTags.create(new ResourceLocation("forge", name));
	}

}
