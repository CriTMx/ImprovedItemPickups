package com.critmx.improveditempickups.client.gui;

import com.critmx.improveditempickups.client.presentation.animation.*;
import com.critmx.improveditempickups.client.presentation.notification.*;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.Ease;
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

        if (activeNotifElements.isEmpty()) {
            return;
        }

        int centerX = guiGraphics.guiWidth() / 2;
        int centerY = guiGraphics.guiHeight() / 2;

        int marginX = 24;
        int marginY = -16;

        int spacingX = 0;
        int spacingY = 28;

        float xBase = centerX + 91;
        float yBase = guiGraphics.guiHeight();

        for (IPickupNotificationDisplayElement element : activeNotifElements) {
            int index = activeNotifElements.indexOf(element);
            Vec2 pos = new Vec2(xBase + marginX, yBase + marginY - spacingY * index);
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
                activeNotifElements.add(new PickupNotificationDisplayElement(notification, displayComposition, new AnimationController(
                        new AnimationDefinition(4, 4, List.of(
                                new PositionAnimation(50f, 0f, Eases.EASE_OUT, Eases.EASE_IN),
                                new ColorAnimation(0x00FFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x00FFFFFF, Eases.EASE_IN, Eases.EASE_OUT)
                        )),
                        notification.getLifetimeTicks()
                )));
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
}
