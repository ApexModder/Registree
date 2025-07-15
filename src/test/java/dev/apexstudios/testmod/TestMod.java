package dev.apexstudios.testmod;

import dev.apexstudios.registree.Registree;
import dev.apexstudios.registree.holder.DeferredBlock;
import dev.apexstudios.registree.holder.DeferredItem;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(TestMod.ID)
public final class TestMod {
    public static final String ID = "testmod";
    public static final Registree REGISTREE = new Registree(ID);

    public static final DeferredBlock<Block> TEST_BLOCK = REGISTREE.registerSimpleBlock("test_block", () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS));
    public static final DeferredItem<BlockItem> TEST_BLOCK_ITEM = REGISTREE.registerSimpleBlockItem(TEST_BLOCK);

    public static final DeferredItem<Item> TEST_ITEM = REGISTREE.registerSimpleItem("test_item");

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = REGISTREE.registerCreativeModeTab("items", () -> TEST_ITEM.toStack(), (parameters, output) -> REGISTREE
            .asLookup(Registries.ITEM)
            .filterFeatures(parameters.enabledFeatures())
            .listElements()
            .map(Holder::value)
            .map(Item::getDefaultInstance)
            .forEach(output::accept)
    );

    public TestMod(IEventBus modBus) {
         REGISTREE.registerEvents(modBus);
    }
}
