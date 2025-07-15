package dev.apexstudios.registree.holder;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public final class DeferredEntity<TEntity extends Entity> extends ApexDeferredHolder<EntityType<?>, EntityType<TEntity>> {
    public DeferredEntity(ResourceKey<EntityType<?>> registryKey) {
        super(registryKey);
    }
}
