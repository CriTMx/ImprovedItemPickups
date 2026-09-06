package com.critmx.improveditempickups.client.presentation.animation;

public class RotationAnimation implements IAnimation {
    private final boolean inEnabled;
    private final boolean outEnabled;
    private final float inRotation;
    private final float outRotation;
    private final Ease inEasing;
    private final Ease outEasing;

    public RotationAnimation(
            boolean inEnabled, float inRotation, Ease inEasing,
            boolean outEnabled, float outRotation, Ease outEasing) {
        this.inEnabled = inEnabled;
        this.outEnabled = outEnabled;
        this.inRotation = inRotation;
        this.outRotation = outRotation;
        this.inEasing = inEasing;
        this.outEasing = outEasing;
    }

    @Override
    public AnimationResult apply(AnimationState state, float progress, AnimationResult currentAnim) {

        return switch (state) {
            case IN -> inEnabled ? new AnimationResult(currentAnim.position(), currentAnim.rotation() + inRotation * (1f - inEasing.apply(progress)), currentAnim.scale(), currentAnim.color()) : currentAnim;

            case OUT -> outEnabled ? new AnimationResult(currentAnim.position(), currentAnim.rotation() + outRotation * outEasing.apply(progress), currentAnim.scale(), currentAnim.color()) : currentAnim;

            case CYCLE, NONE -> currentAnim;
        };
    }
}
