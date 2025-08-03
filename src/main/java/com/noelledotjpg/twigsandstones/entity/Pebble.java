package com.noelledotjpg.twigsandstones.entity;

import com.noelledotjpg.twigsandstones.item.ModItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class Pebble extends ThrowableItemProjectile {
    public Pebble(EntityType<Pebble> entityType, Level level) {
        super(entityType, level);
    }

    public Pebble(Level level, LivingEntity shooter) {
        super(ModEntityTypes.PEBBLE.get(), shooter, level);
    }

    public Pebble(Level level, double x, double y, double z) {
        super(ModEntityTypes.PEBBLE.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.PEBBLE.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        entity.hurt(level().damageSources().thrown(this, this.getOwner()), 1);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
            if (!(result instanceof EntityHitResult)) {
                this.level().addFreshEntity(new ItemEntity(this.level(), result.getLocation().x, result.getLocation().y, result.getLocation().z, ModItems.PEBBLE.toStack()));
            }
        } else {
            ParticleOptions particleOptions = new ItemParticleOption(ParticleTypes.ITEM, this.getItem());

            for (int i = 0; i < 16; i++) {
                this.level().addParticle(particleOptions, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }
}