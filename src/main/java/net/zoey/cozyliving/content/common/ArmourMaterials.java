package net.zoey.cozyliving.content.common;

import net.minecraft.sounds.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.zoey.cozyliving.*;

import java.util.function.*;

public enum ArmourMaterials implements ArmorMaterial {
    FLOWER_CROWN(
        "flower_crown",
        0,
        new int[] {0, 0, 0, 0},
        99,
        SoundEvents.CHERRY_LEAVES_PLACE,
        0f,
        0f,
        () -> Ingredient.of(ItemTags.SMALL_FLOWERS)
    );

    private final String name;
    private final int durabilityMultiplier;
    private final int[] defenceAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float kbResist;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = {11, 16, 15, 13};

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
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.defenceAmounts = defenceAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.kbResist = kbResist;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return defenceAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }

    @Override
    public String getName() {
        return CozyLiving.MODID + ":" + name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return kbResist;
    }
}
