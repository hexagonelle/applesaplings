package net.hexagonelle.applesaplings.entities.client;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.Constants;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {

	private static ResourceLocation modLoc(String path){
		return new ResourceLocation(Constants.MODID,path);
	}

	private static ModelLayerLocation boatModelLayer(String woodType){
		return new ModelLayerLocation(modLoc("boat/" + woodType), "main");
	}

	private static ModelLayerLocation chestBoatModelLayer(String woodType){
		return new ModelLayerLocation(modLoc("chest_boat/" + woodType), "main");
	}

	public static final ModelLayerLocation APPLEWOOD_BOAT_LAYER =
		boatModelLayer("applewood");
	public static final ModelLayerLocation APPLEWOOD_CHEST_BOAT_LAYER =
		chestBoatModelLayer("applewood");

}