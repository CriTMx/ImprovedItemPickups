package com.critmx.improveditempickups.client.presentation.notification.operation;

import com.critmx.improveditempickups.common.logic.IPickupNotificationPresenter;
import net.minecraft.world.item.ItemStack;

public record CreateNotificationOperation(ItemStack stack) implements IPickupNotificationOperation {
    @Override
    public void execute(IPickupNotificationPresenter presenter) {
        presenter.createNotification(stack);
    }
}
