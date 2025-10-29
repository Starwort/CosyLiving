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

public class CustomBoat extends Boat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(Boat.class,
        EntityDataSerializers.INT
    );

    public CustomBoat(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
    }

    public CustomBoat(Level level, double x, double y, double z) {
        this(ModEntities.CUSTOM_BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    public @NotNull Item getDropItem() {
        return switch (getModVariant()) {
            case COCONUT ->
                ModItems.COCONUT_BOAT.item(); // TODO: Implement and use Coconut Boat
        };
    }

    public void setVariant(Type variant) {
        this.entityData.set(DATA_ID_TYPE, variant.ordinal());
    }

    public Type getModVariant() {
        return Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, Type.COCONUT.ordinal());
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putString("Type", getModVariant().getSerializedName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains("Type", 8)) {
            setVariant(Type.byName(tag.getString("Type")));
        }
    }

    public enum Type implements StringRepresentable {
        // TODO: Implement and use Coconut Planks
        COCONUT(Blocks.AMETHYST_BLOCK, "coconut");

        private final String name;
        private final Block planks;
        public static final StringRepresentable.EnumCodec<Type> CODEC = StringRepresentable.fromEnum(
            Type::values);
        private static final IntFunction<Type> BY_ID = ByIdMap.continuous(
            Enum::ordinal,
            values(),
            ByIdMap.OutOfBoundsStrategy.ZERO
        );

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

        public static Type byId(int p_38431_) {
            return BY_ID.apply(p_38431_);
        }

        public static Type byName(String p_38433_) {
            return CODEC.byName(p_38433_, COCONUT);
        }
    }
}
