package net.hexagonelle.examplemod.creativetabs;

import net.hexagonelle.examplemod.items.ItemRegistry;
import net.hexagonelle.examplemod.registration.RegistryObject;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

public class CreativeTabSuppliers {
	public static CreativeModeTab createCreativeTab(
		String tabId,
		RegistryObject<Item> iconItem
	){
		return CreativeModeTab.builder(CreativeModeTab.Row.TOP,0)
			.icon(() -> new ItemStack(iconItem.get()))
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
