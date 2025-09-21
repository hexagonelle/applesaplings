package net.hexagonelle.applesaplings.datagen;

import net.hexagonelle.applesaplings.Constants;
import net.hexagonelle.applesaplings.datagen.loot.ModLootTableProvider;
import net.hexagonelle.applesaplings.datagen.models.ModBlockStateProvider;
import net.hexagonelle.applesaplings.datagen.models.ModItemModelProvider;
import net.hexagonelle.applesaplings.datagen.recipes.ModRecipeProvider;
import net.hexagonelle.applesaplings.datagen.tags.ModBlockTagGenerator;
import net.hexagonelle.applesaplings.datagen.tags.ModItemTagGenerator;
import net.hexagonelle.applesaplings.datagen.worldgen.ModWorldGenProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Constants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class DataGeneration {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event){
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		ModBlockTagGenerator blockTagGenerator =
			generator.addProvider(
				event.includeServer(),
				new ModBlockTagGenerator(
					packOutput,
					lookupProvider,
					existingFileHelper
				)
			);

			generator.addProvider(
				event.includeServer(),
				new ModItemTagGenerator(
					packOutput,
					lookupProvider,
					blockTagGenerator.contentsGetter(),
					existingFileHelper
				)
		);

		generator.addProvider(
				event.includeServer(),
				new ModWorldGenProvider(packOutput,lookupProvider)
		);

		generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput));
		generator.addProvider(event.includeServer(), ModLootTableProvider.create(packOutput));
		generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput,existingFileHelper));
		generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput,existingFileHelper));
	}

}
