package com.critmx.improveditempickups.common.logic;

import com.critmx.improveditempickups.common.logic.pickup.PickupAggregation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PickupTrackerManager {
    static PickupTracker tracker;
    static long lastPickupTick = -1;
    static final long pickupWindowTicks = 2;
    static boolean isWindowActive = false;

    private static final List<IPickupAggregationListener> listeners = new ArrayList<>();

    /// <region>Lifecycle management API</region>

    public static void createTracker(Player player) {
        tracker = new PickupTracker(player);
    }

    public static void discardTracker() {
        lastPickupTick = -1;
        isWindowActive = false;

        if (tracker == null) {
            return;
        }
        tracker.reset();
        tracker = null;
    }

    public static void tick(long currentTick) {
        if (!isWindowActive) {
            return;
        }

        if (tracker == null) {
            return;
        }

        if (currentTick >= lastPickupTick + pickupWindowTicks) {
            flush();
        }
    }

    /// <endregion></endregion>

    public static void onPickup(ItemStack stack, long pickupTick) {
        lastPickupTick = pickupTick;
        isWindowActive = true;
        tracker.recordPickup(stack);
    }

    public static PickupAggregation flush() {
        isWindowActive = false;
        var aggregation = tracker.flush();
        notifyAggregationListeners(aggregation);
        return aggregation;
    }

    public static void notifyAggregationListeners(PickupAggregation aggregation) {
        for (IPickupAggregationListener listener : listeners) {
            if (listener == null) {
                continue;
            }

            listener.onPickupAggregation(aggregation);
        }
    }

    public static PickupTracker getTracker() {
        return tracker;
    }

    public static boolean isTrackerAlive() {
        return tracker != null;
    }

    public static boolean isTrackerWindowActive() {
        return isTrackerAlive() && isWindowActive;
    }

    public static void addListener(IPickupAggregationListener listener) {
        listeners.add(listener);
    }

    public static void removeListener(IPickupAggregationListener listener) {
        listeners.remove(listener);
    }
}
