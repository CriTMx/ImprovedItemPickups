package com.critmx.improveditempickups.client.presentation.notification.component;

import com.critmx.improveditempickups.client.presentation.notification.IPickupNotification;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.phys.Vec2;

public class PickupNotificationNameComponent implements IPickupNotificationComponent {
    @Override
    public void render(IPickupNotification notification, GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, Vec2 position, int width, int height, int color) {
        var name = notification.getItemStack().getHoverName();
        var font = Minecraft.getInstance().font;
        int x = (int)position.x;
        int y = (int)position.y;

        guiGraphics.text(font, name, x, y, color);
    }

    @Override
    public Vec2 getRequiredSize(IPickupNotification notification) {
        return new Vec2(Minecraft.getInstance().font.width(notification.getItemStack().getHoverName()), Minecraft.getInstance().font.lineHeight);
    }
}
