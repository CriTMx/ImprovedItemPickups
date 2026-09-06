package com.critmx.improveditempickups.client.presentation.notification;

import com.critmx.improveditempickups.logic.IPickupNotification;

import java.util.List;

public interface IPickupNotificationUpdateListener {
    void onNotificationsUpdated(List<IPickupNotification> notifications);
}
