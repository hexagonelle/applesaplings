package net.hexagonelle.applesaplings.worldgen.tree;

import com.mojang.serialization.Codec;
import net.hexagonelle.applesaplings.AppleSaplings;
import net.minecraft.world.level.levelgen.feature.treedecorators.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModTreeDecorators {

	// Create a Deferred Register to hold TreeDecoratorType under the "applesaplings" namespace
	public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES =
		DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, AppleSaplings.MODID);

	// A method that will register the DeferredRegister<TreeDecoratorType<?>> to the mod event bus
	public static void register(IEventBus eventBus){
		TREE_DECORATOR_TYPES.register(eventBus);
	}

	// A method that creates a TreeDecoratorType and registers it under TREE_DECORATOR_TYPES with the given ID
	private static <T extends TreeDecoratorType<?>> RegistryObject<T> registerDecoratorType(
		String key,
		Supplier<T> treeDecoratorType
	){
		return TREE_DECORATOR_TYPES.register(key,treeDecoratorType);
	}

	private static TreeDecoratorType<FloweringLeavesDecorator> createFloweringLeavesDecorator(
		Codec<FloweringLeavesDecorator> codec
	){
		return new TreeDecoratorType<>(codec);
	}

	public static final RegistryObject<TreeDecoratorType<FloweringLeavesDecorator>> FLOWERING_LEAVES
		= registerDecoratorType(
			"flowering_leaves",
			() -> createFloweringLeavesDecorator(FloweringLeavesDecorator.CODEC)
		);

}
