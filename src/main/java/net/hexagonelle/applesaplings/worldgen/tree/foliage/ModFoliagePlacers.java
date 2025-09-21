package net.hexagonelle.applesaplings.worldgen.tree.foliage;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

//// CURRENTLY UNUSED ////

public class ModFoliagePlacers {

	// HELPER METHODS //

	// Create a Deferred Register to hold FoliagePlacerTypes which will all be registered under the "applesaplings" namespace
	public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
		DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, Constants.MODID);

	// A method that will register the DeferredRegister<FoliagePlacerType<?>> to the mod event bus
	public static void register(IEventBus eventBus) {
		FOLIAGE_PLACERS.register(eventBus);
	}

	// METHODS FOR REGISTERING FOLIAGE PLACER TYPES //

	// A method that creates the corresponding BlockItem and registers both Block and BlockItem under the blockID.
	public static final RegistryObject<FoliagePlacerType<AppleFoliagePlacer>> APPLE_FOLIAGE_PLACER =
		FOLIAGE_PLACERS.register("apple_foliage_placer", () -> new FoliagePlacerType<>(AppleFoliagePlacer.CODEC));
}
