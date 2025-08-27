package net.hexagonelle.applesaplings.entities.custom;


import net.hexagonelle.applesaplings.entities.ModEntities;
import net.hexagonelle.applesaplings.items.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class ModChestBoat extends ChestBoat {
	private static final EntityDataAccessor<Integer> DATA_ID_TYPE =
		SynchedEntityData.defineId(ModChestBoat.class, EntityDataSerializers.INT);

	public ModChestBoat(EntityType<? extends ChestBoat> entityType, Level level) {
		super(entityType, level);
	}

	public ModChestBoat(Level level, double pX, double pY, double pZ) {
		this(ModEntities.MOD_CHEST_BOAT.get(), level);
		this.setPos(pX, pY, pZ);
		this.xo = pX;
		this.yo = pY;
		this.zo = pZ;
	}

	@Override
	public Item getDropItem() {
		Item item;
		switch (getModVariant()) {
			case APPLEWOOD:
				item = ModItems.APPLEWOOD_CHEST_BOAT.get();
				break;
			default:
				item = ModItems.APPLEWOOD_CHEST_BOAT.get();
		}
		return item;
	}

	public void setVariant(ModBoat.Type variant) {
		this.entityData.set(DATA_ID_TYPE, variant.ordinal());
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
			this.setVariant(ModBoat.Type.byName(compound.getString("Type")));
		}
	}

	public ModBoat.Type getModVariant() {
		return ModBoat.Type.byId(this.entityData.get(DATA_ID_TYPE));
	}

}
