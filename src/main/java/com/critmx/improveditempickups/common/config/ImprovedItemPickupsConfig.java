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
    }

    public enum RepeatedPickupPolicy {
        MERGE,
        SEPARATE
    }
}
