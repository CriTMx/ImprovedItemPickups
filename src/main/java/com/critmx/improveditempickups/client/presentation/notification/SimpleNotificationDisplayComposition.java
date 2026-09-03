package com.critmx.improveditempickups.client.presentation.notification;

import com.critmx.improveditempickups.ImprovedItemPickups;
import com.critmx.improveditempickups.client.presentation.notification.component.*;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
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
        var mc = Minecraft.getInstance();
        ItemStack stack = notification.getItemStack();

        int x = (int)position.x;
        int y = (int)position.y;

        int textWidth = (int) (nameComponent.getRequiredSize(notification).x + quantityComponent.getRequiredSize(notification).x);
        int quantityWidth = (int)quantityComponent.getRequiredSize(notification).x;
        int nameWidth = (int)nameComponent.getRequiredSize(notification).x;
        int textHeight = mc.font.lineHeight;

        int paddingLeft = 12;
        int paddingRight = 12;
        int paddingTop = 6;
        int paddingBottom = 6;

        int imageOffsetX = 0;
        int imageOffsetY = 0;

        int iconOffsetX = -12;
        int iconOffsetY = -9;

        int quantityOffsetX = 8;
        int quantityOffsetY = -4;

        int nameOffsetX = quantityWidth + 8 ;
        int nameOffsetY = -4;

        int width = 24 + textWidth + paddingLeft + paddingRight;
        int height = 42;

        Vec2 imagePos = new Vec2(x - paddingLeft - paddingRight + imageOffsetX, y - paddingTop - paddingBottom - height / 4 + imageOffsetY);
        Vec2 iconPos = new Vec2(x+iconOffsetX, y+iconOffsetY);
        Vec2 quantityPos = new Vec2(x+quantityOffsetX, y+quantityOffsetY);
        Vec2 namePos = new Vec2(x+nameOffsetX, y+nameOffsetY);

        backgroundComponent.render(notification, guiGraphics, deltaTracker, imagePos, width, height, color);
        frameComponent.render(notification, guiGraphics, deltaTracker, imagePos, width, height, color);
        iconComponent.render(notification, guiGraphics, deltaTracker, iconPos, width, height, color);
        quantityComponent.render(notification, guiGraphics, deltaTracker, quantityPos, width, height, color);
        nameComponent.render(notification, guiGraphics, deltaTracker, namePos, width, height, color);
    }
}
