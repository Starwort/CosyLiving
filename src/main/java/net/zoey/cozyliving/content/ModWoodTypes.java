package net.zoey.cozyliving.content;

import net.minecraft.sounds.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.zoey.cozyliving.*;

public class ModWoodTypes {
    public static final BlockSetType COCONUT_BLOCK_SET_TYPE = new BlockSetType(
        "coconut",
        true,
        SoundType.WOOD,
        SoundEvents.WOODEN_DOOR_CLOSE,
        SoundEvents.WOODEN_DOOR_OPEN,
        SoundEvents.WOODEN_TRAPDOOR_CLOSE,
        SoundEvents.WOODEN_TRAPDOOR_OPEN,
        SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF,
        SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON,
        SoundEvents.WOODEN_BUTTON_CLICK_OFF,
        SoundEvents.WOODEN_BUTTON_CLICK_ON
    );

    public static final WoodType COCONUT = WoodType.register(new WoodType(
        CozyLiving.MODID + ":coconut",
        COCONUT_BLOCK_SET_TYPE
    ));
}
