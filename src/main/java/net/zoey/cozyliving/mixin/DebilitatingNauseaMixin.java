package net.zoey.cozyliving.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.*;
import net.minecraft.client.player.*;
import net.minecraft.util.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class DebilitatingNauseaMixin extends AbstractClientPlayer {

    @Shadow
    public float spinningEffectIntensity;
    @Unique
    private float cosyLiving$bonusIntensity = 0;

    public DebilitatingNauseaMixin(ClientLevel level, GameProfile profile) {
        super(level, profile);
    }

    @Inject(method = "handleConfusionTransitionEffect", at = @At("TAIL"))
    protected void injectNauseaFromSilly(CallbackInfo ci) {
        float dNausea;
        var thirdEye = this.getEffect(ModEffects.THIRD_EYE_OPEN.holder());
        if (thirdEye != null && !thirdEye.endsWithin(600)) {
            dNausea = 0.0006666667F;
        } else {
            dNausea = -0.005F;
        }

        cosyLiving$bonusIntensity = Mth.clamp(
            cosyLiving$bonusIntensity + dNausea,
            0.0F,
            (float) Config.debilitatingNauseaFactor()
        );
        spinningEffectIntensity = Mth.clamp(
            cosyLiving$bonusIntensity + spinningEffectIntensity,
            0.0F,
            (float) Config.debilitatingNauseaFactor()
        );
    }

    //TODO: STILL RELIES ON NAUSEA (CONFUSION) EFFECT TO SLOW DOWN THE EFFECT SPEED, FIND WORK AROUND

}