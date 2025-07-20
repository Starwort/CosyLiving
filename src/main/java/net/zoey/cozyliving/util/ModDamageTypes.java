package net.zoey.cozyliving.util;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.zoey.cozyliving.CozyLiving;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> COCONUT_BONK_DAMAGE_TYPE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(CozyLiving.MOD_ID, "coconut_bonk"));
    public static final RegistryKey<DamageType> HOTTER_CHOCOLATE_DAMAGE_TYPE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(CozyLiving.MOD_ID, "hotter_chocolate"));

    public static DamageSource of(World world, RegistryKey<DamageType> damageTypeKey) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(COCONUT_BONK_DAMAGE_TYPE));
    }

    public static void registerDamageTypes() {
        CozyLiving.LOGGER.info("Registering Damage Types for " + CozyLiving.MOD_ID);
    }
}
