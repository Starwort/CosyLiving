package net.zoey.cozyliving.content.entity.client;

import com.google.common.collect.*;
import com.mojang.datafixers.util.*;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.vehicle.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.entity.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.stream.*;

public class CustomBoatRenderer extends BoatRenderer {
    private final Map<CustomBoat.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public CustomBoatRenderer(
        EntityRendererProvider.Context context,
        boolean isChestBoat
    ) {
        super(context, isChestBoat);
        this.boatResources = Stream
            .of(CustomBoat.Type.values())
            .collect(ImmutableMap.toImmutableMap(
                type -> type, type -> Pair.of(
                    ResourceLocation.fromNamespaceAndPath(
                        CozyLiving.MODID,
                        getTextureLocation(type, isChestBoat)
                    ),
                    createBoatModel(context, type, isChestBoat)
                )
            ));
    }

    private static String getTextureLocation(
        CustomBoat.Type type,
        boolean isChestBoat
    ) {
        return isChestBoat
            ? "textures/entity/chest_boat/" + type.getName() + ".png"
            : "textures/entity/boat/" + type.getName() + ".png";
    }

    private ListModel<Boat> createBoatModel(
        EntityRendererProvider.Context context,
        CustomBoat.Type type,
        boolean isChestBoat
    ) {
        ModelLayerLocation modellayerlocation = isChestBoat
            ? CustomBoatRenderer.createChestBoatModelName(type)
            : CustomBoatRenderer.createBoatModelName(type);
        ModelPart modelpart = context.bakeLayer(modellayerlocation);
        return (ListModel<Boat>) (
            isChestBoat ? new ChestBoatModel(modelpart) : new BoatModel(modelpart)
        );
    }

    public static ModelLayerLocation createBoatModelName(CustomBoat.Type type) {
        return createLocation("boat/" + type.getName(), "main");
    }

    public static ModelLayerLocation createChestBoatModelName(CustomBoat.Type type) {
        return createLocation("chest_boat/" + type.getName(), "main");
    }

    private static ModelLayerLocation createLocation(String path, String model) {
        return new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(CozyLiving.MODID, path),
            // force a line break so my formatter behaves
            model
        );
    }

    @Override
    public @NotNull Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(@NotNull Boat boat) {
        if (boat instanceof CustomBoat modBoat) {
            return this.boatResources.get(modBoat.getModVariant());
        } else if (boat instanceof CustomChestBoat chestBoat) {
            return this.boatResources.get(chestBoat.getModVariant());
        } else {
            return super.getModelWithLocation(boat);
        }
    }
}
