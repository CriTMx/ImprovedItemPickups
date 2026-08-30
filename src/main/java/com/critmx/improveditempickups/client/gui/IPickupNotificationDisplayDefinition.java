package com.critmx.improveditempickups.client.gui;

import com.critmx.improveditempickups.client.presentation.notification.IPickupNotification;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.phys.Vec2;

public interface IPickupNotificationDisplayDefinition {
    void render(IPickupNotification notification, GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, Vec2 position);
}
