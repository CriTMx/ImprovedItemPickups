package com.critmx.improveditempickups.client.presentation.animation;

import net.minecraft.world.phys.Vec2;

public class PositionAnimation implements IAnimation {
    private final boolean inEnabled;
    private final boolean outEnabled;
    private final float inOffsetX;
    private final float inOffsetY;
    private final float outOffsetX;
    private final float outOffsetY;
    private final Ease inEasing;
    private final Ease outEasing;

    public PositionAnimation(boolean inEnabled, float inOffsetX, float inOffsetY, Ease inEasing, boolean outEnabled, float outOffsetX, float outOffsetY, Ease outEasing) {
        this.inEnabled = inEnabled;
        this.outEnabled = outEnabled;
        this.inOffsetX = inOffsetX;
        this.inOffsetY = inOffsetY;
        this.outOffsetX = outOffsetX;
        this.outOffsetY = outOffsetY;
        this.inEasing = inEasing;
        this.outEasing = outEasing;
    }

    @Override
    public AnimationResult apply(AnimationState state, float progress, AnimationResult currentAnim) {
        return switch (state) {
            case IN -> {
                if (!inEnabled) yield currentAnim;
                float easedProgress = inEasing.apply(progress);
                Vec2 position = new Vec2(currentAnim.position().x + inOffsetX * (1f - easedProgress), currentAnim.position().y + inOffsetY * (1f - easedProgress));
                yield new AnimationResult(position, currentAnim.rotation(), currentAnim.scale(), currentAnim.color());
            }

            case OUT -> {
                if (!outEnabled) yield currentAnim;
                float easedProgress = outEasing.apply(progress);
                Vec2 position = new Vec2(currentAnim.position().x + outOffsetX * easedProgress, currentAnim.position().y + outOffsetY * easedProgress);
                yield new AnimationResult(position, currentAnim.rotation(), currentAnim.scale(), currentAnim.color());
            }

            case CYCLE, NONE -> currentAnim;
        };
    }
}
