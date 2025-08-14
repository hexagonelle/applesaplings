package net.hexagonelle.applesaplings.datagen.loot;

import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.hexagonelle.applesaplings.custom.FruitingLeavesBlock;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

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
		return this
			.createLeavesDrops(
				leavesBlock,
				saplingBlock,
				chances
			).withPool(
				LootPool
					.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.when((HAS_SHEARS.or(HAS_SILK_TOUCH)).invert())
					.add(
						this
							.applyExplosionCondition(
									leavesBlock,
									LootItem.lootTableItem(fruitItem)
							).when(
									hasBlockStateProperties(ModBlocks.APPLE_LEAVES.get())
											.setProperties(StatePropertiesPredicate.Builder.properties()
													.hasProperty(FruitingLeavesBlock.AGE,4)
											)
							)
					)
			);
	}

	@Override
	protected void generate() {
		this.dropSelf(ModBlocks.APPLE_SAPLING.get());
		this.add(
				ModBlocks.APPLE_LEAVES.get(),
				block -> createFruitingLeavesDrops(
						block,
						ModBlocks.APPLE_SAPLING.get(),
						Items.APPLE,
						NORMAL_LEAVES_SAPLING_CHANCES
				)
		);
	}

//	protected LootTable.Builder createLeavesDrops(Block block, Item item){
//		return createSilkTouchDispatchTable(
//				block,
//				this.applyExplosionDecay(
//						block,
//						LootItem.lootTableItem(item)
//								.apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F,5.0F)))
//								.apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
//				)
//		);
//	}

	@Override
	protected Iterable<Block> getKnownBlocks(){
		return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
	}
}
