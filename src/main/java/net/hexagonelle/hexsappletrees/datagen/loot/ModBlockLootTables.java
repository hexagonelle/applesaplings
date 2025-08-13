package net.hexagonelle.hexsappletrees.datagen.loot;

import net.hexagonelle.hexsappletrees.blocks.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
	public ModBlockLootTables() {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags());
	}

	@Override
	protected void generate() {
		this.dropSelf(ModBlocks.APPLE_SAPLING.get());
		this.add(
				ModBlocks.APPLE_LEAVES.get(),
				block -> createLeavesDrops(
						block,
						ModBlocks.APPLE_SAPLING.get(),
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
