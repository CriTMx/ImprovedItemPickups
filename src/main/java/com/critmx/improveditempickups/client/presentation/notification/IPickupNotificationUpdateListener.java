package com.critmx.improveditempickups.client.presentation.notification;

import java.util.List;

public interface IPickupNotificationUpdateListener {
    void onNotificationsUpdated(List<IPickupNotification> notifications);
}
