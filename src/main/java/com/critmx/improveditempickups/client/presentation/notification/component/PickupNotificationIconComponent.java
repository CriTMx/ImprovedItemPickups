package com.critmx.improveditempickups.client.presentation.notification.component;

import com.critmx.improveditempickups.client.presentation.notification.IPickupNotification;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;

public class PickupNotificationIconComponent implements IPickupNotificationComponent {
    @Override
    public void render(IPickupNotification notification, GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, Vec2 position, int width, int height, int color) {
        ItemStack stack = notification.getItemStack();
        int x = (int)position.x;
        int y = (int)position.y;

        guiGraphics.item(stack, x, y);
    }

    @Override
    public Vec2 getRequiredSize(IPickupNotification notification) {
        return null;
    }
}
