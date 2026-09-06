package com.critmx.improveditempickups.logic;

import com.critmx.improveditempickups.logic.IPickupNotification;
import net.minecraft.world.item.ItemStack;

public interface IPickupNotificationPresenter {
    IPickupNotification createNotification(ItemStack stack);
    IPickupNotification updateNotification(IPickupNotification notification, ItemStack stack);
}
