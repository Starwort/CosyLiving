package net.zoey.cozyliving.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.zoey.cozyliving.CozyLiving;

public class ModModelLayers {
    public static final EntityModelLayer LADYBEETLE =
            new EntityModelLayer(new Identifier(CozyLiving.MOD_ID, "lady_beetle"), "main");
}
