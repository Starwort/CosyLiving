package net.zoey.cozyliving.content;

import net.minecraft.core.registries.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.resources.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.phys.*;
import net.zoey.cozyliving.*;

import javax.annotation.*;

public enum ModDamageTypes {
    COCONUT_BONK("coconut_bonk");

    private final ResourceKey<DamageType> myValue;

    ModDamageTypes(String id) {
        myValue = ResourceKey.create(Registries.DAMAGE_TYPE, CozyLiving.loc(id));
    }

    public final ResourceKey<DamageType> asKey() {
        return myValue;
    }

    public final ResourceLocation id() {
        return myValue.location();
    }

    public DamageSource asSource(
        Level level,
        // The direct entity. For example, if a skeleton shot you, the skeleton would be the causing entity
        // (= the parameter above), and the arrow would be the direct entity (= this parameter). Similar to
        // the causing entity, this isn't always applicable and therefore nullable. Optional, defaults to null.
        @Nullable Entity directEntity,
        // The entity causing the damage. This isn't always applicable (e.g. when falling out of the world)
        // and may therefore be null. Optional, defaults to null.
        @Nullable Entity causingEntity,
        // The damage source position. This is rarely used, one example would be intentional game design
        // (= nether beds exploding). Nullable and optional, defaulting to null.
        @Nullable Vec3 damageSourcePosition
    ) {
        var reg = level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE);
        return new DamageSource(reg.getHolderOrThrow(myValue), directEntity, causingEntity, damageSourcePosition);
    }

    public DamageSource asSource(
        Level level,
        // The direct entity. For example, if a skeleton shot you, the skeleton would be the causing entity
        // (= the parameter above), and the arrow would be the direct entity (= this parameter). Similar to
        // the causing entity, this isn't always applicable and therefore nullable. Optional, defaults to null.
        @Nullable Entity directEntity,
        // The entity causing the damage. This isn't always applicable (e.g. when falling out of the world)
        // and may therefore be null. Optional, defaults to null.
        @Nullable Entity causingEntity
    ) {
        return asSource(level, directEntity, causingEntity, null);
    }

    public DamageSource asSource(
        Level level,
        // The direct entity. For example, if a skeleton shot you, the skeleton would be the causing entity
        // (= the parameter above), and the arrow would be the direct entity (= this parameter). Similar to
        // the causing entity, this isn't always applicable and therefore nullable. Optional, defaults to null.
        @Nullable Entity directEntity
    ) {
        return asSource(level, directEntity, null, null);
    }

    public DamageSource asSource(Level level) {
        return asSource(level, null, null, null);
    }

    public static void bootstrap(BootstrapContext<DamageType> context) {
        context.register(
            COCONUT_BONK.asKey(),
            new DamageType(
                COCONUT_BONK.id().getPath(),
                DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
                0.1f,
                DamageEffects.HURT,
                DeathMessageType.DEFAULT
            )
        );
        // context.register(
        //     HOTTER_CHOCOLATE.asKey(),
        //     new DamageType(
        //         HOTTER_CHOCOLATE.id().getPath(),
        //         DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
        //         0.1f,
        //         DamageEffects.HURT,
        //         DeathMessageType.DEFAULT
        //     )
        // );
    }
}
