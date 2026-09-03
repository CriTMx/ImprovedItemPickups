package com.critmx.improveditempickups.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public final class ImprovedItemPickupsConfig {
    public static final ImprovedItemPickupsConfig CLIENT_CONFIG;
    public static final ModConfigSpec CLIENT_SPEC;

    static {
        Pair<ImprovedItemPickupsConfig, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(ImprovedItemPickupsConfig::new);
        CLIENT_CONFIG = pair.getLeft();
        CLIENT_SPEC = pair.getRight();
    }

    public final ModConfigSpec.IntValue notificationLifetimeTicks;
    public final ModConfigSpec.IntValue maxActiveNotifications;
    public final ModConfigSpec.EnumValue<RepeatedPickupPolicy> repeatedPickupPolicy;
    public final ModConfigSpec.EnumValue<PositionPreset> positionPreset;
    public final ModConfigSpec.IntValue positionOffsetX;
    public final ModConfigSpec.IntValue positionOffsetY;
    public final ModConfigSpec.IntValue notificationSpacing;
    public final AnimationSettings animation;

    public final ModConfigSpec.BooleanValue backgroundEnabled;
    public final ModConfigSpec.BooleanValue frameEnabled;
    public final ModConfigSpec.BooleanValue iconEnabled;
    public final ModConfigSpec.BooleanValue quantityEnabled;
    public final ModConfigSpec.BooleanValue nameEnabled;

    public final ModConfigSpec.ConfigValue<String> quantityPrefix;
    public final ModConfigSpec.ConfigValue<String> quantitySuffix;
    public final ModConfigSpec.ConfigValue<String> namePrefix;
    public final ModConfigSpec.ConfigValue<String> nameSuffix;
    public final ModConfigSpec.BooleanValue showRarityAsItemNameColor;

    public final ModConfigSpec.IntValue backgroundOffsetX;
    public final ModConfigSpec.IntValue backgroundOffsetY;
    public final ModConfigSpec.IntValue frameOffsetX;
    public final ModConfigSpec.IntValue frameOffsetY;
    public final ModConfigSpec.IntValue iconOffsetX;
    public final ModConfigSpec.IntValue iconOffsetY;
    public final ModConfigSpec.IntValue quantityOffsetX;
    public final ModConfigSpec.IntValue quantityOffsetY;
    public final ModConfigSpec.IntValue nameOffsetX;
    public final ModConfigSpec.IntValue nameOffsetY;

    public final ModConfigSpec.IntValue contentPaddingLeft;
    public final ModConfigSpec.IntValue contentPaddingRight;
    public final ModConfigSpec.IntValue contentPaddingTop;
    public final ModConfigSpec.IntValue contentPaddingBottom;
    public final ModConfigSpec.IntValue backgroundMinWidth;
    public final ModConfigSpec.IntValue backgroundHeight;
    public final ModConfigSpec.IntValue frameWidth;
    public final ModConfigSpec.IntValue frameHeight;

    public final ModConfigSpec.ConfigValue<String> backgroundColor;
    public final ModConfigSpec.ConfigValue<String> frameColor;
    public final ModConfigSpec.IntValue quantityColor;
    public final ModConfigSpec.IntValue nameColor;

    private ImprovedItemPickupsConfig(ModConfigSpec.Builder builder) {
        builder.push("notifications");
        notificationLifetimeTicks = builder
                .comment("How long a pickup notification remains visible, in ticks.")
                .translation("improveditempickups.config.notifications.notification_lifetime_ticks")
                .defineInRange(
                        "notification_lifetime_ticks",
                        60,
                        8,
                        1200
                );

        maxActiveNotifications = builder
                .comment("Maximum number of pickup notifications shown at once.")
                .translation("improveditempickups.config.notifications.max_active_notifications")
                .defineInRange(
                        "max_active_notifications",
                        5,
                        1,
                        100
                );

        repeatedPickupPolicy = builder
                .comment("How repeated pickups of the same item are presented.")
                .translation("improveditempickups.config.notifications.repeated_pickup_policy")
                .defineEnum(
                        "repeated_pickup_policy",
                        RepeatedPickupPolicy.MERGE
                );

        builder.pop();

        builder.push("position");
        positionPreset = builder.comment("Preset anchor used for the notification stack.")
                .translation("improveditempickups.config.position.preset")
                .defineEnum("preset", PositionPreset.HOTBAR_RIGHT);
        positionOffsetX = builder.comment("Horizontal offset applied after the preset anchor.")
                .translation("improveditempickups.config.position.offset_x")
                .defineInRange("offset_x", 0, -4096, 4096);
        positionOffsetY = builder.comment("Vertical offset applied after the preset anchor.")
                .translation("improveditempickups.config.position.offset_y")
                .defineInRange("offset_y", 0, -4096, 4096);
        notificationSpacing = builder.comment("Vertical distance between stacked notifications.")
                .translation("improveditempickups.config.position.notification_spacing")
                .defineInRange("notification_spacing", 28, 0, 4096);
        builder.pop();

        builder.push("animation");
        animation = new AnimationSettings(builder);
        builder.pop();

        builder.push("components");

        backgroundEnabled = builder
                .comment("Whether the notification background image is rendered.")
                .translation("improveditempickups.config.components.background_enabled")
                .define("background_enabled", true);

        frameEnabled = builder
                .comment("Whether the notification frame image is rendered.")
                .translation("improveditempickups.config.components.frame_enabled")
                .define("frame_enabled", true);

        iconEnabled = builder
                .comment("Whether the picked-up item icon is rendered.")
                .translation("improveditempickups.config.components.icon_enabled")
                .define("icon_enabled", true);

        quantityEnabled = builder
                .comment("Whether the picked-up quantity is rendered.")
                .translation("improveditempickups.config.components.quantity_enabled")
                .define("quantity_enabled", true);

        nameEnabled = builder
                .comment("Whether the picked-up item name is rendered.")
                .translation("improveditempickups.config.components.name_enabled")
                .define("name_enabled", true);

        quantityPrefix = builder
                .comment("Literal text placed before the picked-up quantity.")
                .translation("improveditempickups.config.components.quantity_prefix")
                .define("quantity_prefix", "x");

        quantitySuffix = builder
                .comment("Literal text placed after the picked-up quantity.")
                .translation("improveditempickups.config.components.quantity_suffix")
                .define("quantity_suffix", " ");

        namePrefix = builder
                .comment("Literal text placed before the item name.")
                .translation("improveditempickups.config.components.name_prefix")
                .define("name_prefix", "");

        nameSuffix = builder
                .comment("Literal text placed after the item name.")
                .translation("improveditempickups.config.components.name_suffix")
                .define("name_suffix", "");

        showRarityAsItemNameColor = builder
                .comment("Use the item's styled hover name so rarity controls the item name color.")
                .translation("improveditempickups.config.components.show_rarity_as_item_name_color")
                .define("show_rarity_as_item_name_color", true);

        backgroundOffsetX = defineOffset(builder, "background_offset_x", "Horizontal background image offset.", 0);
        backgroundOffsetY = defineOffset(builder, "background_offset_y", "Vertical background image offset.", 0);
        frameOffsetX = defineOffset(builder, "frame_offset_x", "Horizontal frame image offset.", 0);
        frameOffsetY = defineOffset(builder, "frame_offset_y", "Vertical frame image offset.", 0);
        iconOffsetX = defineOffset(builder, "icon_offset_x", "Horizontal item icon offset.", -12);
        iconOffsetY = defineOffset(builder, "icon_offset_y", "Vertical item icon offset.", -9);
        quantityOffsetX = defineOffset(builder, "quantity_offset_x", "Horizontal quantity text offset.", 8);
        quantityOffsetY = defineOffset(builder, "quantity_offset_y", "Vertical quantity text offset.", -4);
        nameOffsetX = defineOffset(builder, "name_offset_x", "Horizontal item name text offset.", 8);
        nameOffsetY = defineOffset(builder, "name_offset_y", "Vertical item name text offset.", -4);

        contentPaddingLeft = defineNonNegative(builder, "content_padding_left", "Left content padding.", 12);
        contentPaddingRight = defineNonNegative(builder, "content_padding_right", "Right content padding.", 12);
        contentPaddingTop = defineNonNegative(builder, "content_padding_top", "Top content padding.", 6);
        contentPaddingBottom = defineNonNegative(builder, "content_padding_bottom", "Bottom content padding.", 6);
        backgroundMinWidth = defineNonNegative(builder, "background_min_width", "Minimum adaptive background width.", 0);
        backgroundHeight = definePositive(builder, "background_height", "Background image height.", 42);
        frameWidth = builder
                .comment("Frame image width. Set to -1 to match the adaptive background width.")
                .translation("improveditempickups.config.components.frame_width")
                .defineInRange("frame_width", -1, -1, 4096);
        frameHeight = builder
                .comment("Frame image height. Set to -1 to match the background height.")
                .translation("improveditempickups.config.components.frame_height")
                .defineInRange("frame_height", -1, -1, 4096);

        backgroundColor = builder
                .comment("ARGB background image color in hexadecimal.")
                .translation("improveditempickups.config.components.background_color")
                .define("background_color", "F0100010");

        frameColor = builder
                .comment("ARGB frame image color in hexadecimal.")
                .translation("improveditempickups.config.components.frame_color")
                .define("frame_color", "E05000ff");

        quantityColor = builder
                .comment("Default RGB quantity text color.")
                .translation("improveditempickups.config.components.quantity_color")
                .defineInRange("quantity_color", 0xD0D0D0, 0, 0xFFFFFF);

        nameColor = builder
                .comment("Default RGB item name text color when rarity-based text color is disabled.")
                .translation("improveditempickups.config.components.name_color")
                .defineInRange("name_color", 0xD0D0D0, 0, 0xFFFFFF);

        builder.pop();
    }

    private static ModConfigSpec.IntValue defineOffset(ModConfigSpec.Builder builder, String name, String comment, int defaultValue) {
        return builder
                .comment(comment)
                .translation("improveditempickups.config.components." + name)
                .defineInRange(name, defaultValue, -4096, 4096);
    }

    private static ModConfigSpec.IntValue defineNonNegative(ModConfigSpec.Builder builder, String name, String comment, int defaultValue) {
        return builder
                .comment(comment)
                .translation("improveditempickups.config.components." + name)
                .defineInRange(name, defaultValue, 0, 4096);
    }

    private static ModConfigSpec.IntValue definePositive(ModConfigSpec.Builder builder, String name, String comment, int defaultValue) {
        return builder
                .comment(comment)
                .translation("improveditempickups.config.components." + name)
                .defineInRange(name, defaultValue, 1, 4096);
    }

    private static ModConfigSpec.DoubleValue defineDouble(ModConfigSpec.Builder builder, String name, String comment, double defaultValue, double minimum, double maximum) {
        return builder.comment(comment)
                .translation("improveditempickups.config.animation." + name)
                .defineInRange(name, defaultValue, minimum, maximum);
    }

    public static final class AnimationSettings {
        public final ModConfigSpec.IntValue inDurationTicks;
        public final ModConfigSpec.IntValue outDurationTicks;
        public final PhaseSettings in;
        public final PhaseSettings out;

        private AnimationSettings(ModConfigSpec.Builder builder) {
            inDurationTicks = builder.comment("Duration of the enter animation, in ticks.")
                    .translation("improveditempickups.config.animation.in_duration_ticks")
                    .defineInRange("in_duration_ticks", 4, 0, 1200);
            outDurationTicks = builder.comment("Duration of the exit animation, in ticks.")
                    .translation("improveditempickups.config.animation.out_duration_ticks")
                    .defineInRange("out_duration_ticks", 4, 0, 1200);
            in = new PhaseSettings(builder, "in");
            out = new PhaseSettings(builder, "out");
        }
    }

    public static final class PhaseSettings {
        public final ModConfigSpec.BooleanValue positionEnabled;
        public final ModConfigSpec.DoubleValue positionOffsetX;
        public final ModConfigSpec.DoubleValue positionOffsetY;
        public final ModConfigSpec.EnumValue<AnimationEase> positionEase;
        public final ModConfigSpec.BooleanValue rotationEnabled;
        public final ModConfigSpec.DoubleValue rotationDegrees;
        public final ModConfigSpec.EnumValue<AnimationEase> rotationEase;
        public final ModConfigSpec.BooleanValue scaleEnabled;
        public final ModConfigSpec.DoubleValue scaleX;
        public final ModConfigSpec.DoubleValue scaleY;
        public final ModConfigSpec.EnumValue<AnimationEase> scaleEase;
        public final ModConfigSpec.BooleanValue colorEnabled;
        public final ModConfigSpec.ConfigValue<String> colorStart;
        public final ModConfigSpec.ConfigValue<String> colorEnd;
        public final ModConfigSpec.EnumValue<AnimationEase> colorEase;

        private PhaseSettings(ModConfigSpec.Builder builder, String phase) {
            boolean entering = phase.equals("in");
            builder.push(phase);

            positionEnabled = builder.comment("Whether the position primitive is active during this phase.")
                    .translation("improveditempickups.config.animation." + phase + ".position_enabled")
                    .define("position_enabled", true);
            positionOffsetX = defineDouble(builder, phase + ".position_offset_x", "Horizontal position animation offset.", 50.0, -4096.0, 4096.0);
            positionOffsetY = defineDouble(builder, phase + ".position_offset_y", "Vertical position animation offset.", 0.0, -4096.0, 4096.0);
            positionEase = builder.comment("Easing used by the position primitive.")
                    .translation("improveditempickups.config.animation." + phase + ".position_ease")
                    .defineEnum("position_ease", entering ? AnimationEase.OUT_QUAD : AnimationEase.IN_QUAD);

            rotationEnabled = builder.comment("Whether the rotation primitive is active during this phase.")
                    .translation("improveditempickups.config.animation." + phase + ".rotation_enabled")
                    .define("rotation_enabled", false);
            rotationDegrees = defineDouble(builder, phase + ".rotation_degrees", "Rotation animation amount in degrees.", 0.0, -3600.0, 3600.0);
            rotationEase = builder.comment("Easing used by the rotation primitive.")
                    .translation("improveditempickups.config.animation." + phase + ".rotation_ease")
                    .defineEnum("rotation_ease", entering ? AnimationEase.OUT_QUAD : AnimationEase.IN_QUAD);

            scaleEnabled = builder.comment("Whether the scale primitive is active during this phase.")
                    .translation("improveditempickups.config.animation." + phase + ".scale_enabled")
                    .define("scale_enabled", false);
            scaleX = defineDouble(builder, phase + ".scale_x", "Horizontal scale animation target.", 1.0, 0.0, 100.0);
            scaleY = defineDouble(builder, phase + ".scale_y", "Vertical scale animation target.", 1.0, 0.0, 100.0);
            scaleEase = builder.comment("Easing used by the scale primitive.")
                    .translation("improveditempickups.config.animation." + phase + ".scale_ease")
                    .defineEnum("scale_ease", entering ? AnimationEase.OUT_QUAD : AnimationEase.IN_QUAD);

            colorEnabled = builder.comment("Whether the color primitive is active during this phase.")
                    .translation("improveditempickups.config.animation." + phase + ".color_enabled")
                    .define("color_enabled", true);
            colorStart = builder.comment("ARGB color at the start of this phase, in hexadecimal form.")
                    .translation("improveditempickups.config.animation." + phase + ".color_start")
                    .define("color_start", entering ? "00FFFFFF" : "FFFFFFFF");
            colorEnd = builder.comment("ARGB color at the end of this phase, in hexadecimal form.")
                    .translation("improveditempickups.config.animation." + phase + ".color_end")
                    .define("color_end", entering ? "FFFFFFFF" : "00FFFFFF");
            colorEase = builder.comment("Easing used by the color primitive.")
                    .translation("improveditempickups.config.animation." + phase + ".color_ease")
                    .defineEnum("color_ease", entering ? AnimationEase.IN_QUAD : AnimationEase.OUT_QUAD);

            builder.pop();
        }
    }

}
