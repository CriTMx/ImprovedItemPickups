package com.critmx.improveditempickups.client.presentation.animation;

public final class Eases {
    public static final Ease LINEAR = progress -> progress;

    public static final Ease EASE_OUT = progress -> 1.0f - (1.0f - progress) * (1.0f - progress);

    public static final Ease EASE_IN = progress -> progress * progress;
}
