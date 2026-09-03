package com.critmx.improveditempickups.client.presentation.notification;

import com.critmx.improveditempickups.ImprovedItemPickups;
import com.critmx.improveditempickups.client.presentation.notification.component.*;
import com.critmx.improveditempickups.common.config.ImprovedItemPickupsConfig;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec2;

public class SimpleNotificationDisplayComposition implements IPickupNotificationDisplayComposition {
    private final Identifier backgroundImage = Identifier.fromNamespaceAndPath(ImprovedItemPickups.MODID, "pickup_background");
    private final Identifier backgroundFrame = Identifier.fromNamespaceAndPath(ImprovedItemPickups.MODID, "pickup_frame_default");

    private final PickupNotificationStyledNameComponent nameComponent = new PickupNotificationStyledNameComponent();
    private final PickupNotificationQuantityComponent quantityComponent = new PickupNotificationQuantityComponent();
    private final PickupNotificationImageComponent backgroundComponent = new PickupNotificationImageComponent(backgroundImage);
    private final PickupNotificationImageComponent frameComponent = new PickupNotificationImageComponent(backgroundFrame);
    private final PickupNotificationIconComponent iconComponent = new PickupNotificationIconComponent();

    @Override
    public void render(IPickupNotification notification, GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, Vec2 position, float rotation, Vec2 scale, int color) {
        var config = ImprovedItemPickupsConfig.CLIENT_CONFIG;

        int x = (int)position.x;
        int y = (int)position.y;

        boolean backgroundEnabled = config.backgroundEnabled.get();
        boolean frameEnabled = config.frameEnabled.get();
        boolean iconEnabled = config.iconEnabled.get();
        boolean quantityEnabled = config.quantityEnabled.get();
        boolean nameEnabled = config.nameEnabled.get();

        int quantityWidth = quantityEnabled ? (int) quantityComponent.getRequiredSize(notification).x : 0;
        int nameWidth = nameEnabled ? (int) nameComponent.getRequiredSize(notification).x : 0;
        int textWidth = quantityWidth + nameWidth;

        int paddingLeft = config.contentPaddingLeft.get();
        int paddingRight = config.contentPaddingRight.get();
        int paddingTop = config.contentPaddingTop.get();
        int paddingBottom = config.contentPaddingBottom.get();

        int iconSpace = iconEnabled ? 24 : 0;

        int width = Math.max(config.backgroundMinWidth.get(), iconSpace + textWidth + paddingLeft + paddingRight);
        int height = config.backgroundHeight.get();

        int imageX = x - paddingLeft - paddingRight;
        int imageY = y - paddingTop - paddingBottom - height / 4;

        Vec2 backgroundPos = new Vec2(imageX + config.backgroundOffsetX.get(), imageY + config.backgroundOffsetY.get());
        Vec2 framePos = new Vec2(imageX + config.frameOffsetX.get(), imageY + config.frameOffsetY.get());
        Vec2 iconPos = new Vec2(x + config.iconOffsetX.get(), y + config.iconOffsetY.get());
        Vec2 quantityPos = new Vec2(x + config.quantityOffsetX.get(), y + config.quantityOffsetY.get());
        Vec2 namePos = new Vec2(x + quantityWidth + config.nameOffsetX.get(), y + config.nameOffsetY.get());

        if (backgroundEnabled) {
            backgroundComponent.render(notification, guiGraphics, deltaTracker, backgroundPos, width, height, multiplyColor(parseColor(config.backgroundColor.get(), 0xFFFFFFFF), color));
        }

        if (frameEnabled) {
            int frameWidth = config.frameWidth.get() < 0 ? width : config.frameWidth.get();
            int frameHeight = config.frameHeight.get() < 0 ? height : config.frameHeight.get();
            frameComponent.render(notification, guiGraphics, deltaTracker, framePos, frameWidth, frameHeight, multiplyColor(parseColor(config.frameColor.get(), 0xFFFFFFFF), color));
        }

        if (iconEnabled) {
            iconComponent.render(notification, guiGraphics, deltaTracker, iconPos, width, height, color);
        }
        if (quantityEnabled) {
            quantityComponent.render(notification, guiGraphics, deltaTracker, quantityPos, width, height, color);
        }
        if (nameEnabled) {
            nameComponent.render(notification, guiGraphics, deltaTracker, namePos, width, height, color);
        }
    }

    private int parseColor(String value, int fallback) {
        if (value == null) {
            return fallback;
        }

        try {
            String normalized = value.trim();
            if (normalized.startsWith("0x") || normalized.startsWith("0X")) {
                normalized = normalized.substring(2);
            } else if (normalized.startsWith("#")) {
                normalized = normalized.substring(1);
            }

            if (normalized.length() == 6) {
                normalized = "FF" + normalized;
            }
            if (normalized.length() != 8) {
                return fallback;
            }
            return (int) Long.parseLong(normalized, 16);
        } catch (NumberFormatException ignored) {
            return fallback;
        }
    }

    private int multiplyColor(int baseColor, int animationColor) {
        int alpha = ((baseColor >>> 24) & 0xFF) * ((animationColor >>> 24) & 0xFF) / 255;
        int red = ((baseColor >>> 16) & 0xFF) * ((animationColor >>> 16) & 0xFF) / 255;
        int green = ((baseColor >>> 8) & 0xFF) * ((animationColor >>> 8) & 0xFF) / 255;
        int blue = (baseColor & 0xFF) * (animationColor & 0xFF) / 255;
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }
}
