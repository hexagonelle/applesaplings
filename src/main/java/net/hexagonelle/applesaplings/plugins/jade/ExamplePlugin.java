package net.hexagonelle.applesaplings.plugins.jade;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;


@WailaPlugin
public class ExamplePlugin implements IWailaPlugin {

	public static final ResourceLocation FURNACE_FUEL = new ResourceLocation("furnace");

	@Override
	public void register(IWailaCommonRegistration registration) {
		//TODO register data providers
	}

	@Override
	public void registerClient(IWailaClientRegistration registration) {
		registration.registerBlockComponent(ExampleComponentProvider.INSTANCE, AbstractFurnaceBlock.class);
	}
}

