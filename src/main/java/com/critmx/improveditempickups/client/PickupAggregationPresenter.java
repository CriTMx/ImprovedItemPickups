package com.critmx.improveditempickups.client;

import com.critmx.improveditempickups.common.logic.IPickupAggregationListener;
import com.critmx.improveditempickups.common.logic.data.PickupAggregation;
import com.critmx.improveditempickups.common.logic.data.PickupEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;

public class PickupAggregationPresenter implements IPickupAggregationListener {
    @Override
    public void onPickupAggregation(PickupAggregation pickupAggregation) {
        var entries = pickupAggregation.entries();
        if (entries == null || entries.isEmpty()) {
            return;
        }

        for (PickupEntry entry : entries) {
            var stack = entry.stack();
            MutableComponent prefix = Component.literal("Item picked up: ");
            MutableComponent itemCount = Component.literal(String.format("x%d", stack.getCount()));
            MutableComponent itemName = (MutableComponent) stack.getStyledHoverName();
            MutableComponent finalMsg = prefix.append(itemCount).append(" ").append(itemName);
            pickupAggregation.player().sendSystemMessage(finalMsg);
        }
    }
}
