package com.critmx.improveditempickups.client.presentation.animation;

import net.minecraft.world.phys.Vec2;

public class PositionAnimation implements IAnimation {
    private final float offsetX;
    private final float offsetY;
    private final Ease inEasing;
    private final Ease outEasing;

    public PositionAnimation(float offsetX, float offsetY, Ease inEasing, Ease outEasing) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.inEasing = inEasing;
        this.outEasing = outEasing;
    }

    @Override
    public AnimationResult apply(AnimationState state, float progress, AnimationResult currentAnim) {
        return switch (state) {
            case IN -> {
                float easedProgress = inEasing.apply(progress);
                Vec2 position = new Vec2(currentAnim.position().x + offsetX * (1f - easedProgress), currentAnim.position().y + offsetY * (1f - easedProgress));
                yield new AnimationResult(position, currentAnim.rotation(), currentAnim.scale(), currentAnim.color());
            }

            case OUT -> {
                float easedProgress = outEasing.apply(progress);
                Vec2 position = new Vec2(currentAnim.position().x + offsetX * easedProgress, currentAnim.position().y + offsetY * easedProgress);
                yield new AnimationResult(position, currentAnim.rotation(), currentAnim.scale(), currentAnim.color());
            }

            case CYCLE, NONE -> currentAnim;
        };
    }
}
