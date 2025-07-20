package net.zoey.cozyliving.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.zoey.cozyliving.block.ModBlocks;
import net.zoey.cozyliving.sound.ModSounds;
import net.zoey.cozyliving.util.ModDamageTypes;

import static net.minecraft.block.enums.DoubleBlockHalf.LOWER;

public class CoconutBlock extends FallingBlock implements LandingBlock {
    public static final BooleanProperty PERSISTANT = Properties.PERSISTENT;



    //public static final MapCodec<CoconutBlock> CODEC = createCodec(CoconutBlock::new);

    public CoconutBlock(Settings settings) {
        super(settings);
        this.setDefaultState((this.stateManager.getDefaultState()).with(PERSISTANT, Boolean.TRUE));
    }

    public DamageSource getDamageSource(Entity attacker) {
        return new DamageSource(attacker.getWorld().getRegistryManager()
                .get(RegistryKeys.DAMAGE_TYPE)
                .entryOf(ModDamageTypes.COCONUT_BONK_DAMAGE_TYPE));
    }

    //@Override
    //protected MapCodec<? extends FallingBlock> getCodec() {
    //   return CODEC;
    //}

    protected void configureFallingBlockEntity(FallingBlockEntity entity) {
        entity.setHurtEntities(1.0F, 6);
        entity.dropItem = true;
    }

    public static void spawnFallingBlock(BlockState state, ServerWorld world, BlockPos pos) {
        FallingBlockEntity fallingBlockEntity = FallingBlockEntity.spawnFromBlock(world, pos, state);
        fallingBlockEntity.setHurtEntities(1, 5);
    }

    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
        if (!fallingBlockEntity.isSilent()) {
            world.playSound(null, pos, ModSounds.COCONUT_BONK, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
        }
    }

    public void onDestroyedOnLanding(World world, BlockPos pos, FallingBlockEntity fallingBlockEntity) {
        if (!fallingBlockEntity.isSilent()) {
            world.playSound(null, pos, ModSounds.COCONUT_BONK, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
        }
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return (sideCoversSmallSquare(world, pos.down(), Direction.UP) || world.getBlockState(pos.down()).isAir());
    }

    //Borrowed from AbstractTorchBlock, stop asking me to simplify smh
    public static boolean sideCoversSmallSquare(WorldView world, BlockPos pos, Direction side) {
        BlockState blockState = world.getBlockState(pos);
        return side == Direction.DOWN && blockState.isIn(BlockTags.UNSTABLE_BOTTOM_CENTER) ? false : blockState.isSideSolid(world, pos, side, SideShapeType.CENTER);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PERSISTANT);
    }

    public boolean hasRandomTicks(BlockState state) {
        return state.get(PERSISTANT) == Boolean.FALSE;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        //Scans 32 blocks above for a coconut plant, if none are found the block decays.
        BlockPos position = pos.up();
        int i;
        boolean decay = true;
        BlockState blockState;
        for(i = 0; i < 32; i++){
            blockState = world.getBlockState(position);
            if (blockState.getBlock() == ModBlocks.COCONUT_PLANT){
                decay = false;
                break;
            } else if (blockState.isAir()) {
                position = position.up();
            }
        }
        if(decay){
            world.removeBlock(pos, false);
        }
    }
}
