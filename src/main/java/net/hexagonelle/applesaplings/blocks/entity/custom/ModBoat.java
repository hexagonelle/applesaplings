package net.hexagonelle.applesaplings.blocks.entity.custom;

import net.hexagonelle.applesaplings.blocks.ModBlocks;
import net.hexagonelle.applesaplings.blocks.entity.ModEntities;
import net.hexagonelle.applesaplings.items.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.IntFunction;

public class ModBoat extends Boat {
	private static final EntityDataAccessor<Integer> DATA_ID_TYPE =
		SynchedEntityData.defineId(ModBoat.class, EntityDataSerializers.INT);

	public ModBoat(EntityType<? extends Boat> entityType, Level level) {
		super(entityType, level);
	}

	public ModBoat(Level level, double pX, double pY, double pZ) {
		this(ModEntities.MOD_BOAT.get(), level);
		this.setPos(pX, pY, pZ);
		this.xo = pX;
		this.yo = pY;
		this.zo = pZ;
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_ID_TYPE, ModBoat.Type.APPLEWOOD.ordinal());
	}

	protected void addAdditionalSaveData(CompoundTag compound) {
		compound.putString("Type", this.getModVariant().getSerializedName());
	}

	protected void readAdditionalSaveData(CompoundTag compound) {
		if (compound.contains("Type", 8)) {
			this.setVariant(Type.byName(compound.getString("Type")));
		}
	}

	public void setVariant(ModBoat.Type variant) {
		this.entityData.set(DATA_ID_TYPE, variant.ordinal());
	}

	public ModBoat.Type getModVariant() {
		return ModBoat.Type.byId(this.entityData.get(DATA_ID_TYPE));
	}

	public @NotNull Item getDropItem() {
		Item item;
		switch (getModVariant()) {
			case APPLEWOOD:
				item = ModItems.APPLEWOOD_BOAT.get();
				break;
			default:
				item = ModItems.APPLEWOOD_BOAT.get();
		}

		return item;
	}

	public enum Type implements StringRepresentable {
		APPLEWOOD(ModBlocks.APPLEWOOD_PLANKS.get(), "applewood");

		private final String name;
		private final Block planks;
		public static final StringRepresentable.EnumCodec<ModBoat.Type> CODEC = StringRepresentable.fromEnum(ModBoat.Type::values);
		private static final IntFunction<ModBoat.Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

		Type(Block planks, String name) {
			this.name = name;
			this.planks = planks;
		}

		public @NotNull String getSerializedName() {
			return this.name;
		}

		public String getName() {
			return this.name;
		}
		public Block getPlanks() {
			return this.planks;
		}
		public String toString() {
			return this.name;
		}

		/**
		 * Get a boat type by its enum ordinal
		 */
		public static ModBoat.Type byId(int id) {
			return BY_ID.apply(id);
		}

		public static ModBoat.Type byName(String typeName) {
			return CODEC.byName(typeName, APPLEWOOD);
		}
	}

}
