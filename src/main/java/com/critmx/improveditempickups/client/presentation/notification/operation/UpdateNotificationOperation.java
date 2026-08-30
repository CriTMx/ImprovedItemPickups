package com.critmx.improveditempickups.client.presentation.notification.operation;

import com.critmx.improveditempickups.client.presentation.notification.IPickupNotification;
import com.critmx.improveditempickups.common.logic.IPickupNotificationPresenter;
import net.minecraft.world.item.ItemStack;

public record UpdateNotificationOperation(IPickupNotification notification, ItemStack stack) implements IPickupNotificationOperation {
    @Override
    public void execute(IPickupNotificationPresenter presenter) {
        presenter.updateNotification(notification, stack);
    }
}
