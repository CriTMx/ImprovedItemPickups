package com.critmx.improveditempickups.client.gui;

import com.critmx.improveditempickups.client.presentation.notification.IPickupNotification;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;

public class PickupNotificationDisplayElement implements IPickupNotificationDisplayElement {
    private final IPickupNotification notification;
    private final IPickupNotificationDisplayDefinition displayDefinition;

    private Vec2 position;

    public PickupNotificationDisplayElement(IPickupNotification notification, IPickupNotificationDisplayDefinition displayDefinition, Vec2 position) {
        this.notification = notification;
        this.displayDefinition = displayDefinition;
        this.position = position;
    }

    @Override
    public void render(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker) {
        displayDefinition.render(notification, guiGraphics, deltaTracker, position);
    }

    @Override
    public IPickupNotification getNotification() {
        return notification;
    }

    @Override
    public void setPosition(Vec2 position) {
        this.position = position;
    }
}
