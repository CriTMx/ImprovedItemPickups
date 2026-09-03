package com.critmx.improveditempickups.client.gui;

import com.critmx.improveditempickups.client.presentation.animation.*;
import com.critmx.improveditempickups.client.presentation.notification.*;
import com.critmx.improveditempickups.common.config.ImprovedItemPickupsConfig;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.phys.Vec2;
import net.neoforged.neoforge.client.gui.GuiLayer;

import java.util.ArrayList;
import java.util.List;

public class PickupNotificationsRenderer implements GuiLayer, IPickupNotificationUpdateListener {
    private final List<IPickupNotificationDisplayElement> activeNotifElements = new ArrayList<>();
    public final static String GUI_LAYER_ID = "pickup_notifications";

    public static final PickupNotificationsRenderer INSTANCE = new PickupNotificationsRenderer();

    private IPickupNotificationDisplayComposition displayComposition = new SimpleNotificationDisplayComposition();

    private GuiGraphicsExtractor guiGraphics;

    @Override
    public void render(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker) {
        if (this.guiGraphics == null) {
            this.guiGraphics = guiGraphics;
        }

        if (activeNotifElements.isEmpty() || Minecraft.getInstance().gui.hud.isHidden()) {
            return;
        }

        var config = ImprovedItemPickupsConfig.CLIENT_CONFIG;
        int centerX = guiGraphics.guiWidth() / 2;
        int centerY = guiGraphics.guiHeight() / 2;
        float xBase;
        float yBase;
        switch (config.positionPreset.get()) {
            case HOTBAR_LEFT -> { xBase = centerX - 70; yBase = guiGraphics.guiHeight() - 16; }
            case RIGHT_SIDEBAR -> { xBase = guiGraphics.guiWidth(); yBase = centerY; }
            case LEFT_SIDEBAR -> { xBase = 16; yBase = centerY; }
            default -> { xBase = centerX + 115; yBase = guiGraphics.guiHeight() - 16; }
        }
        xBase += config.positionOffsetX.get();
        yBase += config.positionOffsetY.get();

        for (IPickupNotificationDisplayElement element : activeNotifElements) {
            int index = activeNotifElements.indexOf(element);
            Vec2 pos = new Vec2(xBase, yBase - config.notificationSpacing.get() * index);
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
        if (in.positionEnabled.get() || out.positionEnabled.get()) animations.add(new PositionAnimation(in.positionEnabled.get(), in.positionOffsetX.get().floatValue(), in.positionOffsetY.get().floatValue(), Eases.fromConfig(in.positionEase.get()), out.positionEnabled.get(), out.positionOffsetX.get().floatValue(), out.positionOffsetY.get().floatValue(), Eases.fromConfig(out.positionEase.get())));
        if (in.rotationEnabled.get() || out.rotationEnabled.get()) animations.add(new RotationAnimation(in.rotationEnabled.get(), (float) Math.toRadians(in.rotationDegrees.get()), Eases.fromConfig(in.rotationEase.get()), out.rotationEnabled.get(), (float) Math.toRadians(out.rotationDegrees.get()), Eases.fromConfig(out.rotationEase.get())));
        if (in.scaleEnabled.get() || out.scaleEnabled.get()) animations.add(new ScaleAnimation(in.scaleEnabled.get(), new Vec2(in.scaleX.get().floatValue(), in.scaleY.get().floatValue()), Eases.fromConfig(in.scaleEase.get()), out.scaleEnabled.get(), new Vec2(out.scaleX.get().floatValue(), out.scaleY.get().floatValue()), Eases.fromConfig(out.scaleEase.get())));
        if (in.colorEnabled.get() || out.colorEnabled.get()) animations.add(new ColorAnimation(in.colorEnabled.get(), parseColor(in.colorStart.get(), 0x00FFFFFF), parseColor(in.colorEnd.get(), 0xFFFFFFFF), Eases.fromConfig(in.colorEase.get()), out.colorEnabled.get(), parseColor(out.colorStart.get(), 0xFFFFFFFF), parseColor(out.colorEnd.get(), 0x00FFFFFF), Eases.fromConfig(out.colorEase.get())));
        return new AnimationController(new AnimationDefinition(config.inDurationTicks.get(), config.outDurationTicks.get(), animations), lifetimeTicks);
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
