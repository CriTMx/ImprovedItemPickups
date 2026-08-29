package com.critmx.improveditempickups.client;

import com.critmx.improveditempickups.common.logic.PickupTracker;
import com.critmx.improveditempickups.common.logic.PickupTrackerManager;
import net.minecraft.world.entity.player.Player;

public class ClientSession {
    private final PickupAggregationPresenter presenter;

    public ClientSession(Player player) {
        PickupTrackerManager.createTracker(player);
        presenter = new PickupAggregationPresenter();
        PickupTrackerManager.addListener(presenter);
    }

    public void close() {
        PickupTrackerManager.removeListener(presenter);
        PickupTrackerManager.discardTracker();
    }
}
