package com.critmx.improveditempickups.client.gui;

import com.critmx.improveditempickups.client.presentation.notification.IPickupNotification;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.phys.Vec2;

public interface IPickupNotificationDisplayElement {
    void render(GuiGraphicsExtractor extractor, DeltaTracker deltaTracker);
    IPickupNotification getNotification();
    void setPosition(Vec2 position);
}
