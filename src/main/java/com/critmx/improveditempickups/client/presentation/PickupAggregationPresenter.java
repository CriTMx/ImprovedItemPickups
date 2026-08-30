package com.critmx.improveditempickups.client.presentation;

import com.critmx.improveditempickups.client.presentation.notification.IPickupNotification;
import com.critmx.improveditempickups.client.presentation.notification.IPickupNotificationUpdateListener;
import com.critmx.improveditempickups.client.presentation.notification.PickupNotification;
import com.critmx.improveditempickups.client.presentation.notification.policy.IPickupNotificationPolicy;
import com.critmx.improveditempickups.client.presentation.notification.policy.MergeNotificationsPolicy;
import com.critmx.improveditempickups.common.logic.IPickupAggregationListener;
import com.critmx.improveditempickups.common.logic.IPickupNotificationPresenter;
import com.critmx.improveditempickups.common.logic.pickup.*;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PickupAggregationPresenter implements IPickupAggregationListener, IPickupNotificationPresenter {
    private final List<IPickupNotification> activeNotifications = new ArrayList<>();
    private final List<IPickupNotificationUpdateListener> notificationUpdateListeners = new ArrayList<>();

    private final int notificationLifetimeTicks = 60;
    private final int maxNotifications = 5;

    private IPickupNotificationPolicy policy = new MergeNotificationsPolicy();

    @Override
    public void onPickupAggregation(PickupAggregation pickupAggregation) {
        var entries = pickupAggregation.entries();
        if (entries == null || entries.isEmpty()) {
            return;
        }

        for (PickupEntry entry : entries) {
            processPickup(entry.stack());
        }

        notifyUpdateListeners();

//      MutableComponent prefix = Component.literal("Item picked up: ");
//      MutableComponent itemCount = Component.literal(String.format("x%d", stack.getCount()));
//      MutableComponent itemName = (MutableComponent) stack.getStyledHoverName();
//      MutableComponent finalMsg = prefix.append(itemCount).append(" ").append(itemName);
//      pickupAggregation.player().sendSystemMessage(finalMsg);
    }

    private void processPickup(ItemStack stack) {
        var operation = policy.determineOperation(activeNotifications, stack);
        operation.execute(this);
    }

    public void tick() {
        var iterator = activeNotifications.iterator();
        boolean updated = false;

        while (iterator.hasNext()) {
            var notification = iterator.next();
            notification.tick();

            if (notification.isMarkedForRemoval()) {
                notification.onRemove();
                iterator.remove();
                updated = true;
            }
        }

        if (updated) {
            notifyUpdateListeners();
        }
    }

    public void setPolicy(IPickupNotificationPolicy policy) {
        this.policy = policy;
    }

    private void notifyUpdateListeners() {
        for (var listener : notificationUpdateListeners) {
            listener.onNotificationsUpdated(activeNotifications);
        }
    }

    public void addUpdateListener(IPickupNotificationUpdateListener listener) {
        notificationUpdateListeners.add(listener);
    }

    public void removeUpdateListener(IPickupNotificationUpdateListener listener) {
        notificationUpdateListeners.remove(listener);
    }

    public void clearUpdateListeners() {
        notificationUpdateListeners.clear();
    }

    @Override
    public IPickupNotification createNotification(ItemStack stack) {
        PickupNotification notification = new PickupNotification(stack.copy(), notificationLifetimeTicks);
        if (activeNotifications.size() >= maxNotifications) {
            var toRemove = activeNotifications.subList(0, activeNotifications.size() - maxNotifications + 1);
            activeNotifications.removeAll(toRemove);
        }
        activeNotifications.add(notification);
        notification.onStart();
        return notification;
    }

    @Override
    public IPickupNotification updateNotification(IPickupNotification notification, ItemStack stack) {
        notification.incrementItemStack(stack);
        notification.refreshLifetime();
        return notification;
    }
}
