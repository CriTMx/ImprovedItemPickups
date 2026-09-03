package com.critmx.improveditempickups.client.gui;

import com.critmx.improveditempickups.client.presentation.animation.*;
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

        AnimationResult animResult = getAnimatedState();
        displayComposition.render(notification, guiGraphics, deltaTracker, animResult.position(), animResult.rotation(), animResult.scale(), animResult.color());
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

    public void expire() {
        exitPosition = position;
        animationController.startOut();
    }

    @Override
    public AnimationResult getBaseState() {
        return new AnimationResult(position, 0f, new Vec2(1f, 1f), 0xFFFFFFFF);
    }

    @Override
    public AnimationResult getAnimatedState() {
        AnimationResult result = getBaseState();
        AnimationState state = animationController.getState();
        float progress = animationController.getProgress();

        for (IAnimation animation : animationController.getDefinition().animations()) {
            result = animation.apply(state, progress, result);
        }

        return result;
    }
}
