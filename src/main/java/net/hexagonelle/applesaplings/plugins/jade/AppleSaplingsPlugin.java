package net.hexagonelle.applesaplings.plugins.jade;

import net.hexagonelle.applesaplings.blocks.custom.FloweringLeavesBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.LeavesBlock;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;


@WailaPlugin
public class AppleSaplingsPlugin implements IWailaPlugin {

	@Override
	public void register(IWailaCommonRegistration registration) {
		//TODO register data providers
	}

	@Override
	public void registerClient(IWailaClientRegistration registration) {
		registration.registerBlockComponent(FloweringLeavesProgressProvider.INSTANCE, FloweringLeavesBlock.class);
	}
}

