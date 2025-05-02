package pl.supercraft.magicbushes.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static pl.supercraft.magicbushes.MagicBushes.MODID;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredItem<Item> EXAMPLE_ITEM = registerItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    static DeferredItem<Item> registerItem(String n, Item.Properties p) {
        return ITEMS.registerSimpleItem(n, p);
    }

    public static DeferredItem<BlockItem> registerBlockItem(String n, DeferredBlock<Block> b) {
        return ITEMS.registerSimpleBlockItem(n, b);
    }

    public static DeferredItem<BlockItem> registerAdvancedBlockItem(String n, DeferredBlock<Block> b, Item.Properties p) {
        return ITEMS.registerSimpleBlockItem(n, b, p);
    }
}
