package com.noelledotjpg.twigsandstones.entity;

import com.noelledotjpg.twigsandstones.TwigsStones;
import com.noelledotjpg.twigsandstones.item.FireStrikerItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, TwigsStones.MODID);
    public static final DeferredHolder<EntityType<?>, EntityType<Pebble>> PEBBLE = register(
            "pebble", EntityType.Builder.<Pebble>of(Pebble::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10)
    );

    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String key, EntityType.Builder<T> builder) {
        return ENTITY_TYPES.register(key, ()-> builder.build(key));
    }

}
