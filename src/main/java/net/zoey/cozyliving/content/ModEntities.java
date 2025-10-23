package net.zoey.cozyliving.content;

import net.minecraft.core.registries.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.entity.*;

public class ModEntities {
    public static void register(IEventBus modEventBus) {
        CozyLiving.LOGGER.info("Found {} entity types", REGISTER.getEntries().size());
        REGISTER.register(modEventBus);
    }

    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
        CozyLiving.MODID
    );

    public static final RegistryObject<EntityType<CustomBoat>> CUSTOM_BOAT = REGISTER.register(
        "custom_boat",
        () -> EntityType.Builder
            .<CustomBoat>of(CustomBoat::new, MobCategory.MISC)
            .sized(1.375f, 0.5625f)
            .build("custom_boat")
    );

    public static final RegistryObject<EntityType<CustomChestBoat>> CUSTOM_CHEST_BOAT = REGISTER.register(
        "custom_chest_boat",
        () -> EntityType.Builder
            .<CustomChestBoat>of(CustomChestBoat::new, MobCategory.MISC)
            .sized(1.375f, 0.5625f)
            .build("custom_chest_boat")
    );
}
