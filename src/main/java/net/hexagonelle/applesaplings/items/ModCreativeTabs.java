package net.hexagonelle.applesaplings.items;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {

	// Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "applesaplings" namespace
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
			DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AppleSaplings.MODID);

	// Creates a creative tab with the id "applesaplings:example_tab" for the example item, that is placed after the combat tab
	public static final RegistryObject<CreativeModeTab> APPLESAPLINGS_TAB =
			CREATIVE_MODE_TABS.register(
					"applesaplings_tab",
					() ->
						CreativeModeTab.builder()
							.withTabsBefore(CreativeModeTabs.COMBAT)
							.icon(() -> new ItemStack(ModBlocks.APPLE_SAPLING.get()))
							.title(Component.translatable("creativetab.applesaplings_tab"))
							.displayItems((parameters, output) -> {
								// Add the example item to the tab. For your own tabs, this method is preferred over the event

								output.accept(ModBlocks.APPLE_SAPLING.get());
								output.accept(ModBlocks.APPLE_LEAVES.get());
								output.accept(ModBlocks.FLOWERING_APPLE_LEAVES.get());
								output.accept(ModBlocks.APPLEWOOD_LOG.get());
								output.accept(ModBlocks.APPLEWOOD_WOOD.get());
								output.accept(ModBlocks.STRIPPED_APPLEWOOD_LOG.get());
								output.accept(ModBlocks.STRIPPED_APPLEWOOD_WOOD.get());
								output.accept(ModBlocks.APPLEWOOD_PLANKS.get());
								output.accept(ModBlocks.APPLEWOOD_SIGN.get());
								output.accept(ModBlocks.APPLEWOOD_HANGING_SIGN.get());

							}).build()
			);

	public static void register(IEventBus eventBus){
		CREATIVE_MODE_TABS.register(eventBus);
	}
}
