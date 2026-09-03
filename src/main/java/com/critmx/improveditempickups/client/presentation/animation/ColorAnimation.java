package com.critmx.improveditempickups.client.presentation.animation;

public class ColorAnimation implements IAnimation {
    private final int inStartColor;
    private final int inEndColor;
    private final int outStartColor;
    private final int outEndColor;
    private final Ease inEasing;
    private final Ease outEasing;

    public ColorAnimation(int inStartColor, int inEndColor, int outStartColor, int outEndColor, Ease inEasing, Ease outEasing) {
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
                float easedProgress = inEasing.apply(progress);
                int alpha = (int) (inStartColor + 255 * easedProgress);

                yield new AnimationResult(currentAnim.position(), currentAnim.rotation(), currentAnim.scale(), interpolateColor(inStartColor, inEndColor, easedProgress));
            }

            case OUT -> {
                float easedProgress = outEasing.apply(progress);
                int alpha = (int) (255 * (1f - easedProgress));

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
