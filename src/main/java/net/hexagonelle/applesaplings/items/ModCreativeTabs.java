package net.hexagonelle.applesaplings.items;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {

	// Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "applesaplings" namespace
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
			DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AppleSaplings.MODID);

	// Creates a creative tab with the id "applesaplings:example_tab" for the example item, that is placed after the combat tab
	public static final RegistryObject<CreativeModeTab> APPLESAPLINGS_TAB = CREATIVE_MODE_TABS.register(
		"applesaplings_tab",
		() -> CreativeModeTab
			.builder()
			.icon(() -> new ItemStack(ModBlocks.APPLE_SAPLING.get()))
			.title(Component.translatable("creativetab.applesaplings_tab"))
			.displayItems(
				(parameters, output) -> {

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
					output.accept(ModBlocks.APPLEWOOD_STAIRS.get());
					output.accept(ModBlocks.APPLEWOOD_SLAB.get());
					output.accept(ModBlocks.APPLEWOOD_BUTTON.get());
					output.accept(ModBlocks.APPLEWOOD_PRESSURE_PLATE.get());
					output.accept(ModBlocks.APPLEWOOD_FENCE.get());
					output.accept(ModBlocks.APPLEWOOD_FENCE_GATE.get());
					output.accept(ModBlocks.APPLEWOOD_DOOR.get());
					output.accept(ModBlocks.APPLEWOOD_TRAPDOOR.get());
					output.accept(ModItems.APPLEWOOD_BOAT.get());
					output.accept(ModItems.APPLEWOOD_CHEST_BOAT.get());
				}
			).build()
	);

	public static void register(IEventBus eventBus){
		CREATIVE_MODE_TABS.register(eventBus);
	}
}
