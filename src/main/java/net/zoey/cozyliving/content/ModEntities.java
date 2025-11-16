package net.zoey.cozyliving.content;

import net.minecraft.world.entity.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.entity.*;

import java.util.function.*;

public enum ModEntities {
    CUSTOM_BOAT(
        "custom_boat", () -> EntityType.Builder
        .<CustomBoat>of(
            net.zoey.cozyliving.content.entity.CustomBoat::new,
            MobCategory.MISC
        )
        .sized(1.375f, 0.5625f)
        .build("custom_boat")
    ),

    CUSTOM_CHEST_BOAT(
        "custom_chest_boat",
        () -> EntityType.Builder
            .<CustomChestBoat>of(CustomChestBoat::new, MobCategory.MISC)
            .sized(1.375f, 0.5625f)
            .build("custom_chest_boat")
    ),
    ;

    private final RegistryObject<EntityType<? extends Entity>> myValue;

    ModEntities(String name, Supplier<EntityType<? extends Entity>> supplier) {
        myValue = CozyLiving.ENTITIES.register(name, supplier);
    }

    public RegistryObject<EntityType<? extends Entity>> registryObject() {
        return myValue;
    }

    // what maniac decided *this* would be the generic syntax
    public <T extends Entity> EntityType<T> entity() {
        // I'm not sure that this even does anything at runtime, and even if
        // it does, if you call this wrong you deserve to cause a crash
        //noinspection unchecked
        return (EntityType<T>) myValue.get();
    }

    public static void register(IEventBus modEventBus) {
        CozyLiving.LOGGER.info(
            "Found {} entity types",
            CozyLiving.ENTITIES.getEntries().size()
        );
        CozyLiving.ENTITIES.register(modEventBus);
    }
}
