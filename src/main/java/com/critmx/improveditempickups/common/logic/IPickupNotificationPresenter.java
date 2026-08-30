package com.critmx.improveditempickups.common.logic;

import com.critmx.improveditempickups.client.presentation.notification.IPickupNotification;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface IPickupNotificationPresenter {
    IPickupNotification createNotification(ItemStack stack);
    IPickupNotification updateNotification(IPickupNotification notification, ItemStack stack);
}
