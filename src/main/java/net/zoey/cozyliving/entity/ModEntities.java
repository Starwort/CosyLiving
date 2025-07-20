package net.zoey.cozyliving.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.entity.custom.LadyBeetleEntity;

public class ModEntities {

    public static final EntityType<LadyBeetleEntity> LADYBEETLE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(CozyLiving.MOD_ID, "lady_beetle"),
            FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, LadyBeetleEntity::new)
                    .dimensions(EntityDimensions.fixed(0.625f, 0.2f)).build());
}
