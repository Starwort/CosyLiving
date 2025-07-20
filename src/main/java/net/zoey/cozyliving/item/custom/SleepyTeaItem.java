package net.zoey.cozyliving.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.zoey.cozyliving.effect.ModEffects;

public class SleepyTeaItem extends BottledItem{
    public SleepyTeaItem(Settings settings, int timeToDrink, String translationID) {
        super(settings, timeToDrink, translationID);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {

        super.finishUsing(stack, world, user);

        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));

            if (user.hasStatusEffect(ModEffects.WELL_RESTED)){
                user.removeStatusEffect(ModEffects.WELL_RESTED);
            } else {
                user.addStatusEffect(new StatusEffectInstance(ModEffects.SLEEPY, 216000, 0, true, false));
            }

        }

        if (stack.isEmpty()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        } else {
            if (user instanceof PlayerEntity playerEntity) {
                if (!playerEntity.isCreative()) {
                    ItemStack itemStack = new ItemStack(Items.GLASS_BOTTLE);
                    if (!playerEntity.getInventory().insertStack(itemStack)) {
                        playerEntity.dropItem(itemStack, false);
                    }
                }
            }

            return stack;
        }
    }
}
