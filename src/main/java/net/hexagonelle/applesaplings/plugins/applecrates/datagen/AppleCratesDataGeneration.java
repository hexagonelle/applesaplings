package net.hexagonelle.applesaplings.plugins.applecrates.datagen;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//@Mod.EventBusSubscriber(modid = AppleSaplings.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AppleCratesDataGeneration {

	@SubscribeEvent
	public static void gen(GatherDataEvent event) {
		jackdaw.applecrates.api.DataGenerators.generatedCrates(AppleSaplings.MODID, event);
	}

}
