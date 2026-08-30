package com.critmx.improveditempickups.client.gui;

import com.critmx.improveditempickups.client.presentation.notification.IPickupNotification;
import com.critmx.improveditempickups.client.presentation.notification.IPickupNotificationUpdateListener;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.phys.Vec2;
import net.neoforged.neoforge.client.gui.GuiLayer;

import java.util.ArrayList;
import java.util.List;

public class PickupNotificationsRenderer implements GuiLayer, IPickupNotificationUpdateListener {
    private final List<IPickupNotificationDisplayElement> activeNotifElements = new ArrayList<>();
    public final static String GUI_LAYER_ID = "pickup_notifications";

    public static final PickupNotificationsRenderer INSTANCE = new PickupNotificationsRenderer();

    private IPickupNotificationDisplayDefinition displayDefinition = new SimpleNotificationDisplayDefinition();

    private GuiGraphicsExtractor guiGraphics;

    @Override
    public void render(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker) {
        if (this.guiGraphics == null) {
            this.guiGraphics = guiGraphics;
        }

        if (activeNotifElements.isEmpty()) {
            return;
        }

        var itr = activeNotifElements.iterator();
        while (itr.hasNext()) {
            var element = itr.next();
            element.render(guiGraphics, deltaTracker);
        }
    }

    @Override
    public void onNotificationsUpdated(List<IPickupNotification> notifications) {

        float xBase = guiGraphics.guiWidth() - 500;
        float yBase = guiGraphics.guiHeight() - 16;

        activeNotifElements.clear();
        for (var notif : notifications) {
            activeNotifElements.add(new PickupNotificationDisplayElement(notif, displayDefinition, new Vec2(xBase, yBase - 18 * activeNotifElements.size())));
        }
        // we need the comparison for showing new/expiring old here (for animations).
    }

    public void setDisplayDefinition(IPickupNotificationDisplayDefinition displayDefinition) {
        this.displayDefinition = displayDefinition;
    }
}
