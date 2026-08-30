package com.critmx.improveditempickups.client.presentation.notification.policy;

import com.critmx.improveditempickups.client.presentation.notification.*;
import com.critmx.improveditempickups.client.presentation.notification.operation.CreateNotificationOperation;
import com.critmx.improveditempickups.client.presentation.notification.operation.IPickupNotificationOperation;
import com.critmx.improveditempickups.client.presentation.notification.operation.UpdateNotificationOperation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class MergeNotificationsPolicy implements IPickupNotificationPolicy {
    @Override
    public IPickupNotificationOperation determineOperation(List<IPickupNotification> activeNotifications, ItemStack stack) {
        var existingNotification = activeNotifications.stream()
                .filter(notification -> notification.matches(stack))
                .findFirst();
        if (existingNotification.isPresent()) {
            return new UpdateNotificationOperation(existingNotification.get(), stack);
        } else {
            return new CreateNotificationOperation(stack);
        }
    }
}
