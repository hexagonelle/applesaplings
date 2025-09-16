package net.hexagonelle.applesaplings.creativetabs;

import net.hexagonelle.applesaplings.items.ItemRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

public class CreativeTabSuppliers {

	@SuppressWarnings("deprecation")
	public static CreativeModeTab createCreativeTab(
		String tabId,
		String iconItemId
	){
		return CreativeModeTab.builder(CreativeModeTab.Row.TOP,0)
			.icon(() -> new ItemStack(ItemRegistry.ITEM_MAP.get(iconItemId).get()))
			.title(Component.translatable("creativetab." + tabId))
			.displayItems((parameters, output) -> {
				CreativeTabRegistry.ITEM_MODTAB_MAP.forEach(
					(itemRegistryObject, creativeTabKey) -> {
						if (Objects.equals(creativeTabKey, tabId))
							output.accept(itemRegistryObject.get());
					});
			}).build();
	}

}
