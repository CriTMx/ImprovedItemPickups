package com.critmx.improveditempickups.client.presentation.notification.component;

import com.critmx.improveditempickups.common.config.ImprovedItemPickupsConfig;
import com.critmx.improveditempickups.client.presentation.notification.IPickupNotification;
import net.minecraft.network.chat.Component;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.phys.Vec2;

public class PickupNotificationStyledNameComponent implements IPickupNotificationComponent {
    @Override
    public void render(IPickupNotification notification, GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, Vec2 position, int width, int height, int color) {
        var config = ImprovedItemPickupsConfig.CLIENT_CONFIG;
        boolean useRarityColor = config.showRarityAsItemNameColor.get();
        var name = buildName(notification, useRarityColor);
        var font = Minecraft.getInstance().font;
        int x = (int)position.x;
        int y = (int)position.y;

        int renderColor = useRarityColor
                ? color
                : multiplyRgbColor(config.nameColor.get(), color);
        guiGraphics.text(font, name, x, y, renderColor);
    }

    @Override
    public Vec2 getRequiredSize(IPickupNotification notification) {
        var config = ImprovedItemPickupsConfig.CLIENT_CONFIG;
        Component name = buildName(notification, config.showRarityAsItemNameColor.get());
        return new Vec2(Minecraft.getInstance().font.width(name), Minecraft.getInstance().font.lineHeight);
    }

    private Component buildName(IPickupNotification notification, boolean useRarityColor) {
        var config = ImprovedItemPickupsConfig.CLIENT_CONFIG;
        Component itemName = useRarityColor
                ? notification.getItemStack().getStyledHoverName()
                : notification.getItemStack().getHoverName();

        return Component.literal(config.namePrefix.get())
                .append(itemName)
                .append(config.nameSuffix.get());
    }

    private int multiplyRgbColor(int rgbColor, int animationColor) {
        int alpha = ((animationColor >>> 24) & 0xFF);
        int red = ((rgbColor >>> 16) & 0xFF) * ((animationColor >>> 16) & 0xFF) / 255;
        int green = ((rgbColor >>> 8) & 0xFF) * ((animationColor >>> 8) & 0xFF) / 255;
        int blue = (rgbColor & 0xFF) * (animationColor & 0xFF) / 255;
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }
}
