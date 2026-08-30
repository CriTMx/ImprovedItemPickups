package com.critmx.improveditempickups.client.presentation.notification.policy;

import com.critmx.improveditempickups.client.presentation.notification.operation.CreateNotificationOperation;
import com.critmx.improveditempickups.client.presentation.notification.IPickupNotification;
import com.critmx.improveditempickups.client.presentation.notification.operation.IPickupNotificationOperation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class SeparatePickupsPolicy implements IPickupNotificationPolicy {
    @Override
    public IPickupNotificationOperation determineOperation(List<IPickupNotification> activeNotifications, ItemStack stack) {
        return new CreateNotificationOperation(stack);
    }
}
