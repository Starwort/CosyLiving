package net.zoey.cozyliving.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import net.zoey.cozyliving.effect.ModEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntitySillyEffectMixin extends AbstractClientPlayerEntity {

    @Shadow public float nauseaIntensity;
    @Unique
    private float sillyIntensity = 0;

    public ClientPlayerEntitySillyEffectMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(method = "updateNausea", at = @At("TAIL"))
    protected void injectNauseaFromSilly(CallbackInfo ci) {
        //Idk if fromPortalEffect or info are needed!!
        // But they won't do any harm if they're there!! Probably!!

        float sillyModifier;
        if (this.hasStatusEffect(ModEffects.THIRD_EYE_OPEN) && !this.getStatusEffect(ModEffects.THIRD_EYE_OPEN).isDurationBelow(600)) {
            sillyModifier = 0.0006666667F;
        } else {
            sillyModifier = -0.005F;
        }

        sillyIntensity = MathHelper.clamp(sillyIntensity + sillyModifier, 0.0F, 1.0F);
        nauseaIntensity = MathHelper.clamp(this.sillyIntensity + nauseaIntensity, 0.0F, 2.0F);

        /*if (this.hasStatusEffect(ModEffects.SILLY_EFFECT)){
            nauseaIntensity = 2f;
        }*/

    }

}
