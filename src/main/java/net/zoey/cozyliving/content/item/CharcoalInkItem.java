package net.zoey.cozyliving.content.item;

import net.minecraft.sounds.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.entity.*;
import org.jetbrains.annotations.*;

public class CharcoalInkItem extends TooltipItem implements SignApplicator {
    public CharcoalInkItem() {
        super(new Properties().craftRemainder(Items.GLASS_BOTTLE));
    }

    @Override
    public boolean tryApplyToSign(
        @NotNull Level level, @NotNull SignBlockEntity sign, boolean front, // ?
        @NotNull Player player
    ) {
        if (sign.updateText(signText -> signText.setHasGlowingText(false), front)) {
            level.playSound(
                null, sign.getBlockPos(),
                // TODO: Custom sound event
                SoundEvents.INK_SAC_USE, SoundSource.BLOCKS, 1f, 1f
            );

            // try to give the player a bottle
            var mainStack = player.getMainHandItem();
            var offStack = player.getOffhandItem();
            var hand = InteractionHand.MAIN_HAND;
            ItemStack usedStack;
            if (mainStack.getItem().getClass() == this.getClass()) {
                usedStack = mainStack;
            } else if (offStack.getItem().getClass() == this.getClass()) {
                usedStack = offStack;
                hand = InteractionHand.OFF_HAND;
            } else {
                // we're somehow using an item that's neither the main-hand or off-hand item,
                // just use vanilla consume behaviour
                return true;
            }
            player.setItemInHand(
                hand,
                CLItemUtils.createFilledResultWithoutConsuming(
                    usedStack,
                    player,
                    new ItemStack(Items.GLASS_BOTTLE),
                    false
                )
            );
            return true;
        }
        return false;
    }
}
