package com.critmx.improveditempickups.client.presentation.notification;

import net.minecraft.world.item.ItemStack;

public interface IPickupNotification {
    void tick();
    void onStart();
    void onRemove();
    void refreshLifetime();

    void updateItemStack(ItemStack newStack);
    void incrementItemStack(ItemStack newStack);
    boolean isMarkedForRemoval();

    boolean matches(ItemStack other);
    ItemStack getItemStack();
    int getLifetimeTicks();
}
