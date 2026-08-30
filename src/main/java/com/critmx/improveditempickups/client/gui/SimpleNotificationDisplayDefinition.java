package com.critmx.improveditempickups.client.gui;

import com.critmx.improveditempickups.client.presentation.notification.IPickupNotification;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;

public class SimpleNotificationDisplayDefinition implements IPickupNotificationDisplayDefinition {
    @Override
    public void render(IPickupNotification notification, GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, Vec2 position) {
        ItemStack stack = notification.getItemStack();
        Component text = Component.literal("×" + stack.getCount() + " ")
                .append(stack.getHoverName());

        int x = (int)position.x;
        int y = (int)position.y;

        guiGraphics.item(stack, x-8, y-4);

        guiGraphics.text(
                Minecraft.getInstance().font,
                text,
                x + 10,
                y,
                0xFFFFFFFF
        );


    }
}
