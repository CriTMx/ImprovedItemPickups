package com.critmx.improveditempickups.common.logic.data;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public record PickupKey (
        Item item,
        DataComponentMap components
) {
    public static PickupKey createFromItemStack(ItemStack stack) {
        return new PickupKey(stack.getItem(), stack.getComponents());
    }

    public boolean equals(PickupKey other) {
        return other != null && this.item == other.item && this.components.equals(other.components);
    }
}
