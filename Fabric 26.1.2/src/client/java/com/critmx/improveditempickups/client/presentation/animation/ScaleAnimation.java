package com.critmx.improveditempickups.client.presentation.animation;

import net.minecraft.world.phys.Vec2;

public class ScaleAnimation implements IAnimation {
    private final boolean inEnabled;
    private final boolean outEnabled;
    private final Vec2 inScale;
    private final Vec2 outScale;
    private final Ease inEase;
    private final Ease outEase;

    public ScaleAnimation(boolean inEnabled, Vec2 inScale, Ease inEase, boolean outEnabled, Vec2 outScale, Ease outEase) {
        this.inEnabled = inEnabled;
        this.outEnabled = outEnabled;
        this.inScale = inScale;
        this.outScale = outScale;
        this.inEase = inEase;
        this.outEase = outEase;
    }

    @Override
    public AnimationResult apply(AnimationState state, float progress, AnimationResult currentAnim) {
        return switch (state) {
            case NONE, CYCLE -> currentAnim;

            case IN -> {
                if (!inEnabled) yield currentAnim;
                float easedProgress = inEase.apply(progress);
                Vec2 result = new Vec2(currentAnim.scale().x + (inScale.x - currentAnim.scale().x) * easedProgress, currentAnim.scale().y + (inScale.y - currentAnim.scale().y) * easedProgress);
                yield new AnimationResult(currentAnim.position(), currentAnim.rotation(), result, currentAnim.color());
            }

            case OUT -> {
                if (!outEnabled) yield currentAnim;
                float easedProgress = outEase.apply(progress);
                Vec2 result = new Vec2(outScale.x + (currentAnim.scale().x - outScale.x) * easedProgress, outScale.y + (currentAnim.scale().y - outScale.y) * easedProgress);
                yield new AnimationResult(currentAnim.position(), currentAnim.rotation(), result, currentAnim.color());
            }
        };
    }
}
