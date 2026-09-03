package com.critmx.improveditempickups.client.presentation.notification.component;

import com.critmx.improveditempickups.common.config.ImprovedItemPickupsConfig;
import com.critmx.improveditempickups.client.presentation.notification.IPickupNotification;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.phys.Vec2;

public class PickupNotificationQuantityComponent implements IPickupNotificationComponent {
    @Override
    public void render(IPickupNotification notification, GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, Vec2 position, int width, int height, int color) {
        var font = Minecraft.getInstance().font;
        int x = (int)position.x;
        int y = (int)position.y;

        var quantityString = buildQuantityString(notification);

        int configuredColor = ImprovedItemPickupsConfig.CLIENT_CONFIG.quantityColor.get();
        guiGraphics.text(font, quantityString, x, y, multiplyRgbColor(configuredColor, color));
    }

    @Override
    public Vec2 getRequiredSize(IPickupNotification notification) {
        var quantityString = buildQuantityString(notification);
        return new Vec2(Minecraft.getInstance().font.width(quantityString), Minecraft.getInstance().font.lineHeight);
    }

    private String buildQuantityString(IPickupNotification notification) {
        var config = ImprovedItemPickupsConfig.CLIENT_CONFIG;
        int quantity = notification.getItemStack().count();

        return config.quantityPrefix.get() + quantity + config.quantitySuffix.get();
    }

    private int multiplyRgbColor(int rgbColor, int animationColor) {
        int alpha = ((animationColor >>> 24) & 0xFF);
        int red = ((rgbColor >>> 16) & 0xFF) * ((animationColor >>> 16) & 0xFF) / 255;
        int green = ((rgbColor >>> 8) & 0xFF) * ((animationColor >>> 8) & 0xFF) / 255;
        int blue = (rgbColor & 0xFF) * (animationColor & 0xFF) / 255;
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }
}
