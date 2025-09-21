package net.hexagonelle.applesaplings.datagen.loot;

import net.hexagonelle.applesaplings.blocks.custom.FloweringLeavesBlock;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static net.hexagonelle.applesaplings.blocks.ModBlocks.*;
import static net.hexagonelle.applesaplings.items.ItemRegistry.*;
import static net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition.hasBlockStateProperties;

public class ModBlockLootTables extends BlockLootSubProvider {
	public ModBlockLootTables() {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags());
	}

	protected LootTable.Builder createFloweringLeavesDrops(
			String saplingPrefix,
			Item fruitItem,
			float... chances) {

		Block leavesBlock = BLOCK_MAP.get(saplingPrefix + "_flowering_leaves").get();
		Block saplingBlock = BLOCK_MAP.get(saplingPrefix + "_sapling").get();

		// convert fruitItem to LootTableItem
		LootPoolSingletonContainer.Builder<?> fruitLoot = LootItem.lootTableItem(fruitItem);
		// convert chances to NumberProvider
		NumberProvider chancesProvider = ConstantValue.exactly(1.0F);
		// create condition that player cannot have shears or silk touch
		LootItemCondition.Builder NO_SHEARS_OR_SILK_TOUCH = (HAS_SHEARS.or(HAS_SILK_TOUCH)).invert();
		// define fully grown state
		StatePropertiesPredicate.Builder fullyGrownState =
				StatePropertiesPredicate.Builder.properties()
						.hasProperty(FloweringLeavesBlock.AGE, FloweringLeavesBlock.MAX_AGE);
		// convert fullyGrownState to a condition
		LootItemBlockStatePropertyCondition.Builder fullyGrown =
				hasBlockStateProperties(leavesBlock).setProperties(fullyGrownState);
		// define lootPool that will drop fruit when leaves are fully grown
		LootPool.Builder lootPool =
				LootPool.lootPool()
						.setRolls(chancesProvider)
						.when(NO_SHEARS_OR_SILK_TOUCH)
						.add(this.applyExplosionCondition(leavesBlock, fruitLoot).when(fullyGrown));

		return this.createLeavesDrops(leavesBlock, saplingBlock, chances).withPool(lootPool);
	}

	protected LootTable.Builder FloweringLeavesLootFactory(
			String saplingPrefix,
			Item fruit
	){
        return createFloweringLeavesDrops(saplingPrefix, fruit, NORMAL_LEAVES_SAPLING_CHANCES);
	}

	private void signAndHangingSignLoot(String woodType){

		String signString = woodType + "_sign";
		String hangingSignString = woodType + "_hanging_sign";

		this.dropSelf(BLOCK_MAP.get(signString).get());
		this.dropSelf(BLOCK_MAP.get(hangingSignString).get());

		this.add(
			BLOCK_MAP.get(woodType + "_wall_sign").get(),
			block -> createSingleItemTable(ITEM_MAP.get(signString).get())
		);
		this.add(
			BLOCK_MAP.get(woodType + "_wall_hanging_sign").get(),
			block -> createSingleItemTable(ITEM_MAP.get(hangingSignString).get())
		);
	}

	private void woodTypeLoot(
		String saplingPrefix,
		String woodType
	){

		Block saplingBlock = BLOCK_MAP.get(saplingPrefix + "_sapling").get();

		this.dropSelf(saplingBlock);
		this.add(
			BLOCK_MAP.get(saplingPrefix + "_leaves").get(),
			block -> createLeavesDrops(block,saplingBlock,NORMAL_LEAVES_SAPLING_CHANCES)
		);
		this.dropSelf(BLOCK_MAP.get(woodType + "_log").get());
		this.dropSelf(BLOCK_MAP.get(woodType + "_wood").get());
		this.dropSelf(BLOCK_MAP.get("stripped_" + woodType + "_log").get());
		this.dropSelf(BLOCK_MAP.get("stripped_" + woodType + "_log").get());
		this.dropSelf(BLOCK_MAP.get(woodType + "_planks").get());

		signAndHangingSignLoot(woodType);

		this.dropSelf(BLOCK_MAP.get(woodType + "_stairs").get());
		this.dropSelf(BLOCK_MAP.get(woodType + "_slab").get());
		this.dropSelf(BLOCK_MAP.get(woodType + "_button").get());
		this.dropSelf(BLOCK_MAP.get(woodType + "_pressure_plate").get());
		this.dropSelf(BLOCK_MAP.get(woodType + "_fence").get());
		this.dropSelf(BLOCK_MAP.get(woodType + "_fence_gate").get());
		this.dropSelf(BLOCK_MAP.get(woodType + "_door").get());
		this.dropSelf(BLOCK_MAP.get(woodType + "_trapdoor").get());

	}

	@Override
	protected void generate() {
		woodTypeLoot("apple","applewood");
		this.add(
			BLOCK_MAP.get("flowering_apple_leaves").get(),
			(block) -> FloweringLeavesLootFactory("apple", Items.APPLE)
		);
	}

	@Override
	protected @NotNull Iterable<Block> getKnownBlocks(){
		return BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
	}
}
