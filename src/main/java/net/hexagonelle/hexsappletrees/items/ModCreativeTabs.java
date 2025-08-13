package net.hexagonelle.hexsappletrees.items;

import net.hexagonelle.hexsappletrees.HexsAppleTrees;
import net.hexagonelle.hexsappletrees.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {

	// Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "hexsappletrees" namespace
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
			DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HexsAppleTrees.MODID);

	// Creates a creative tab with the id "hexsappletrees:example_tab" for the example item, that is placed after the combat tab
	public static final RegistryObject<CreativeModeTab> APPLE_TREES_TAB =
			CREATIVE_MODE_TABS.register("hexsappletrees_tab", () ->
				CreativeModeTab.builder()
					.withTabsBefore(CreativeModeTabs.COMBAT)
					.icon(() -> new ItemStack(ModBlocks.APPLE_SAPLING.get()))
					.title(Component.translatable("creativetab.hexsappletrees_tab"))
					.displayItems((parameters, output) -> {
						// Add the example item to the tab. For your own tabs, this method is preferred over the event

						output.accept(ModBlocks.APPLE_SAPLING.get());
						output.accept(ModBlocks.APPLE_LEAVES.get());

					}).build()
			);

	public static void register(IEventBus eventBus){
		CREATIVE_MODE_TABS.register(eventBus);
	}
}
