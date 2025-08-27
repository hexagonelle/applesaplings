package net.hexagonelle.applesaplings.plugins.jade;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IJadeProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum ExampleComponentProvider implements IBlockComponentProvider {
	INSTANCE;

	@Override
	public int getDefaultPriority(){

		return 1;
	}

	@Override
	public void appendTooltip(
		ITooltip tooltip,
		BlockAccessor accessor,
		IPluginConfig config
	) {
		tooltip.append(Component.literal("\n mymod.fuel"));
		tooltip.append(Component.translatable("applesaplings:mymod.fuel"));
	}

	@Override
	public ResourceLocation getUid() {
		return ExamplePlugin.FURNACE_FUEL;
	}

//	@Override
//	public ResourceLocation getUid() {
//		return null;
//	}
}