package pl.supercraft.magicbushes.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.ToIntFunction;

public class GlowingBerryBushBlock extends BerryBushBlock{
    public GlowingBerryBushBlock(Properties properties) {
        super(properties);
    }

    public static ToIntFunction<BlockState> lightEmission() {
        int light = 7;
        return (blockState) -> {
            return !MultifaceBlock.hasFace(blockState, Direction.NORTH) ? light : 0;
        };
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) { return level.getBlockState(pos.below(1)).is(ModBlocks.ETHEREAL_MOSS); }

}
