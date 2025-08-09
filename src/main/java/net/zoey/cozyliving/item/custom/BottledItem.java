package net.zoey.cozyliving.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class BottledItem extends Item {
    String translationKey;
    int drinkingTime;
    SoundEvent drinkingSound;

    public BottledItem(Settings settings, int timeToDrink, String translationID) {
        super(settings);
        translationKey = translationID;
        drinkingTime = timeToDrink;
        drinkingSound = SoundEvents.ENTITY_GENERIC_DRINK;
    }

    public BottledItem(Settings settings, int timeToDrink, String translationID, SoundEvent drinkSound) {
        super(settings);
        translationKey = translationID;
        drinkingTime = timeToDrink;
        drinkingSound = drinkSound;
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
        if (Objects.equals(translationKey, "coconut_milk") || Objects.equals(translationKey, "heavy_cream")){
            if (!world.isClient) {
                user.clearStatusEffects();
            }
        }
        return stack;
    }
    @Override
    public int getMaxUseTime(ItemStack stack) {
        return drinkingTime;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    public SoundEvent getDrinkSound() {
        return drinkingSound;
    }

    public SoundEvent getEatSound() {
        return drinkingSound;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }
}