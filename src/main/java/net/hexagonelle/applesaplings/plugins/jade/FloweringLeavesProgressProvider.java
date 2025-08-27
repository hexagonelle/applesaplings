package net.hexagonelle.applesaplings.plugins.jade;

import net.hexagonelle.applesaplings.blocks.custom.FloweringLeavesBlock;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IJadeProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.theme.IThemeHelper;

public enum FloweringLeavesProgressProvider implements IBlockComponentProvider {
	INSTANCE;

	@Override
	public void appendTooltip(
		ITooltip tooltip,
		BlockAccessor accessor,
		IPluginConfig config
	) {
		BlockState state = accessor.getBlockState();
		Block block = state.getBlock();

		if (block instanceof FloweringLeavesBlock floweringLeaves) {
			addMaturityTooltip(tooltip, floweringLeaves.getAge(state) / (float) floweringLeaves.getMaxAge());
		}
	}

	private static void addMaturityTooltip(ITooltip tooltip, float growthValue) {
		MutableComponent component;
		if (growthValue < 1) {
			component = IThemeHelper.get().info(String.format("%.0f%%", growthValue * 100));
		} else {
			component = IThemeHelper.get().success(Component.translatable("tooltip.jade.crop_mature"));
		}
		tooltip.add(Component.translatable("tooltip.jade.crop_growth", component));
	}

	@Override
	public ResourceLocation getUid() {
		return AppleSaplingsJadeIds.FLOWERING_LEAVES_PROGRESS;
	}

//	@Override
//	public ResourceLocation getUid() {
//		return null;
//	}
}