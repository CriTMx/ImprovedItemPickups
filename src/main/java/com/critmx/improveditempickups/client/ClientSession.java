package com.critmx.improveditempickups.client;

import com.critmx.improveditempickups.client.presentation.PickupAggregationPresenter;
import com.critmx.improveditempickups.client.presentation.notification.PickupNotificationUpdateListener;
import com.critmx.improveditempickups.client.presentation.notification.policy.MergeNotificationsPolicy;
import com.critmx.improveditempickups.common.logic.PickupTrackerManager;
import net.minecraft.world.entity.player.Player;

public class ClientSession {
    private final PickupAggregationPresenter presenter;
    private final PickupNotificationUpdateListener updateListener;

    public ClientSession(Player player) {
        PickupTrackerManager.createTracker(player);
        presenter = new PickupAggregationPresenter();
        presenter.setPolicy(new MergeNotificationsPolicy());

        updateListener = new PickupNotificationUpdateListener();
        presenter.addUpdateListener(updateListener);

        PickupTrackerManager.addListener(presenter);
    }

    public void tick() {
        if (presenter != null) {
            presenter.tick();
        }
    }

    public void close() {
        PickupTrackerManager.removeListener(presenter);
        PickupTrackerManager.discardTracker();

        presenter.removeUpdateListener(updateListener);
    }
}
