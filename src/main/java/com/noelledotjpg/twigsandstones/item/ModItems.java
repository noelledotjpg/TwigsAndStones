package com.noelledotjpg.twigsandstones.item;

import com.noelledotjpg.twigsandstones.TwigsStones;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TwigsStones.MODID);

    public static final DeferredItem<Item> FIRE_STRIKER = ITEMS.register("fire_striker",
            () -> new FireStrikerItem(new Item.Properties().durability(10).stacksTo(1)));

    public static final DeferredItem<Item> TWIG = ITEMS.register("twig",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> PEBBLE = ITEMS.register("pebble",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
