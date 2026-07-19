package net.zoey.cozyliving.util;

import com.mojang.serialization.*;
import net.minecraft.core.registries.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.common.loot.*;
import net.neoforged.neoforge.registries.*;
import net.zoey.cozyliving.*;

import java.util.function.*;

public class ModLootTableModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
        DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, CozyLiving.MODID);

    public static final Supplier<MapCodec<AddItemModifier>> ADD_ITEM =
        LOOT_MODIFIER_SERIALIZERS.register("add_item", () -> AddItemModifier.CODEC);

    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
