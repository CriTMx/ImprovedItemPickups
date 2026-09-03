package com.critmx.improveditempickups.client.presentation.notification;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.phys.Vec2;

public interface IPickupNotificationDisplayComposition {
    void render(IPickupNotification notification, GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, Vec2 position, float rotation, Vec2 scale, int color);
}
