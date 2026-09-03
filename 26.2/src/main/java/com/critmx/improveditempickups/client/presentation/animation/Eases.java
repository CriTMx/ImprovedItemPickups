package com.critmx.improveditempickups.client.presentation.animation;

import com.critmx.improveditempickups.common.config.AnimationEase;

public final class Eases {
    public static final Ease LINEAR = progress -> progress;
    public static final Ease IN_BACK = net.minecraft.util.Ease::inBack;
    public static final Ease IN_BOUNCE = net.minecraft.util.Ease::inBounce;
    public static final Ease IN_CUBIC = net.minecraft.util.Ease::inCubic;
    public static final Ease IN_ELASTIC = net.minecraft.util.Ease::inElastic;
    public static final Ease IN_EXPO = net.minecraft.util.Ease::inExpo;
    public static final Ease IN_QUAD = net.minecraft.util.Ease::inQuad;
    public static final Ease IN_QUART = net.minecraft.util.Ease::inQuart;
    public static final Ease IN_QUINT = net.minecraft.util.Ease::inQuint;
    public static final Ease IN_SINE = net.minecraft.util.Ease::inSine;
    public static final Ease IN_OUT_BACK = net.minecraft.util.Ease::inOutBack;
    public static final Ease IN_OUT_BOUNCE = net.minecraft.util.Ease::inOutBounce;
    public static final Ease IN_OUT_CIRC = net.minecraft.util.Ease::inOutCirc;
    public static final Ease IN_OUT_CUBIC = net.minecraft.util.Ease::inOutCubic;
    public static final Ease IN_OUT_ELASTIC = net.minecraft.util.Ease::inOutElastic;
    public static final Ease IN_OUT_EXPO = net.minecraft.util.Ease::inOutExpo;
    public static final Ease IN_OUT_QUAD = net.minecraft.util.Ease::inOutQuad;
    public static final Ease IN_OUT_QUART = net.minecraft.util.Ease::inOutQuart;
    public static final Ease IN_OUT_QUINT = net.minecraft.util.Ease::inOutQuint;
    public static final Ease IN_OUT_SINE = net.minecraft.util.Ease::inOutSine;
    public static final Ease OUT_BACK = net.minecraft.util.Ease::outBack;
    public static final Ease OUT_BOUNCE = net.minecraft.util.Ease::outBounce;
    public static final Ease OUT_CUBIC = net.minecraft.util.Ease::outCubic;
    public static final Ease OUT_ELASTIC = net.minecraft.util.Ease::outElastic;
    public static final Ease OUT_EXPO = net.minecraft.util.Ease::outExpo;
    public static final Ease OUT_QUAD = net.minecraft.util.Ease::outQuad;
    public static final Ease OUT_QUART = net.minecraft.util.Ease::outQuart;
    public static final Ease OUT_QUINT = net.minecraft.util.Ease::outQuint;
    public static final Ease OUT_SINE = net.minecraft.util.Ease::outSine;
    public static final Ease OUT_CIRC = net.minecraft.util.Ease::outCirc;
    public static final Ease IN_CIRC = net.minecraft.util.Ease::inCirc;

    public static Ease fromConfig(AnimationEase ease) {
        return switch (ease) {
            case LINEAR -> LINEAR;
            case IN_BACK -> IN_BACK;
            case IN_BOUNCE -> IN_BOUNCE;
            case IN_CUBIC -> IN_CUBIC;
            case IN_ELASTIC -> IN_ELASTIC;
            case IN_EXPO -> IN_EXPO;
            case IN_QUAD -> IN_QUAD;
            case IN_QUART -> IN_QUART;
            case IN_QUINT -> IN_QUINT;
            case IN_SINE -> IN_SINE;
            case IN_OUT_BACK -> IN_OUT_BACK;
            case IN_OUT_BOUNCE -> IN_OUT_BOUNCE;
            case IN_OUT_CIRC -> IN_OUT_CIRC;
            case IN_OUT_CUBIC -> IN_OUT_CUBIC;
            case IN_OUT_ELASTIC -> IN_OUT_ELASTIC;
            case IN_OUT_EXPO -> IN_OUT_EXPO;
            case IN_OUT_QUAD -> IN_OUT_QUAD;
            case IN_OUT_QUART -> IN_OUT_QUART;
            case IN_OUT_QUINT -> IN_OUT_QUINT;
            case IN_OUT_SINE -> IN_OUT_SINE;
            case OUT_BACK -> OUT_BACK;
            case OUT_BOUNCE -> OUT_BOUNCE;
            case OUT_CUBIC -> OUT_CUBIC;
            case OUT_ELASTIC -> OUT_ELASTIC;
            case OUT_EXPO -> OUT_EXPO;
            case OUT_QUAD -> OUT_QUAD;
            case OUT_QUART -> OUT_QUART;
            case OUT_QUINT -> OUT_QUINT;
            case OUT_SINE -> OUT_SINE;
            case OUT_CIRC -> OUT_CIRC;
            case IN_CIRC -> IN_CIRC;
        };
    }
}
