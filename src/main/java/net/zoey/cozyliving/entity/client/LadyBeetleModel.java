package net.zoey.cozyliving.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.zoey.cozyliving.entity.custom.LadyBeetleEntity;

// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class LadyBeetleModel<T extends LadyBeetleEntity> extends SinglePartEntityModel<T> {
	private final ModelPart lady_beetle;
	private final ModelPart head;
	public LadyBeetleModel(ModelPart root) {
		this.lady_beetle = root.getChild("lady_beetle");
		this.head = this.lady_beetle.getChild("base").getChild("head");
	}

    public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData lady_beetle = modelPartData.addChild("lady_beetle", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData base = lady_beetle.addChild("base", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -3.0F, -5.0F, 8.0F, 3.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData head = base.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -2.0F, -5.0F));

		ModelPartData head_r1 = head.addChild("head_r1", ModelPartBuilder.create().uv(15, 13).cuboid(-2.0F, -0.4F, -2.2F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -2.0F, -0.0873F, 0.0F, 0.0F));

		ModelPartData head_r2 = head.addChild("head_r2", ModelPartBuilder.create().uv(0, 25).cuboid(-2.5F, -3.8F, -7.0F, 5.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 3.0F, 5.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData wings = base.addChild("wings", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -2.75F, -4.0F));

		ModelPartData left_wing = wings.addChild("left_wing", ModelPartBuilder.create().uv(-9, 30).cuboid(0.1F, -0.1F, 0.0F, 4.0F, 0.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_wing = wings.addChild("right_wing", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_wing_r1 = right_wing.addChild("right_wing_r1", ModelPartBuilder.create().uv(-1, 30).cuboid(0.1F, 0.1F, 0.0F, 4.0F, 0.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		ModelPartData elytra = base.addChild("elytra", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -3.0F, -4.0F));

		ModelPartData left_elytron = elytra.addChild("left_elytron", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_elytron_r1 = left_elytron.addChild("left_elytron_r1", ModelPartBuilder.create().uv(0, 13).cuboid(0.15F, 0.8F, -5.2F, 4.0F, 2.0F, 10.0F, new Dilation(0.1F)), ModelTransform.of(0.0F, -1.0F, 4.2F, 0.0F, 0.0F, 0.0175F));

		ModelPartData right_elytron = elytra.addChild("right_elytron", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -0.2F));

		ModelPartData right_elytron_r1 = right_elytron.addChild("right_elytron_r1", ModelPartBuilder.create().uv(0, 13).cuboid(0.15F, 0.8F, -5.2F, 4.0F, 2.0F, 10.0F, new Dilation(0.1F)), ModelTransform.of(0.0F, -1.0F, 4.0F, 0.0F, 3.1416F, -0.0175F));

		ModelPartData legs = base.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData hind_legs_r1 = legs.addChild("hind_legs_r1", ModelPartBuilder.create().uv(16, 36).cuboid(-3.0F, 0.0F, 0.0F, 6.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 3.0F, 0.3491F, 0.0F, 0.0F));

		ModelPartData middle_legs_r1 = legs.addChild("middle_legs_r1", ModelPartBuilder.create().uv(16, 36).cuboid(-3.0F, 0.0F, 0.0F, 6.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		ModelPartData front_legs_r1 = legs.addChild("front_legs_r1", ModelPartBuilder.create().uv(16, 36).cuboid(-3.0F, 0.0F, 0.0F, 6.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -3.0F, 0.3491F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 36, 39);
	}
	@Override
	public void setAngles(LadyBeetleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		lady_beetle.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return lady_beetle;
	}
}