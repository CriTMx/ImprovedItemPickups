package com.critmx.improveditempickups.client.presentation.animation;

public class RotationAnimation implements IAnimation {
    private final float rotation;
    private final Ease inEasing;
    private final Ease outEasing;

    public RotationAnimation(
            float rotation,
            Ease inEasing,
            Ease outEasing) {
        this.rotation = rotation;
        this.inEasing = inEasing;
        this.outEasing = outEasing;
    }

    @Override
    public AnimationResult apply(AnimationState state, float progress, AnimationResult currentAnim) {

        return switch (state) {
            case IN ->
                    new AnimationResult(currentAnim.position(), currentAnim.rotation() + rotation * (1f - inEasing.apply(progress)), currentAnim.scale(), currentAnim.color());

            case OUT ->
                    new AnimationResult(currentAnim.position(), currentAnim.rotation() + rotation * outEasing.apply(progress), currentAnim.scale(), currentAnim.color());

            case CYCLE, NONE -> currentAnim;
        };
    }
}
