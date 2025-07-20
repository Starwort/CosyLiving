package net.zoey.cozyliving.mixin.TeaMixins;

import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.zoey.cozyliving.util.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin (BrewingStandBlockEntity.class)
public class BrewingStandBlockEntityMixin {

    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    public void isValid(int slot, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (slot == 3) {
            if (stack.isIn(ModTags.Items.BREWING_STAND_INGREDIENT_USEABLE)) {
                cir.setReturnValue(true);
            }
        }
        else if (slot != 4) {
                if (stack.isIn(ModTags.Items.BREWING_STAND_INPUT_USEABLE)) {
                    cir.setReturnValue(true);
            }
        }
    }

}
