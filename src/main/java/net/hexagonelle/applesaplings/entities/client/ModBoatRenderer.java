package net.hexagonelle.applesaplings.entities.client;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.entities.custom.ModBoat;
import net.hexagonelle.applesaplings.entities.custom.ModChestBoat;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.stream.Stream;

public class ModBoatRenderer extends BoatRenderer {

	public ModBoatRenderer(EntityRendererProvider.Context context, boolean isChestBoat) {
		super(context, isChestBoat);
		this.boatResources =
			Stream
				.of(ModBoat.Type.values())
				.collect(
					ImmutableMap.toImmutableMap(
						type -> type,
						type -> Pair.of(
							new ResourceLocation(
								AppleSaplings.MODID,
								getTextureLocation(type, isChestBoat)
							), this.createBoatModel(context, type, isChestBoat)
						)
					)
				);
	}

	private final Map<ModBoat.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

	private static String getTextureLocation(ModBoat.Type pType, boolean isChestBoat) {
		return isChestBoat ?
			"textures/entity/chest_boat/" + pType.getName() + ".png" :
			"textures/entity/boat/" + pType.getName() + ".png";
	}

	private ListModel<Boat> createBoatModel(
		EntityRendererProvider.Context context,
		ModBoat.Type boatType,
		boolean isChestBoat
	){
		ModelLayerLocation modelLayerLocation =
			isChestBoat ?
				ModBoatRenderer.createChestBoatModelName(boatType) :
				ModBoatRenderer.createBoatModelName(boatType);

		ModelPart modelpart = context.bakeLayer(modelLayerLocation);
		return isChestBoat ? new ChestBoatModel(modelpart) : new BoatModel(modelpart);
	}

	public static ModelLayerLocation createBoatModelName(ModBoat.Type boatType) {
		return createLocation("boat/" + boatType.getName(), "main");
	}

	public static ModelLayerLocation createChestBoatModelName(ModBoat.Type boatType) {
		return createLocation("chest_boat/" + boatType.getName(), "main");
	}

	private static ModelLayerLocation createLocation(String path, String model) {
		return new ModelLayerLocation(new ResourceLocation(AppleSaplings.MODID, path), model);
	}

	public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(@NotNull Boat boat) {
		if(boat instanceof ModBoat modBoat) {
			return this.boatResources.get(modBoat.getModVariant());
		} else if(boat instanceof ModChestBoat modChestBoat) {
			return this.boatResources.get(modChestBoat.getModVariant());
		} else {
			return null;
		}
	}

}
