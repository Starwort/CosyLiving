package net.zoey.cozyliving.block.custom;


import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.zoey.cozyliving.item.ModItems;

public class CottonCropBlock extends CropBlock {
    public CottonCropBlock(Settings settings) {
        super(settings);
    }

    protected ItemConvertible getSeedsItem() {
        return ModItems.COTTON_BOLL;
    }
}