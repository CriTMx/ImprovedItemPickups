package com.critmx.improveditempickups.common.logic;

import com.critmx.improveditempickups.ImprovedItemPickups;
import com.critmx.improveditempickups.common.logic.pickup.PickupAggregation;
import com.critmx.improveditempickups.common.logic.pickup.PickupEntry;
import com.critmx.improveditempickups.common.logic.pickup.PickupKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PickupTracker {
    private final Map<PickupKey, PickupEntry> pickups = new HashMap<>();
    private final Player player;

    public PickupTracker(Player player) {
        this.player = player;
    }

    public void recordPickup(ItemStack stack) {
        if (stack == null) {
            ImprovedItemPickups.LOGGER.error("[{}] Null/invalid item pickup detected.", ImprovedItemPickups.MODID);
            return;
        }

        PickupKey key = PickupKey.createFromItemStack(stack);
        if (pickups.containsKey(key)) {
            var existingEntry = pickups.get(key);
            var existingStack = existingEntry.stack();
            existingStack.grow(stack.count());
            pickups.put(key, new PickupEntry(existingStack));

            return;
        }

        var newEntry = new PickupEntry(stack.copy());
        pickups.put(key, newEntry);
    }

    public Collection<PickupEntry> getPickups() {
        return pickups.values();
    }

    public PickupAggregation flush() {
        PickupAggregation result = new PickupAggregation(this.player, List.copyOf(getPickups()));
        reset();
        return result;
    }

    public void reset() {
        pickups.clear();
    }
}
