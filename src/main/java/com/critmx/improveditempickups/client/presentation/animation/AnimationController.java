package com.critmx.improveditempickups.client.presentation.animation;

public class AnimationController {
    private static final int IN_TICKS = 4;
    private static final int CYCLE_TICKS = 52;
    private static final int OUT_TICKS = 4;

    private AnimationState state = AnimationState.IN;
    private float stateElapsedTicks = 0;

    public AnimationController() {

    }

    public void tick(float delta) {
        stateElapsedTicks += delta;

        switch (state) {
            case IN -> tickIn();
            case CYCLE -> tickCycle();
            case OUT -> tickOut();
        }
    }

    private void tickIn() {
        float progress = Math.min((float) stateElapsedTicks / IN_TICKS, 1f);
        if (stateElapsedTicks >= IN_TICKS) {
            state = AnimationState.CYCLE;
            stateElapsedTicks = 0;
        }
    }

    private void tickCycle() {
        if (stateElapsedTicks >= CYCLE_TICKS) {
            startOut();
        }
    }

    private void tickOut() {
        float progress = Math.min((float) stateElapsedTicks / OUT_TICKS, 1f);

        if (stateElapsedTicks >= OUT_TICKS) {
            state = AnimationState.NONE;
        }
    }

    public void startOut() {
        if (state == AnimationState.CYCLE || state == AnimationState.IN) {
            state = AnimationState.OUT;
            stateElapsedTicks = 0;
        }
    }

    public float getProgress() {
        return switch (state) {
            case NONE -> 0f;
            case IN -> Math.min(1.0f, (float) stateElapsedTicks / IN_TICKS);
            case CYCLE -> 1.0f;
            case OUT -> Math.min(1.0f, (float) stateElapsedTicks / OUT_TICKS);
        };
    }

    public AnimationState getState() {
        return state;
    }

    public void refresh() {
        state = AnimationState.CYCLE;
        stateElapsedTicks = 0;
    }
}
