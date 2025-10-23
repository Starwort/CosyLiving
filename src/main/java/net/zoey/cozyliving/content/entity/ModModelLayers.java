package net.zoey.cozyliving.content.entity;

import net.minecraft.client.model.geom.*;
import net.minecraft.resources.*;
import net.zoey.cozyliving.*;

public class ModModelLayers {
    public static final ModelLayerLocation COCONUT_BOAT_LAYER = new ModelLayerLocation(
        ResourceLocation.fromNamespaceAndPath(CozyLiving.MODID, "boat/coconut"),
        "main"
    );

    public static final ModelLayerLocation COCONUT_CHEST_BOAT_LAYER = new ModelLayerLocation(
        ResourceLocation.fromNamespaceAndPath(CozyLiving.MODID, "chest_boat/coconut"),
        "main"
    );
}
