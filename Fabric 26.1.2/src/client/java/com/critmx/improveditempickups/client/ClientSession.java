package com.critmx.improveditempickups.client;

import com.critmx.improveditempickups.client.gui.PickupNotificationsRenderer;
import com.critmx.improveditempickups.client.presentation.PickupAggregationPresenter;
import com.critmx.improveditempickups.client.presentation.notification.policy.MergeNotificationsPolicy;
import com.critmx.improveditempickups.client.presentation.notification.policy.SeparatePickupsPolicy;
import com.critmx.improveditempickups.config.ImprovedItemPickupsConfig;
import com.critmx.improveditempickups.logic.PickupTrackerManager;
import net.minecraft.world.entity.player.Player;

public class ClientSession {
    private final PickupAggregationPresenter presenter;
//    private final DebugPickupNotificationUpdateListener updateListener;

    public ClientSession(Player player) {
        PickupTrackerManager.createTracker(player);

        var config = ImprovedItemPickupsConfig.CLIENT_CONFIG;
        presenter = new PickupAggregationPresenter(
                config.notificationLifetimeTicks,
                config.maxActiveNotifications
        );
        presenter.setPolicy(switch (config.repeatedPickupPolicy) {
            case MERGE -> new MergeNotificationsPolicy();
            case SEPARATE -> new SeparatePickupsPolicy();
        });

//        updateListener = new DebugPickupNotificationUpdateListener();
//        presenter.addUpdateListener(updateListener);
        presenter.addUpdateListener(PickupNotificationsRenderer.INSTANCE);

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

//        presenter.removeUpdateListener(updateListener);
        presenter.removeUpdateListener(PickupNotificationsRenderer.INSTANCE);
    }
}
