package pl.supercraft.magicbushes.block;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import pl.supercraft.magicbushes.item.ModItems;

import java.util.function.Supplier;

import static pl.supercraft.magicbushes.MagicBushes.MODID;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    public static final DeferredBlock<Block> ETHEREAL_MOSS = registerBlock("ethereal_moss", () -> new EtherealMossBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK).mapColor(MapColor.COLOR_CYAN).lightLevel(EtherealMossBlock.lightEmission())));
    public static final DeferredBlock<Block> ETHEREAL_BERRY_BUSH = registerBlock("ethereal_berries", () -> new EtherealBerryBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));
    public static final DeferredBlock<Block> ETHEREAL_SAPLING = registerBlock("ethereal_sapling", () -> new EtherealSaplingBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .randomTicks()
            .instabreak()
            .sound(SoundType.GRASS)));

    public static final DeferredItem<BlockItem> ETHEREAL_MOSS_ITEM = registerBlockItem("ethereal_moss", ETHEREAL_MOSS);
    public static final DeferredItem<BlockItem> ETHEREAL_BERRY_ITEM = registerAdvancedBlockItem("ethereal_berries", ETHEREAL_BERRY_BUSH, new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(4)
                    .saturationModifier(0.3F)
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 1), 1.0F)
                    .build()
            ));

    static DeferredItem<BlockItem> registerBlockItem(String n, DeferredBlock<Block> b) {
        return ModItems.registerBlockItem(n, b);
    }

    static DeferredBlock<Block> registerBlock(String n, Supplier<? extends Block> b) {
        return BLOCKS.register(n, b);
    }

    public static DeferredItem<BlockItem> registerAdvancedBlockItem(String n, DeferredBlock<Block> b, Item.Properties p) {
        return ModItems.registerAdvancedBlockItem(n, b, p);
    }
}
