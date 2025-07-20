package net.zoey.cozyliving.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.tick.TickPriority;
import net.zoey.cozyliving.block.ModBlocks;

public class CoconutPlantBlock extends PlantBlock implements Fertilizable {
    public static final int MAX_AGE = 2;
    public static final IntProperty AGE;
    private static final VoxelShape SMALL_SHAPE;
    private static final VoxelShape MEDIUM_SHAPE;
    private static final VoxelShape LARGE_SHAPE;

    public CoconutPlantBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(AGE, 0));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int age = state.get(AGE);
        if (random.nextBetween(1,3) == 3) {
            if (age < 3 && random.nextInt(5) == 0 && world.getBaseLightLevel(pos.up(), 0) >= 9) {
                BlockState blockState = (BlockState)state.with(AGE, age + 1);
                world.setBlockState(pos, blockState, 2);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
            } else if (age == 3){
                world.scheduleBlockTick(pos, this, 1, TickPriority.NORMAL);
            }
        }

    }

    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (state.get(AGE) == 2){
            world.scheduleBlockTick(pos, this, 1, TickPriority.NORMAL);
        }
        super.onBlockBreakStart(state, world, pos, player);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockState blockAbove = world.getBlockState(pos.up());
        //If I am no longer below a suitable block, break
        if (!(blockAbove.isIn(BlockTags.LEAVES))) {
            world.breakBlock(pos, true);
        } else if (state.get(AGE) >= 2){
            world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            BlockState naturalCoconutBlockState = ModBlocks.COCONUT.getDefaultState().with(CoconutBlock.PERSISTANT, false);
            FallingBlockEntity fallingBlockEntity = FallingBlockEntity.spawnFromBlock(world, pos, naturalCoconutBlockState);
            fallingBlockEntity.setHurtEntities(1, 5);
            fallingBlockEntity.dropItem = true;
            world.setBlockState(pos, this.getDefaultState());
        }
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(AGE)){
            case 0 ->{
                return SMALL_SHAPE;
            } case 1 ->{
                return MEDIUM_SHAPE;
            } default ->{
                return LARGE_SHAPE;
            }
        }
    }

    //@Override
    //public MapCodec<CoconutPlantBlock> getCodec() {return createCodec(CoconutPlantBlock::new);}

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int i = Math.min(2, (Integer)state.get(AGE) + 1);
        world.setBlockState(pos, (BlockState)state.with(AGE, i), 2);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        return this.canPlantBelow(world.getBlockState(blockPos), world, blockPos);
    }
    protected boolean canPlantBelow(BlockState ceiling, BlockView world, BlockPos pos) {
        return ceiling.isIn(BlockTags.LEAVES) || ceiling.isIn(BlockTags.LEAVES);
    }


    static {
        AGE = Properties.AGE_3;
        SMALL_SHAPE = Block.createCuboidShape(6.0, 12.0, 6.0, 10.0, 16.0, 10.0);
        MEDIUM_SHAPE = Block.createCuboidShape(5.0, 10.0, 5.0, 11.0, 16.0, 11.0);
        LARGE_SHAPE = Block.createCuboidShape(4.0, 8.0, 4.0, 12.0, 16.0, 12.0);
    }
}
