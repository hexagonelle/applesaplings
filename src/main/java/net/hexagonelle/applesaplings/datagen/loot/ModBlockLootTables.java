package net.hexagonelle.applesaplings.datagen.loot;

import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.hexagonelle.applesaplings.blocks.FruitingLeavesBlock;
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

import static net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition.hasBlockStateProperties;

public class ModBlockLootTables extends BlockLootSubProvider {
	public ModBlockLootTables() {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags());
	}

	protected LootTable.Builder createFruitingLeavesDrops(
			Block leavesBlock,
			Block saplingBlock,
			Item fruitItem,
			float... chances) {

		// convert fruitItem to LootTableItem
		LootPoolSingletonContainer.Builder<?> fruitLoot = LootItem.lootTableItem(fruitItem);
		// convert chances to NumberProvider
		NumberProvider chancesProvider = ConstantValue.exactly(1.0F);
		// create condition that player cannot have shears or silk touch
		LootItemCondition.Builder NO_SHEARS_OR_SILK_TOUCH = (HAS_SHEARS.or(HAS_SILK_TOUCH)).invert();
		// define fully grown state
		StatePropertiesPredicate.Builder fullyGrownState =
				StatePropertiesPredicate.Builder.properties()
						.hasProperty(FruitingLeavesBlock.AGE,FruitingLeavesBlock.MAX_AGE);
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

	protected LootTable.Builder fruitingLeavesLootFactory(
			Block block,
			RegistryObject<Block> sapling,
			Item fruit
	){
        return createFruitingLeavesDrops(block, sapling.get(), fruit, NORMAL_LEAVES_SAPLING_CHANCES);
	}

	@Override
	protected void generate() {
		this.dropSelf(ModBlocks.APPLE_SAPLING.get());
		this.dropSelf(ModBlocks.APPLEWOOD_LOG.get());
		this.add(
				ModBlocks.APPLE_LEAVES.get(),
				block -> fruitingLeavesLootFactory(
						block,
						ModBlocks.APPLE_SAPLING,
						Items.APPLE
				)
		);
	}

	@Override
	protected @NotNull Iterable<Block> getKnownBlocks(){
		return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
	}
}
