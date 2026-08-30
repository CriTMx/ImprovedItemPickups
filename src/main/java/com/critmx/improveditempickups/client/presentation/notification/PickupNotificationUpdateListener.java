package com.critmx.improveditempickups.client.presentation.notification;

import com.critmx.improveditempickups.ImprovedItemPickups;

import java.util.List;

public class PickupNotificationUpdateListener implements IPickupNotificationUpdateListener {
    @Override
    public void onNotificationsUpdated(List<IPickupNotification> notifications) {
        ImprovedItemPickups.LOGGER.info("Notifications updated:");
        for(var notif : notifications) {
            String name = notif.getItemStack().getDisplayName().getString();
            int qty = notif.getItemStack().count();
            ImprovedItemPickups.LOGGER.info("x{} {}", qty, name);
        }
    }
}
