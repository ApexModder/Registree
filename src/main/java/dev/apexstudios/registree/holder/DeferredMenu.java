package dev.apexstudios.registree.holder;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public final class DeferredMenu<TMenu extends AbstractContainerMenu> extends ApexDeferredHolder<MenuType<?>, MenuType<TMenu>> {
    public DeferredMenu(ResourceKey<MenuType<?>> registryKey) {
        super(registryKey);
    }
}
