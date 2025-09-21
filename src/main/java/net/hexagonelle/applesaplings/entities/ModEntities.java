package net.hexagonelle.applesaplings.entities;

import net.hexagonelle.applesaplings.Constants;
import net.hexagonelle.applesaplings.entities.custom.ModBoat;
import net.hexagonelle.applesaplings.entities.custom.ModChestBoat;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModEntities {
	// Create a Deferred Register to hold EntityType which will all be registered under the "applesaplings" namespace
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
		DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Constants.MODID);

	// A method that will register the DeferredRegister<BlockEntityType<?>> to the mod event bus
	public static void register(IEventBus eventBus){
		ENTITY_TYPES.register(eventBus);
	}

	// A method that registers an EntityType under the entityID
	private static <T extends EntityType<?>> RegistryObject<T> registerEntity(
		String entityTypeID,
		Supplier<T> entity
	) {
		return ENTITY_TYPES.register(entityTypeID, entity);
	}

	public static final RegistryObject<EntityType<ModBoat>> MOD_BOAT =
		registerEntity("mod_boat", EntityCreator::createBoat);

	public static final RegistryObject<EntityType<ModChestBoat>> MOD_CHEST_BOAT =
		registerEntity("mod_chest_boat", EntityCreator::createChestBoat);

}
