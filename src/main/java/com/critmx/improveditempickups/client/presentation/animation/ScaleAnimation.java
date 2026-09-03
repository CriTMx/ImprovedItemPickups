package com.critmx.improveditempickups.client.presentation.animation;

import net.minecraft.world.phys.Vec2;

public class ScaleAnimation implements IAnimation {
    private final Vec2 scale;
    private final Ease inEase;
    private final Ease outEase;

    public ScaleAnimation(Vec2 scale, Ease inEase, Ease outEase) {
        this.scale = scale;
        this.inEase = inEase;
        this.outEase = outEase;
    }

    @Override
    public AnimationResult apply(AnimationState state, float progress, AnimationResult currentAnim) {
        return switch (state) {
            case NONE, CYCLE -> currentAnim;

            case IN -> {
                float easedProgress = inEase.apply(progress);
                Vec2 result = new Vec2(currentAnim.scale().x + (scale.x - currentAnim.scale().x) * easedProgress, currentAnim.scale().y + (scale.y - currentAnim.scale().y) * easedProgress);
                yield new AnimationResult(currentAnim.position(), currentAnim.rotation(), result, currentAnim.color());
            }

            case OUT -> {
                float easedProgress = outEase.apply(progress);
                Vec2 result = new Vec2(scale.x + (currentAnim.scale().x - scale.x) * easedProgress, scale.y + (currentAnim.scale().y - scale.y) * easedProgress);
                yield new AnimationResult(currentAnim.position(), currentAnim.rotation(), result, currentAnim.color());
            }
        };
    }
}
