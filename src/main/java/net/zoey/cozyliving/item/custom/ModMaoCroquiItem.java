package net.zoey.cozyliving.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

public class ModMaoCroquiItem extends Item {
    String translationKey;
    Text randomPlayerName;
    Boolean nameGenerated = false;


    public ModMaoCroquiItem(Settings settings, String translationID) {
        super(settings);
        translationKey = translationID;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        if (!nameGenerated || randomPlayerName == null){
            randomPlayerName = getRandomPlayerName(world);
        }

        if(randomPlayerName == null){
            tooltip.add(Text.translatable("tooltip.cozyliving." + translationKey + ".1").append("Thomas,"));
        } else {
            tooltip.add(Text.translatable("tooltip.cozyliving." + translationKey + ".1").append(randomPlayerName).append(","));
        }

        tooltip.add(Text.translatable("tooltip.cozyliving." + translationKey + ".2"));
        tooltip.add(Text.empty()); //Empty line
        tooltip.add(Text.translatable("tooltip.cozyliving." + translationKey + ".3"));
        tooltip.add(Text.translatable("tooltip.cozyliving." + translationKey + ".4"));

        super.appendTooltip(stack, world, tooltip, context);
    }

    private Text getRandomPlayerName(@Nullable World world){
        if (world != null){
            Text text;
            Random random = world.getRandom();
            List<? extends PlayerEntity> playerEntityList = world.getPlayers();
            text = (playerEntityList.get(random.nextInt(playerEntityList.size())).getDisplayName());
            nameGenerated = true;
        }

        return randomPlayerName;
    }

}
