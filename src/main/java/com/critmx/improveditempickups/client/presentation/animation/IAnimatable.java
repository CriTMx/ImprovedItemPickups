package com.critmx.improveditempickups.client.presentation.animation;

public interface IAnimatable {
    AnimationController getAnimationController();

    AnimationResult getBaseState();

    default AnimationResult getAnimatedState() {
        AnimationController controller = getAnimationController();

        AnimationResult result = getBaseState();

        AnimationState state = controller.getState();
        float progress = controller.getProgress();

        for (IAnimation animation :
                controller.getDefinition().animations()) {

            result = animation.apply(state, progress, result);
        }

        return result;
    }
}
