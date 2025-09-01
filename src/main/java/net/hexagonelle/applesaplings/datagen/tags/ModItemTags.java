package net.hexagonelle.applesaplings.datagen.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {

	public static final TagKey<Item> STRIPPED_LOGS = createForgeItemTag("stripped_logs");

	private static TagKey<Item> createForgeItemTag(String name) {
		return ItemTags.create(new ResourceLocation("forge", name));
	}

}
