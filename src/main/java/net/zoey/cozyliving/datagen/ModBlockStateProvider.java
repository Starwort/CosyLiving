package net.zoey.cozyliving.datagen;

import net.minecraft.data.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.*;
import net.minecraftforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CozyLiving.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        signBlock(
            (StandingSignBlock) ModBlocks.COCONUT_SIGN.block(),
            (WallSignBlock) ModBlocks.COCONUT_WALL_SIGN.block(),
            blockTexture(ModBlocks.COCONUT_PLANKS.block())
        );
        hangingSignBlock(
            ModBlocks.COCONUT_HANGING_SIGN.block(),
            ModBlocks.COCONUT_WALL_HANGING_SIGN.block(),
            blockTexture(ModBlocks.COCONUT_PLANKS.block())
        );

        logBlock((RotatedPillarBlock) ModBlocks.COCONUT_LOG.block());
        axisBlock(
            (RotatedPillarBlock) ModBlocks.COCONUT_WOOD.block(),
            blockTexture(ModBlocks.COCONUT_LOG.block()),
            blockTexture(ModBlocks.COCONUT_LOG.block())
        );
        axisBlock(
            (RotatedPillarBlock) ModBlocks.STRIPPED_COCONUT_LOG.block(),
            blockTexture(ModBlocks.STRIPPED_COCONUT_LOG.block()),
            ResourceLocation.fromNamespaceAndPath(
                CozyLiving.MODID,
                "block/stripped_coconut_log_top"
            )
        );
        axisBlock(
            (RotatedPillarBlock) ModBlocks.STRIPPED_COCONUT_WOOD.block(),
            blockTexture(ModBlocks.STRIPPED_COCONUT_LOG.block()),
            blockTexture(ModBlocks.STRIPPED_COCONUT_LOG.block())
        );

        blockItem(ModBlocks.COCONUT_LOG);
        blockItem(ModBlocks.COCONUT_WOOD);
        blockItem(ModBlocks.STRIPPED_COCONUT_LOG);
        blockItem(ModBlocks.STRIPPED_COCONUT_WOOD);

        blockWithItem(ModBlocks.COCONUT_PLANKS);

        leavesBlock(ModBlocks.COCONUT_LEAVES.registryObject());
    }

    public void hangingSignBlock(
        Block signBlock,
        Block wallSignBlock,
        ResourceLocation texture
    ) {
        var sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private void leavesBlock(RegistryObject<Block> block) {
        simpleBlockWithItem(
            block.get(), models().singleTexture(
                name(block.get()),
                ResourceLocation.fromNamespaceAndPath("minecraft", "block/leaves"),
                "all",
                blockTexture(block.get())
            ).renderType("cutout")
        );
    }

    private void blockItem(ModBlocks block) {
        blockItem(block.registryObject());
    }

    private void blockItem(RegistryObject<Block> block) {
        simpleBlockItem(
            block.get(),
            new ModelFile.UncheckedModelFile(
                CozyLiving.MODID + ":block/" + name(block.get()))
        );
    }

    private void blockWithItem(ModBlocks block) {
        blockWithItem(block.registryObject());
    }

    private void blockWithItem(RegistryObject<Block> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }
}
