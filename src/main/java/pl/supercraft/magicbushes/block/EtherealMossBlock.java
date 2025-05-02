package pl.supercraft.magicbushes.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.ToIntFunction;

public class EtherealMossBlock extends Block implements BonemealableBlock {
    private static final int MAX_BERRIES = 4;
    private static final int MAX_TREES = 2;

    public EtherealMossBlock(Properties properties) {
        super(properties);
    }

    public static ToIntFunction<BlockState> lightEmission() {
        int light = 3;
        return (blockState) -> {
            return !MultifaceBlock.hasFace(blockState, Direction.NORTH) ? light : 0;
        };
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof LivingEntity livingEntity)
            livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1), null);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) { return true; }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) { return true; }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int berriesSpawned = 0;
        int treesSpawned = 0;
        BlockPos[] genPos = {
                pos.north(1), pos.east(1), pos.south(1), pos.west(1),
                pos.north(2), pos.east(1).north(1), pos.south(1).north(1), pos.west(1).north(1),
                pos.north(1).east(1), pos.east(2), pos.south(1).east(1), pos.west(1).east(1),
                pos.north(1).south(1), pos.east(1).south(1), pos.south(2), pos.west(1).south(1),
                pos.north(1).west(1), pos.east(1).west(1), pos.south(1).west(1), pos.west(2),
        };
        for (BlockPos blockPos : genPos) {
            if (level.getBlockState(blockPos).is(BlockTags.MOSS_REPLACEABLE) && random.nextBoolean()) {
                level.setBlockAndUpdate(blockPos, state);
                spawnPlants(blockPos.above(1), random, level, berriesSpawned, treesSpawned);
            }
            if (level.getBlockState(blockPos.below(1)).is(BlockTags.MOSS_REPLACEABLE) && random.nextBoolean()) {
                level.setBlockAndUpdate(blockPos.below(1), state);
                spawnPlants(blockPos, random, level, berriesSpawned, treesSpawned);
            }
        }
    }

    private void spawnPlants(BlockPos pos, RandomSource random, ServerLevel level, int berriesSpawned, int treesSpawned) {
        int bs=berriesSpawned,ts=treesSpawned;
        if (random.nextIntBetweenInclusive(0, 10) == 10 && bs != MAX_BERRIES && level.isEmptyBlock(pos)) {
            bs++;
            level.setBlockAndUpdate(pos, ModBlocks.ETHEREAL_BERRY_BUSH.get().defaultBlockState());
        } else if (random.nextIntBetweenInclusive(0, 5) == 5 && ts != MAX_TREES && level.isEmptyBlock(pos)) {
            ts++;
            level.setBlockAndUpdate(pos, ModBlocks.ETHEREAL_SAPLING.get().defaultBlockState());
        }
    }

    @Override
    public BonemealableBlock.Type getType() { return Type.NEIGHBOR_SPREADER; }
}
