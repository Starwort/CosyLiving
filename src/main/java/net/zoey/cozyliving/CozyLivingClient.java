package net.zoey.cozyliving;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import net.zoey.cozyliving.block.ModBlocks;
import net.zoey.cozyliving.entity.ModBoats;
import net.zoey.cozyliving.entity.ModEntities;
import net.zoey.cozyliving.entity.client.LadyBeetleModel;
import net.zoey.cozyliving.entity.client.LadyBeetleRenderer;
import net.zoey.cozyliving.entity.client.ModModelLayers;
import net.zoey.cozyliving.util.tools.RenderLayerTool;

public class CozyLivingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //Allow blocks to have transparency
        RenderLayerTool.cutOut();

        //Colour Registry Stuff
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return GrassColors.getColor(0.5, 1.0);
            }
            return BiomeColors.getGrassColor(world, pos);
        }, ModBlocks.RASPBERRY_BUSH);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return GrassColors.getColor(0.5, 1.0);
            }
            return BiomeColors.getGrassColor(world, pos);
        }, ModBlocks.COTTON_CROP);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return GrassColors.getColor(0.5, 1.0);
            }
            return BiomeColors.getGrassColor(world, pos);
        }, ModBlocks.COTTON_SHRUB);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return GrassColors.getColor(0.5, 1.0);
            }
            return BiomeColors.getGrassColor(world, pos);
        }, ModBlocks.POTTED_COTTON);

        TerraformBoatClientHelper.registerModelLayers(ModBoats.COCONUT_BOAT_ID, false);
        TerraformBoatClientHelper.registerModelLayers(ModBoats.COCONUT_CHEST_BOAT_ID, false);

        //Entity stuff
        EntityRendererRegistry.register(ModEntities.LADYBEETLE, LadyBeetleRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.LADYBEETLE, LadyBeetleModel::getTexturedModelData);

    }
}
