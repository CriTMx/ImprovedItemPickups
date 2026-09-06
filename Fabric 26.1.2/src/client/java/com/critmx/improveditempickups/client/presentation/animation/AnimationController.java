package com.critmx.improveditempickups.client.presentation.animation;

public class AnimationController {

    private final AnimationDefinition definition;
    private final int cycleTicks;
    private AnimationState state = AnimationState.IN;
    private float stateElapsedTicks = 0;

    public AnimationController(AnimationDefinition definition, float lifetimeTicks) {
        this.definition = definition;
        this.cycleTicks = (int)Math.max(0, lifetimeTicks - definition.inTicks() - definition.outTicks());
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
        if (stateElapsedTicks >= definition.inTicks() || definition.inTicks() == 0) {
            state = AnimationState.CYCLE;
            stateElapsedTicks = 0;
        }
    }

    private void tickCycle() {
        if (stateElapsedTicks >= cycleTicks) {
            startOut();
        }
    }

    private void tickOut() {
        if (stateElapsedTicks >= definition.outTicks() || definition.outTicks() == 0) {
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
            case IN -> {
                yield definition.inTicks() == 0 ? 1f : Math.min(1.0f, (float) stateElapsedTicks / definition.inTicks());
            }
            case CYCLE -> 1.0f;
            case OUT -> {
                yield definition.outTicks() == 0 ? 1f : Math.min(1.0f, (float) stateElapsedTicks / definition.outTicks());
            }
        };
    }

    public AnimationState getState() {
        return state;
    }

    public AnimationDefinition getDefinition() {
        return definition;
    }

    public void refresh() {
        state = AnimationState.CYCLE;
        stateElapsedTicks = 0;
    }
}
