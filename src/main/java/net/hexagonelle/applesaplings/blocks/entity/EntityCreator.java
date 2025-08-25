package net.hexagonelle.applesaplings.blocks.entity;

import net.hexagonelle.applesaplings.blocks.entity.custom.ModBoat;
import net.hexagonelle.applesaplings.blocks.entity.custom.ModChestBoat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class EntityCreator {

	public static EntityType<ModBoat> createBoat(){
		return EntityType.Builder.<ModBoat>of(
				ModBoat::new,
				MobCategory.MISC
			).sized(1.375f, 0.5625f)
			.build("mod_boat");
	}

	public static EntityType<ModChestBoat> createChestBoat(){
		return EntityType.Builder.<ModChestBoat>of(
				ModChestBoat::new,
				MobCategory.MISC
			).sized(1.375f, 0.5625f)
			.build("mod_chest_boat");
	}

}