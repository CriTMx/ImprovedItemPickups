package com.critmx.improveditempickups.client.presentation.notification.component;

import com.critmx.improveditempickups.client.presentation.notification.IPickupNotification;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.phys.Vec2;

public class PickupNotificationQuantityComponent implements IPickupNotificationComponent {
    @Override
    public void render(IPickupNotification notification, GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, Vec2 position, int width, int height, int color) {
        int quantity = notification.getItemStack().count();
        var font = Minecraft.getInstance().font;
        int x = (int)position.x;
        int y = (int)position.y;

        var quantityString = buildQuantityString(notification);

        guiGraphics.text(font, quantityString, x, y, color);
    }

    @Override
    public Vec2 getRequiredSize(IPickupNotification notification) {
        var quantityString = buildQuantityString(notification);
        return new Vec2(Minecraft.getInstance().font.width(quantityString), Minecraft.getInstance().font.lineHeight);
    }

    private String buildQuantityString(IPickupNotification notification) {
        String prefix = "x";
        String suffix = "";
        int quantity = notification.getItemStack().count();

        return String.format("%s%d%s ", prefix, quantity, suffix);
    }
}
