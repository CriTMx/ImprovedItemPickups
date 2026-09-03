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
                .define("background_color", "f3100010");

        frameColor = builder
                .comment("ARGB frame image color in hexadecimal.")
                .translation("improveditempickups.config.components.frame_color")
                .define("frame_color", "80c16ff3");

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

    private static ModConfigSpec.IntValue defineOffset(
            ModConfigSpec.Builder builder,
            String name,
            String comment,
            int defaultValue
    ) {
        return builder
                .comment(comment)
                .translation("improveditempickups.config.components." + name)
                .defineInRange(name, defaultValue, -4096, 4096);
    }

    private static ModConfigSpec.IntValue defineNonNegative(
            ModConfigSpec.Builder builder,
            String name,
            String comment,
            int defaultValue
    ) {
        return builder
                .comment(comment)
                .translation("improveditempickups.config.components." + name)
                .defineInRange(name, defaultValue, 0, 4096);
    }

    private static ModConfigSpec.IntValue definePositive(
            ModConfigSpec.Builder builder,
            String name,
            String comment,
            int defaultValue
    ) {
        return builder
                .comment(comment)
                .translation("improveditempickups.config.components." + name)
                .defineInRange(name, defaultValue, 1, 4096);
    }

    public enum RepeatedPickupPolicy {
        MERGE,
        SEPARATE
    }
}
