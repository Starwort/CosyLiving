package net.zoey.cozyliving.content;

import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.level.*;
import net.zoey.cozyliving.*;

public enum ModDamageTypes {
    COCONUT_BONK("coconut_bonk"),

    // TODO: not used?
    HOTTER_CHOCOLATE("hotter_chocolate"),
    ;

    private final ResourceKey<DamageType> myValue;

    ModDamageTypes(String id) {
        myValue = ResourceKey.create(Registries.DAMAGE_TYPE, CozyLiving.loc(id));
    }

    public final ResourceKey<DamageType> asKey() {
        return myValue;
    }

    public DamageSource asSource(Level level) {
        var reg = level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE);
        return new DamageSource(reg.getHolderOrThrow(myValue));
    }
}
