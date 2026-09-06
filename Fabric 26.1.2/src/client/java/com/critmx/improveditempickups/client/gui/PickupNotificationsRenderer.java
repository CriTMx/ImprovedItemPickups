package com.critmx.improveditempickups.client.gui;

import com.critmx.improveditempickups.client.presentation.animation.*;
import com.critmx.improveditempickups.client.presentation.notification.*;
import com.critmx.improveditempickups.config.ImprovedItemPickupsConfig;
import com.critmx.improveditempickups.logic.IPickupNotification;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.phys.Vec2;

import java.util.ArrayList;
import java.util.List;

public class PickupNotificationsRenderer implements HudElement, IPickupNotificationUpdateListener {
    private final List<IPickupNotificationDisplayElement> activeNotifElements = new ArrayList<>();
    public final static String GUI_LAYER_ID = "pickup_notifications";

    public static final PickupNotificationsRenderer INSTANCE = new PickupNotificationsRenderer();

    private IPickupNotificationDisplayComposition displayComposition = new SimpleNotificationDisplayComposition();

    private GuiGraphicsExtractor guiGraphics;

    @Override
    public void extractRenderState(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker) {
        if (this.guiGraphics == null) {
            this.guiGraphics = guiGraphics;
        }

        if (activeNotifElements.isEmpty() || Minecraft.getInstance().options.hideGui) {
            return;
        }

        var config = ImprovedItemPickupsConfig.CLIENT_CONFIG;
        int centerX = guiGraphics.guiWidth() / 2;
        int centerY = guiGraphics.guiHeight() / 2;
        float xBase;
        float yBase;
        switch (config.positionPreset) {
            case HOTBAR_LEFT -> { xBase = centerX - 70; yBase = guiGraphics.guiHeight() - 16; }
            case RIGHT_SIDEBAR -> { xBase = guiGraphics.guiWidth(); yBase = centerY; }
            case LEFT_SIDEBAR -> { xBase = 16; yBase = centerY; }
            default -> { xBase = centerX + 115; yBase = guiGraphics.guiHeight() - 16; }
        }
        xBase += config.positionOffsetX;
        yBase += config.positionOffsetY;

        for (IPickupNotificationDisplayElement element : activeNotifElements) {
            int index = activeNotifElements.indexOf(element);
            Vec2 pos = new Vec2(xBase, yBase - config.notificationSpacing * index);
            element.setPosition(pos);
            element.render(guiGraphics, deltaTracker);
        }

        activeNotifElements.removeIf(element -> element.getAnimationController().getState() == AnimationState.NONE);
    }

    @Override
    public void onNotificationsUpdated(List<IPickupNotification> notifications) {
        for (var notification : notifications) {
            var existingElement = activeNotifElements.stream()
                    .filter(element -> element.getNotification() == notification)
                    .findFirst();

            if (existingElement.isPresent()) {
                existingElement.get().updateNotification(notification);
            } else {
                activeNotifElements.add(new PickupNotificationDisplayElement(notification, displayComposition, createAnimationController(notification.getLifetimeTicks())));
            }
        }

        for (var element : activeNotifElements) {
            boolean stillActive = notifications.stream()
                    .anyMatch(notification -> element.getNotification().matches(notification.getItemStack()));

            if (!stillActive) {
                element.expire();
            }
        }
    }

    public void setDisplayComposition(IPickupNotificationDisplayComposition displayComposition) {
        this.displayComposition = displayComposition;
    }

    private AnimationController createAnimationController(float lifetimeTicks) {
        var config = ImprovedItemPickupsConfig.CLIENT_CONFIG.animation;
        var in = config.in;
        var out = config.out;
        var animations = new ArrayList<IAnimation>();
        if (in.positionEnabled || out.positionEnabled) animations.add(new PositionAnimation(in.positionEnabled, (float) in.positionOffsetX, in.positionOffsetY, Eases.fromConfig(in.positionEase), out.positionEnabled, out.positionOffsetX, out.positionOffsetY, Eases.fromConfig(out.positionEase)));
        if (in.rotationEnabled || out.rotationEnabled) animations.add(new RotationAnimation(in.rotationEnabled, (float) Math.toRadians(in.rotationDegrees), Eases.fromConfig(in.rotationEase), out.rotationEnabled, (float) Math.toRadians(out.rotationDegrees), Eases.fromConfig(out.rotationEase)));
        if (in.scaleEnabled || out.scaleEnabled) animations.add(new ScaleAnimation(in.scaleEnabled, new Vec2(in.scaleX, in.scaleY), Eases.fromConfig(in.scaleEase), out.scaleEnabled, new Vec2(out.scaleX, out.scaleY), Eases.fromConfig(out.scaleEase)));
        if (in.colorEnabled || out.colorEnabled) animations.add(new ColorAnimation(in.colorEnabled, parseColor(in.colorStart, 0x00FFFFFF), parseColor(in.colorEnd, 0xFFFFFFFF), Eases.fromConfig(in.colorEase), out.colorEnabled, parseColor(out.colorStart, 0xFFFFFFFF), parseColor(out.colorEnd, 0x00FFFFFF), Eases.fromConfig(out.colorEase)));
        return new AnimationController(new AnimationDefinition(config.inDurationTicks, config.outDurationTicks, animations), lifetimeTicks);
    }

    private int parseColor(String value, int fallback) {
        try {
            String normalized = value.trim().replace("0x", "").replace("#", "");
            if (normalized.length() == 6) normalized = "FF" + normalized;
            return (int) Long.parseLong(normalized, 16);
        } catch (RuntimeException ignored) {
            return fallback;
        }
    }
}
