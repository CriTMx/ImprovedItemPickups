package com.critmx.improveditempickups.client.gui;

import com.critmx.improveditempickups.client.presentation.animation.AnimationController;
import com.critmx.improveditempickups.client.presentation.animation.AnimationState;
import com.critmx.improveditempickups.client.presentation.animation.Eases;
import com.critmx.improveditempickups.client.presentation.notification.IPickupNotification;
import com.critmx.improveditempickups.client.presentation.notification.IPickupNotificationDisplayComposition;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.phys.Vec2;

public class PickupNotificationDisplayElement implements IPickupNotificationDisplayElement {
    private IPickupNotification notification;
    private final IPickupNotificationDisplayComposition displayComposition;

    private final AnimationController animationController;

    private Vec2 position;

    private Vec2 animatedPosition;
    private Vec2 exitPosition;

    private static final float SLIDE_OFFSET = 50.0f;


    public PickupNotificationDisplayElement(IPickupNotification notification, IPickupNotificationDisplayComposition displayComposition, AnimationController animationController) {
        this.notification = notification;
        this.displayComposition = displayComposition;
        this.animationController = animationController;
    }

    @Override
    public void render(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker) {
        animationController.tick(deltaTracker.getGameTimeDeltaTicks());

        if (animationController.getState() == AnimationState.NONE) {
            return;
        }

        Vec2 animatedPosition = getAnimatedPosition();
        float animatedRotation = getAnimatedRotation();
        Vec2 animatedScale = getAnimatedScale();
        int animatedColor = getAnimatedColor();

        displayComposition.render(notification, guiGraphics, deltaTracker, animatedPosition, animatedRotation, animatedScale, animatedColor);
    }

    @Override
    public IPickupNotification getNotification() {
        return notification;
    }

    @Override
    public void setPosition(Vec2 position) {
        this.position = position;
    }

    @Override
    public void updateNotification(IPickupNotification notification) {
        this.notification = notification;
        animationController.refresh();
    }

    @Override
    public AnimationController getAnimationController() {
        return animationController;
    }

    private Vec2 getAnimatedPosition() {
        float progress = animationController.getProgress();
        return switch (animationController.getState()) {
            case NONE, CYCLE -> position;
            case IN -> {
                float x = position.x + SLIDE_OFFSET * (1f - Eases.EASE_OUT.apply(progress));
                yield new Vec2(x, position.y);
            }
            case OUT -> {
                Vec2 startPosition = exitPosition != null
                        ? exitPosition
                        : position;

                float x = startPosition.x + SLIDE_OFFSET * Eases.EASE_IN.apply(progress);
                yield new Vec2(x, startPosition.y);
            }
        };
    }

    private float getAnimatedRotation() {
        return 0f;
    }

    private Vec2 getAnimatedScale() {
        return new Vec2(1f, 1f);
    }

    private int getAnimatedColor() {
        float progress = animationController.getProgress();

        return switch (animationController.getState()) {
            case IN -> {
                int alpha = (int) (255 * Eases.EASE_IN.apply(progress));
                yield (alpha << 24) | 0xFFFFFF;
            }

            case CYCLE, NONE -> 0xFFFFFFFF;

            case OUT -> {
                int alpha = (int) (255 * (1.0f - Eases.EASE_OUT.apply(progress)));
                yield (alpha << 24) | 0xFFFFFF;
            }
        };
    }

    public void expire() {
        exitPosition = position;
        animationController.startOut();
    }
}
