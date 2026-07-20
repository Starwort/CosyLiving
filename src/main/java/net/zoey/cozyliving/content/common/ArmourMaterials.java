package net.zoey.cozyliving.content.common;

import net.minecraft.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.sounds.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.*;
import net.zoey.cozyliving.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.function.*;

public enum ArmourMaterials {
    FLOWER_CROWN(
        "flower_crown",
        0,
        new int[] {0, 0, 0, 0, 0},
        99,
        SoundEvents.CHERRY_LEAVES_PLACE,
        0f,
        0f,
        () -> Ingredient.of(ItemTags.SMALL_FLOWERS)
    );

    private final int durabilityMultiplier;
    private final DeferredHolder<ArmorMaterial, ArmorMaterial> myValue;

    ArmourMaterials(
        String name,
        int durabilityMultiplier,
        int[] defenceAmounts,
        int enchantmentValue,
        SoundEvent equipSound,
        float toughness,
        float kbResist,
        Supplier<Ingredient> repairIngredient
    ) {
        this(
            name,
            durabilityMultiplier,
            defenceAmounts,
            enchantmentValue,
            BuiltInRegistries.SOUND_EVENT.wrapAsHolder(equipSound),
            toughness,
            kbResist,
            repairIngredient
        );
    }
    ArmourMaterials(
        String name,
        int durabilityMultiplier,
        int[] defenceAmounts,
        int enchantmentValue,
        Holder<SoundEvent> equipSound,
        float toughness,
        float kbResist,
        Supplier<Ingredient> repairIngredient
    ) {
        this.durabilityMultiplier = durabilityMultiplier;
        myValue = CozyLiving.ARMOUR_MATERIALS.register(
            name,
            () -> new ArmorMaterial(
                Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                    map.put(ArmorItem.Type.HELMET, defenceAmounts[0]);
                    map.put(ArmorItem.Type.CHESTPLATE, defenceAmounts[1]);
                    map.put(ArmorItem.Type.LEGGINGS, defenceAmounts[2]);
                    map.put(ArmorItem.Type.BOOTS, defenceAmounts[3]);
                    // BODY is used for non-player entities like wolves or horses.
                    map.put(ArmorItem.Type.BODY, defenceAmounts[4]);
                }),
                enchantmentValue,
                equipSound,
                repairIngredient,
                List.of(new ArmorMaterial.Layer(
                    CozyLiving.loc(name)
                )),
                toughness,
                kbResist
            )
        );
    }

    public int durabilityFor(ArmorItem.Type type) {
        return type.getDurability(durabilityMultiplier);
    }

    public DeferredHolder<ArmorMaterial, ArmorMaterial> holder() {
        return myValue;
    }

    public ArmorMaterial material() {
        return myValue.get();
    }

    public ResourceLocation id() {
        return myValue.getId();
    }

    public static void register(IEventBus modEventBus) {
        CozyLiving.LOGGER.info(
                "Found {} armour materials",
                CozyLiving.ARMOUR_MATERIALS.getEntries().size()
        );
        CozyLiving.ARMOUR_MATERIALS.register(modEventBus);
    }
}
