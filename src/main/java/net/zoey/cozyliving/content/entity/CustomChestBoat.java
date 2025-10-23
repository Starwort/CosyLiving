package net.zoey.cozyliving.content.entity;

import net.minecraft.nbt.*;
import net.minecraft.network.syncher.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.vehicle.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.zoey.cozyliving.content.*;
import org.jetbrains.annotations.*;

import java.util.function.*;

public class CustomChestBoat extends ChestBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(Boat.class,
        EntityDataSerializers.INT
    );

    public CustomChestBoat(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
    }

    public CustomChestBoat(Level level, double x, double y, double z) {
        this(ModEntities.CUSTOM_CHEST_BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    public @NotNull Item getDropItem() {
        return switch (getModVariant()) {
            case COCONUT ->
                ModItems.COCONUT_CHEST_BOAT.get(); // TODO: Implement and use Coconut Chest Boat
        };
    }

    public void setVariant(CustomBoat.Type variant) {
        this.entityData.set(DATA_ID_TYPE, variant.ordinal());
    }

    public CustomBoat.Type getModVariant() {
        return CustomBoat.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, CustomBoat.Type.COCONUT.ordinal());
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putString("Type", getModVariant().getSerializedName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains("Type", 8)) {
            setVariant(CustomBoat.Type.byName(tag.getString("Type")));
        }
    }
}
