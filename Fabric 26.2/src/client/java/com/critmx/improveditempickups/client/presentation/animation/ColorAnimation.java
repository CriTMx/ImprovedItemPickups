package com.critmx.improveditempickups.client.presentation.animation;

public class ColorAnimation implements IAnimation {
    private final boolean inEnabled;
    private final boolean outEnabled;
    private final int inStartColor;
    private final int inEndColor;
    private final int outStartColor;
    private final int outEndColor;
    private final Ease inEasing;
    private final Ease outEasing;

    public ColorAnimation(boolean inEnabled, int inStartColor, int inEndColor, Ease inEasing, boolean outEnabled, int outStartColor, int outEndColor, Ease outEasing) {
        this.inEnabled = inEnabled;
        this.outEnabled = outEnabled;
        this.inStartColor = inStartColor;
        this.inEndColor = inEndColor;
        this.outStartColor = outStartColor;
        this.outEndColor = outEndColor;
        this.inEasing = inEasing;
        this.outEasing = outEasing;
    }

    @Override
    public AnimationResult apply(AnimationState state, float progress, AnimationResult currentAnim) {
        return switch (state) {
            case IN -> {
                if (!inEnabled) yield currentAnim;
                float easedProgress = inEasing.apply(progress);
                yield new AnimationResult(currentAnim.position(), currentAnim.rotation(), currentAnim.scale(), interpolateColor(inStartColor, inEndColor, easedProgress));
            }

            case OUT -> {
                if (!outEnabled) yield currentAnim;
                float easedProgress = outEasing.apply(progress);
                yield new AnimationResult(currentAnim.position(), currentAnim.rotation(), currentAnim.scale(), interpolateColor(outStartColor, outEndColor, easedProgress));
            }

            case CYCLE, NONE -> currentAnim;
        };
    }

    private int interpolateColor(int from, int to, float progress) {
        int a = interpolate((from >>> 24) & 0xFF, (to >>> 24) & 0xFF, progress);
        int r = interpolate((from >>> 16) & 0xFF, (to >>> 16) & 0xFF, progress);
        int g = interpolate((from >>> 8) & 0xFF, (to >>> 8) & 0xFF, progress);
        int b = interpolate(from & 0xFF, to & 0xFF, progress);

        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    private int interpolate(int from, int to, float progress) {
        return (int) (from + (to - from) * progress);
    }
}
