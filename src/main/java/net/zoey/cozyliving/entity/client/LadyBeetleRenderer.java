package net.zoey.cozyliving.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.CozyLivingClient;
import net.zoey.cozyliving.entity.custom.LadyBeetleEntity;

public class LadyBeetleRenderer extends MobEntityRenderer<LadyBeetleEntity, LadyBeetleModel<LadyBeetleEntity>> {
    private static final Identifier TEXTURE = new Identifier(CozyLiving.MOD_ID, "textures/entity/lady_beetle/lady_beetle_5.png");
    public LadyBeetleRenderer(EntityRendererFactory.Context context) {
        super(context, new LadyBeetleModel<>(context.getPart(ModModelLayers.LADYBEETLE)), 0.5f);
    }

    @Override
    public Identifier getTexture(LadyBeetleEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(LadyBeetleEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {

        if(mobEntity.isBaby()){
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
