package com.critmx.improveditempickups.client.presentation.notification.operation;

import com.critmx.improveditempickups.common.logic.IPickupNotificationPresenter;

public sealed interface IPickupNotificationOperation permits CreateNotificationOperation, UpdateNotificationOperation {
    void execute(IPickupNotificationPresenter presenter);
}
