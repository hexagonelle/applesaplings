package net.hexagonelle.applesaplings.plugins.applecrates.blocks.entity;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.hexagonelle.applesaplings.blocks.entity.ModBlockEntities;
import net.hexagonelle.applesaplings.items.ModItems;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

//@Mod.EventBusSubscriber(modid = AppleSaplings.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CrateCompat {


	public static void init(){
		new jackdaw.applecrates.api.AppleCrateAPI.AppleCrateBuilder(
			AppleSaplings.MODID,
			AppleSaplings.MODID,
			"applewood"
		).register();

		jackdaw.applecrates.api.GeneralRegistry.prepareForRegistry(
			AppleSaplings.MODID,
			ModBlocks.BLOCKS,
			ModItems.ITEMS,
			ModBlockEntities.BLOCK_ENTITIES
		);

	};

	@SubscribeEvent
	public static void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey().equals(jackdaw.applecrates.api.GeneralRegistry.CRATE_TAB.getKey())) {
			for (RegistryObject<Item> item : ModItems.ITEMS.getEntries())
				if (item.getId().getPath().contains("crate")) event.accept(item);
		}
	}

}
