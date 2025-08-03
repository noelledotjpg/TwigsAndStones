package com.noelledotjpg.twigsandstones.item;

import com.noelledotjpg.twigsandstones.TwigsStones;
import com.noelledotjpg.twigsandstones.block.TwigBlock;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TwigsStones.MODID);

    public static final DeferredBlock<Block> TWIG = BLOCKS.register("twig",
            () -> new TwigBlock(BlockBehaviour.Properties.of().noCollission().instabreak()));

}
