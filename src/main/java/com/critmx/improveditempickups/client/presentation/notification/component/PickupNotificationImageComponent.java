package com.critmx.improveditempickups.client.presentation.notification.component;

import com.critmx.improveditempickups.client.presentation.animation.AnimationController;
import com.critmx.improveditempickups.client.presentation.animation.AnimationResult;
import com.critmx.improveditempickups.client.presentation.animation.AnimationState;
import com.critmx.improveditempickups.client.presentation.animation.IAnimatable;
import com.critmx.improveditempickups.client.presentation.notification.IPickupNotification;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec2;

public class PickupNotificationImageComponent implements IPickupNotificationComponent {
    private final Identifier identifier;
    private Vec2 position;

    public PickupNotificationImageComponent(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public void render(IPickupNotification notification, GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, Vec2 position, int width, int height, int color) {
        this.position = position;

        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, identifier,
            (int)position.x, (int)position.y,
            width, height,
            color
        );
    }

    @Override
    public Vec2 getRequiredSize(IPickupNotification notification) {
        return null;
    }
}
