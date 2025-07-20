package net.zoey.cozyliving.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlowInkSacItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.zoey.cozyliving.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GlowberryJamItem extends GlowInkSacItem{

    String translationKey;

    public GlowberryJamItem(Settings settings, String translationID) {
        super(settings);
        translationKey = translationID;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.cozyliving." + translationKey));
        super.appendTooltip(stack, world, tooltip, context);
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        super.finishUsing(stack, world, user);
        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (stack.isEmpty()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        } else {
            if (user instanceof PlayerEntity && !((PlayerEntity)user).getAbilities().creativeMode) {
                ItemStack itemStack = new ItemStack(Items.GLASS_BOTTLE);
                PlayerEntity playerEntity = (PlayerEntity)user;
                if (!playerEntity.getInventory().insertStack(itemStack)) {
                    playerEntity.dropItem(itemStack, false);
                }
            }
            return stack;
        }
    }

    public int getMaxUseTime(ItemStack stack) {
        return 60;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    public SoundEvent getDrinkSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }

    public SoundEvent getEatSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    public boolean useOnSign(World world, SignBlockEntity signBlockEntity, boolean front, PlayerEntity player) {
        if (signBlockEntity.changeText((text) -> {
            return text.withGlowing(true);
        }, front)) {
            world.playSound((PlayerEntity)null, signBlockEntity.getPos(), ModSounds.JAM_SMEAR, SoundCategory.BLOCKS, 1.0F, 1.0F);
            player.giveItemStack(new ItemStack(Items.GLASS_BOTTLE));

            return true;
        } else {
            return false;
        }
    }

}
