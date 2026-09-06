package com.critmx.improveditempickups.client.presentation.animation;

public interface IAnimation {
    AnimationResult apply(AnimationState state, float progress, AnimationResult currentAnim);
}
