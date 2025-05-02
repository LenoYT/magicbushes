package pl.supercraft.magicbushes.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public class EtherealBerryBlock extends GlowingBerryBushBlock{
    public EtherealBerryBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return new ItemStack(ModBlocks.ETHEREAL_BERRY_ITEM.get());
    }
}
