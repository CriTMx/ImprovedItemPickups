package com.critmx.improveditempickups.client.presentation.notification.policy;

import com.critmx.improveditempickups.client.presentation.notification.IPickupNotification;
import com.critmx.improveditempickups.client.presentation.notification.operation.IPickupNotificationOperation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface IPickupNotificationPolicy {
    IPickupNotificationOperation determineOperation(List<IPickupNotification> activeNotifications, ItemStack stack);
}
