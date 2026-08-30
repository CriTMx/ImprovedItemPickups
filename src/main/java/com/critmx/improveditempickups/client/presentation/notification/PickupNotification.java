package com.critmx.improveditempickups.client.presentation.notification;

import net.minecraft.world.item.ItemStack;

public class PickupNotification implements IPickupNotification {
    private ItemStack itemStack;
    private int lifetimeTicks;
    private int currentTicks;
    private boolean markedForRemoval = false;

    public PickupNotification(ItemStack stack, int lifetimeTicks) {
        itemStack = stack;
        this.lifetimeTicks = lifetimeTicks;
        currentTicks = 0;
        markedForRemoval = false;
    }

    @Override
    public void tick() {
        if (markedForRemoval) {
            return;
        }

        currentTicks++;
        if (currentTicks >= lifetimeTicks) {
            markedForRemoval = true;
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRemove() {

    }

    @Override
    public void refreshLifetime() {
        markedForRemoval = false;
        currentTicks = 0;
    }

    @Override
    public boolean isMarkedForRemoval() {
        return markedForRemoval;
    }

    public void setMarkedForRemoval(boolean markedForRemoval) {
        this.markedForRemoval = markedForRemoval;
    }

    @Override
    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public void updateItemStack(ItemStack newStack) {
        this.itemStack = newStack;
    }

    @Override
    public void incrementItemStack(ItemStack newStack) {
        this.itemStack.grow(newStack.count());
    }

    @Override
    public boolean matches(ItemStack other) {
        if (this.itemStack == null || other == null) {
            return itemStack == null && other == null;
        }

        return this.itemStack.is(other.getItem()) &&
                this.itemStack.getComponents().equals(other.getComponents());
    }
}
