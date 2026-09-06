package com.critmx.improveditempickups.client;

import com.critmx.improveditempickups.config.AnimationEase;
import com.critmx.improveditempickups.config.ImprovedItemPickupsConfig;
import com.critmx.improveditempickups.config.PositionPreset;
import com.critmx.improveditempickups.config.RepeatedPickupPolicy;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ModConfigMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return this::createScreen;
    }

    public Screen createScreen(Screen parent) {
        return YetAnotherConfigLib.create(ImprovedItemPickupsConfig.HANDLER, (defaults, config, builder) ->
                builder
                        .title(Component.translatable("improveditempickups.configuration.notifications"))

                        .category(ConfigCategory.createBuilder()
                                .name(Component.translatable("improveditempickups.configuration.notifications"))
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.notifications.notification_lifetime_ticks"))
                                        .binding(
                                                defaults.notificationLifetimeTicks,
                                                () -> config.notificationLifetimeTicks,
                                                value -> config.notificationLifetimeTicks = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(8, 1200)
                                                .step(1))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.notifications.max_active_notifications"))
                                        .binding(
                                                defaults.maxActiveNotifications,
                                                () -> config.maxActiveNotifications,
                                                value -> config.maxActiveNotifications = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(1, 100)
                                                .step(1))
                                        .build())
                                .option(Option.<RepeatedPickupPolicy>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.notifications.repeated_pickup_policy"))
                                        .binding(
                                                defaults.repeatedPickupPolicy,
                                                () -> config.repeatedPickupPolicy,
                                                value -> config.repeatedPickupPolicy = value
                                        )
                                        .controller(option -> EnumControllerBuilder.create(option)
                                                .enumClass(RepeatedPickupPolicy.class))
                                        .build())
                                .build())

                        .category(ConfigCategory.createBuilder()
                                .name(Component.translatable("improveditempickups.configuration.position"))
                                .option(Option.<PositionPreset>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.position.preset"))
                                        .binding(
                                                defaults.positionPreset,
                                                () -> config.positionPreset,
                                                value -> config.positionPreset = value
                                        )
                                        .controller(option -> EnumControllerBuilder.create(option)
                                                .enumClass(PositionPreset.class))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.position.offset_x"))
                                        .binding(
                                                defaults.positionOffsetX,
                                                () -> config.positionOffsetX,
                                                value -> config.positionOffsetX = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(-4096, 4096)
                                                .step(1))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.position.offset_y"))
                                        .binding(
                                                defaults.positionOffsetY,
                                                () -> config.positionOffsetY,
                                                value -> config.positionOffsetY = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(-4096, 4096)
                                                .step(1))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.position.notification_spacing"))
                                        .binding(
                                                defaults.notificationSpacing,
                                                () -> config.notificationSpacing,
                                                value -> config.notificationSpacing = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(0, 4096)
                                                .step(1))
                                        .build())
                                .build())

                        .category(ConfigCategory.createBuilder()
                                .name(Component.translatable("improveditempickups.configuration.animation"))

                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.animation.in_duration_ticks"))
                                        .binding(
                                                defaults.animation.inDurationTicks,
                                                () -> config.animation.inDurationTicks,
                                                value -> config.animation.inDurationTicks = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(0, 1200)
                                                .step(1))
                                        .build())

                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.animation.out_duration_ticks"))
                                        .binding(
                                                defaults.animation.outDurationTicks,
                                                () -> config.animation.outDurationTicks,
                                                value -> config.animation.outDurationTicks = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(0, 1200)
                                                .step(1))
                                        .build())

                                .group(OptionGroup.createBuilder()
                                        .name(Component.translatable("improveditempickups.configuration.in"))

                                        .option(Option.<Boolean>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.in.position_enabled"))
                                                .binding(
                                                        defaults.animation.in.positionEnabled,
                                                        () -> config.animation.in.positionEnabled,
                                                        value -> config.animation.in.positionEnabled = value
                                                )
                                                .controller(BooleanControllerBuilder::create)
                                                .build())

                                        .option(Option.<Float>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.in.position_offset_x"))
                                                .binding(
                                                        defaults.animation.in.positionOffsetX,
                                                        () -> config.animation.in.positionOffsetX,
                                                        value -> config.animation.in.positionOffsetX = value
                                                )
                                                .controller(option -> FloatSliderControllerBuilder.create(option)
                                                        .range(-4096.0f, 4096.0f)
                                                        .step(0.1f))
                                                .build())

                                        .option(Option.<Float>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.in.position_offset_y"))
                                                .binding(
                                                        defaults.animation.in.positionOffsetY,
                                                        () -> config.animation.in.positionOffsetY,
                                                        value -> config.animation.in.positionOffsetY = value
                                                )
                                                .controller(option -> FloatSliderControllerBuilder.create(option)
                                                        .range(-4096.0f, 4096.0f)
                                                        .step(0.1f))
                                                .build())

                                        .option(Option.<AnimationEase>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.in.position_ease"))
                                                .binding(
                                                        defaults.animation.in.positionEase,
                                                        () -> config.animation.in.positionEase,
                                                        value -> config.animation.in.positionEase = value
                                                )
                                                .controller(option -> EnumControllerBuilder.create(option)
                                                        .enumClass(AnimationEase.class))
                                                .build())

                                        .option(Option.<Boolean>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.in.rotation_enabled"))
                                                .binding(
                                                        defaults.animation.in.rotationEnabled,
                                                        () -> config.animation.in.rotationEnabled,
                                                        value -> config.animation.in.rotationEnabled = value
                                                )
                                                .controller(BooleanControllerBuilder::create)
                                                .build())

                                        .option(Option.<Float>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.in.rotation_degrees"))
                                                .binding(
                                                        defaults.animation.in.rotationDegrees,
                                                        () -> config.animation.in.rotationDegrees,
                                                        value -> config.animation.in.rotationDegrees = value
                                                )
                                                .controller(option -> FloatSliderControllerBuilder.create(option)
                                                        .range(-3600.0f, 3600.0f)
                                                        .step(1.0f))
                                                .build())

                                        .option(Option.<AnimationEase>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.in.rotation_ease"))
                                                .binding(
                                                        defaults.animation.in.rotationEase,
                                                        () -> config.animation.in.rotationEase,
                                                        value -> config.animation.in.rotationEase = value
                                                )
                                                .controller(option -> EnumControllerBuilder.create(option)
                                                        .enumClass(AnimationEase.class))
                                                .build())

                                        .option(Option.<Boolean>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.in.scale_enabled"))
                                                .binding(
                                                        defaults.animation.in.scaleEnabled,
                                                        () -> config.animation.in.scaleEnabled,
                                                        value -> config.animation.in.scaleEnabled = value
                                                )
                                                .controller(BooleanControllerBuilder::create)
                                                .build())

                                        .option(Option.<Float>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.in.scale_x"))
                                                .binding(
                                                        defaults.animation.in.scaleX,
                                                        () -> config.animation.in.scaleX,
                                                        value -> config.animation.in.scaleX = value
                                                )
                                                .controller(option -> FloatSliderControllerBuilder.create(option)
                                                        .range(0.0f, 100.0f)
                                                        .step(0.01f))
                                                .build())

                                        .option(Option.<Float>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.in.scale_y"))
                                                .binding(
                                                        defaults.animation.in.scaleY,
                                                        () -> config.animation.in.scaleY,
                                                        value -> config.animation.in.scaleY = value
                                                )
                                                .controller(option -> FloatSliderControllerBuilder.create(option)
                                                        .range(0.0f, 100.0f)
                                                        .step(0.01f))
                                                .build())

                                        .option(Option.<AnimationEase>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.in.scale_ease"))
                                                .binding(
                                                        defaults.animation.in.scaleEase,
                                                        () -> config.animation.in.scaleEase,
                                                        value -> config.animation.in.scaleEase = value
                                                )
                                                .controller(option -> EnumControllerBuilder.create(option)
                                                        .enumClass(AnimationEase.class))
                                                .build())

                                        .option(Option.<Boolean>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.in.color_enabled"))
                                                .binding(
                                                        defaults.animation.in.colorEnabled,
                                                        () -> config.animation.in.colorEnabled,
                                                        value -> config.animation.in.colorEnabled = value
                                                )
                                                .controller(BooleanControllerBuilder::create)
                                                .build())

                                        .option(Option.<String>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.in.color_start"))
                                                .binding(
                                                        defaults.animation.in.colorStart,
                                                        () -> config.animation.in.colorStart,
                                                        value -> config.animation.in.colorStart = value
                                                )
                                                .controller(StringControllerBuilder::create)
                                                .build())

                                        .option(Option.<String>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.in.color_end"))
                                                .binding(
                                                        defaults.animation.in.colorEnd,
                                                        () -> config.animation.in.colorEnd,
                                                        value -> config.animation.in.colorEnd = value
                                                )
                                                .controller(StringControllerBuilder::create)
                                                .build())

                                        .option(Option.<AnimationEase>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.in.color_ease"))
                                                .binding(
                                                        defaults.animation.in.colorEase,
                                                        () -> config.animation.in.colorEase,
                                                        value -> config.animation.in.colorEase = value
                                                )
                                                .controller(option -> EnumControllerBuilder.create(option)
                                                        .enumClass(AnimationEase.class))
                                                .build())

                                        .build())

                                .group(OptionGroup.createBuilder()
                                        .name(Component.translatable("improveditempickups.configuration.out"))

                                        .option(Option.<Boolean>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.out.position_enabled"))
                                                .binding(
                                                        defaults.animation.out.positionEnabled,
                                                        () -> config.animation.out.positionEnabled,
                                                        value -> config.animation.out.positionEnabled = value
                                                )
                                                .controller(BooleanControllerBuilder::create)
                                                .build())

                                        .option(Option.<Float>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.out.position_offset_x"))
                                                .binding(
                                                        defaults.animation.out.positionOffsetX,
                                                        () -> config.animation.out.positionOffsetX,
                                                        value -> config.animation.out.positionOffsetX = value
                                                )
                                                .controller(option -> FloatSliderControllerBuilder.create(option)
                                                        .range(-4096.0f, 4096.0f)
                                                        .step(0.1f))
                                                .build())

                                        .option(Option.<Float>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.out.position_offset_y"))
                                                .binding(
                                                        defaults.animation.out.positionOffsetY,
                                                        () -> config.animation.out.positionOffsetY,
                                                        value -> config.animation.out.positionOffsetY = value
                                                )
                                                .controller(option -> FloatSliderControllerBuilder.create(option)
                                                        .range(-4096.0f, 4096.0f)
                                                        .step(0.1f))
                                                .build())

                                        .option(Option.<AnimationEase>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.out.position_ease"))
                                                .binding(
                                                        defaults.animation.out.positionEase,
                                                        () -> config.animation.out.positionEase,
                                                        value -> config.animation.out.positionEase = value
                                                )
                                                .controller(option -> EnumControllerBuilder.create(option)
                                                        .enumClass(AnimationEase.class))
                                                .build())

                                        .option(Option.<Boolean>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.out.rotation_enabled"))
                                                .binding(
                                                        defaults.animation.out.rotationEnabled,
                                                        () -> config.animation.out.rotationEnabled,
                                                        value -> config.animation.out.rotationEnabled = value
                                                )
                                                .controller(BooleanControllerBuilder::create)
                                                .build())

                                        .option(Option.<Float>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.out.rotation_degrees"))
                                                .binding(
                                                        defaults.animation.out.rotationDegrees,
                                                        () -> config.animation.out.rotationDegrees,
                                                        value -> config.animation.out.rotationDegrees = value
                                                )
                                                .controller(option -> FloatSliderControllerBuilder.create(option)
                                                        .range(-3600.0f, 3600.0f)
                                                        .step(1.0f))
                                                .build())

                                        .option(Option.<AnimationEase>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.out.rotation_ease"))
                                                .binding(
                                                        defaults.animation.out.rotationEase,
                                                        () -> config.animation.out.rotationEase,
                                                        value -> config.animation.out.rotationEase = value
                                                )
                                                .controller(option -> EnumControllerBuilder.create(option)
                                                        .enumClass(AnimationEase.class))
                                                .build())

                                        .option(Option.<Boolean>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.out.scale_enabled"))
                                                .binding(
                                                        defaults.animation.out.scaleEnabled,
                                                        () -> config.animation.out.scaleEnabled,
                                                        value -> config.animation.out.scaleEnabled = value
                                                )
                                                .controller(BooleanControllerBuilder::create)
                                                .build())

                                        .option(Option.<Float>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.out.scale_x"))
                                                .binding(
                                                        defaults.animation.out.scaleX,
                                                        () -> config.animation.out.scaleX,
                                                        value -> config.animation.out.scaleX = value
                                                )
                                                .controller(option -> FloatSliderControllerBuilder.create(option)
                                                        .range(0.0f, 100.0f)
                                                        .step(0.01f))
                                                .build())

                                        .option(Option.<Float>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.out.scale_y"))
                                                .binding(
                                                        defaults.animation.out.scaleY,
                                                        () -> config.animation.out.scaleY,
                                                        value -> config.animation.out.scaleY = value
                                                )
                                                .controller(option -> FloatSliderControllerBuilder.create(option)
                                                        .range(0.0f, 100.0f)
                                                        .step(0.01f))
                                                .build())

                                        .option(Option.<AnimationEase>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.out.scale_ease"))
                                                .binding(
                                                        defaults.animation.out.scaleEase,
                                                        () -> config.animation.out.scaleEase,
                                                        value -> config.animation.out.scaleEase = value
                                                )
                                                .controller(option -> EnumControllerBuilder.create(option)
                                                        .enumClass(AnimationEase.class))
                                                .build())

                                        .option(Option.<Boolean>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.out.color_enabled"))
                                                .binding(
                                                        defaults.animation.out.colorEnabled,
                                                        () -> config.animation.out.colorEnabled,
                                                        value -> config.animation.out.colorEnabled = value
                                                )
                                                .controller(BooleanControllerBuilder::create)
                                                .build())

                                        .option(Option.<String>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.out.color_start"))
                                                .binding(
                                                        defaults.animation.out.colorStart,
                                                        () -> config.animation.out.colorStart,
                                                        value -> config.animation.out.colorStart = value
                                                )
                                                .controller(StringControllerBuilder::create)
                                                .build())

                                        .option(Option.<String>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.out.color_end"))
                                                .binding(
                                                        defaults.animation.out.colorEnd,
                                                        () -> config.animation.out.colorEnd,
                                                        value -> config.animation.out.colorEnd = value
                                                )
                                                .controller(StringControllerBuilder::create)
                                                .build())

                                        .option(Option.<AnimationEase>createBuilder()
                                                .name(Component.translatable("improveditempickups.config.animation.out.color_ease"))
                                                .binding(
                                                        defaults.animation.out.colorEase,
                                                        () -> config.animation.out.colorEase,
                                                        value -> config.animation.out.colorEase = value
                                                )
                                                .controller(option -> EnumControllerBuilder.create(option)
                                                        .enumClass(AnimationEase.class))
                                                .build())

                                        .build())

                                .build())

                        .category(ConfigCategory.createBuilder()
                                .name(Component.translatable("improveditempickups.configuration.components"))

                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.background_enabled"))
                                        .binding(
                                                defaults.backgroundEnabled,
                                                () -> config.backgroundEnabled,
                                                value -> config.backgroundEnabled = value
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.frame_enabled"))
                                        .binding(
                                                defaults.frameEnabled,
                                                () -> config.frameEnabled,
                                                value -> config.frameEnabled = value
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.icon_enabled"))
                                        .binding(
                                                defaults.iconEnabled,
                                                () -> config.iconEnabled,
                                                value -> config.iconEnabled = value
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.quantity_enabled"))
                                        .binding(
                                                defaults.quantityEnabled,
                                                () -> config.quantityEnabled,
                                                value -> config.quantityEnabled = value
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.name_enabled"))
                                        .binding(
                                                defaults.nameEnabled,
                                                () -> config.nameEnabled,
                                                value -> config.nameEnabled = value
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<String>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.quantity_prefix"))
                                        .binding(
                                                defaults.quantityPrefix,
                                                () -> config.quantityPrefix,
                                                value -> config.quantityPrefix = value
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.<String>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.quantity_suffix"))
                                        .binding(
                                                defaults.quantitySuffix,
                                                () -> config.quantitySuffix,
                                                value -> config.quantitySuffix = value
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.<String>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.name_prefix"))
                                        .binding(
                                                defaults.namePrefix,
                                                () -> config.namePrefix,
                                                value -> config.namePrefix = value
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.<String>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.name_suffix"))
                                        .binding(
                                                defaults.nameSuffix,
                                                () -> config.nameSuffix,
                                                value -> config.nameSuffix = value
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.show_rarity_as_item_name_color"))
                                        .binding(
                                                defaults.showRarityAsItemNameColor,
                                                () -> config.showRarityAsItemNameColor,
                                                value -> config.showRarityAsItemNameColor = value
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build())

                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.background_offset_x"))
                                        .binding(
                                                defaults.backgroundOffsetX,
                                                () -> config.backgroundOffsetX,
                                                value -> config.backgroundOffsetX = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(-4096, 4096)
                                                .step(1))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.background_offset_y"))
                                        .binding(
                                                defaults.backgroundOffsetY,
                                                () -> config.backgroundOffsetY,
                                                value -> config.backgroundOffsetY = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(-4096, 4096)
                                                .step(1))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.frame_offset_x"))
                                        .binding(
                                                defaults.frameOffsetX,
                                                () -> config.frameOffsetX,
                                                value -> config.frameOffsetX = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(-4096, 4096)
                                                .step(1))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.frame_offset_y"))
                                        .binding(
                                                defaults.frameOffsetY,
                                                () -> config.frameOffsetY,
                                                value -> config.frameOffsetY = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(-4096, 4096)
                                                .step(1))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.icon_offset_x"))
                                        .binding(
                                                defaults.iconOffsetX,
                                                () -> config.iconOffsetX,
                                                value -> config.iconOffsetX = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(-4096, 4096)
                                                .step(1))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.icon_offset_y"))
                                        .binding(
                                                defaults.iconOffsetY,
                                                () -> config.iconOffsetY,
                                                value -> config.iconOffsetY = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(-4096, 4096)
                                                .step(1))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.quantity_offset_x"))
                                        .binding(
                                                defaults.quantityOffsetX,
                                                () -> config.quantityOffsetX,
                                                value -> config.quantityOffsetX = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(-4096, 4096)
                                                .step(1))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.quantity_offset_y"))
                                        .binding(
                                                defaults.quantityOffsetY,
                                                () -> config.quantityOffsetY,
                                                value -> config.quantityOffsetY = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(-4096, 4096)
                                                .step(1))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.name_offset_x"))
                                        .binding(
                                                defaults.nameOffsetX,
                                                () -> config.nameOffsetX,
                                                value -> config.nameOffsetX = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(-4096, 4096)
                                                .step(1))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.name_offset_y"))
                                        .binding(
                                                defaults.nameOffsetY,
                                                () -> config.nameOffsetY,
                                                value -> config.nameOffsetY = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(-4096, 4096)
                                                .step(1))
                                        .build())

                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.content_padding_left"))
                                        .binding(
                                                defaults.contentPaddingLeft,
                                                () -> config.contentPaddingLeft,
                                                value -> config.contentPaddingLeft = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(0, 4096)
                                                .step(1))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.content_padding_right"))
                                        .binding(
                                                defaults.contentPaddingRight,
                                                () -> config.contentPaddingRight,
                                                value -> config.contentPaddingRight = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(0, 4096)
                                                .step(1))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.content_padding_top"))
                                        .binding(
                                                defaults.contentPaddingTop,
                                                () -> config.contentPaddingTop,
                                                value -> config.contentPaddingTop = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(0, 4096)
                                                .step(1))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.content_padding_bottom"))
                                        .binding(
                                                defaults.contentPaddingBottom,
                                                () -> config.contentPaddingBottom,
                                                value -> config.contentPaddingBottom = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(0, 4096)
                                                .step(1))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.background_min_width"))
                                        .binding(
                                                defaults.backgroundMinWidth,
                                                () -> config.backgroundMinWidth,
                                                value -> config.backgroundMinWidth = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(0, 4096)
                                                .step(1))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.background_height"))
                                        .binding(
                                                defaults.backgroundHeight,
                                                () -> config.backgroundHeight,
                                                value -> config.backgroundHeight = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(1, 4096)
                                                .step(1))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.frame_width"))
                                        .binding(
                                                defaults.frameWidth,
                                                () -> config.frameWidth,
                                                value -> config.frameWidth = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(-1, 4096)
                                                .step(1))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.frame_height"))
                                        .binding(
                                                defaults.frameHeight,
                                                () -> config.frameHeight,
                                                value -> config.frameHeight = value
                                        )
                                        .controller(option -> IntegerSliderControllerBuilder.create(option)
                                                .range(-1, 4096)
                                                .step(1))
                                        .build())

                                .option(Option.<String>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.background_color"))
                                        .binding(
                                                defaults.backgroundColor,
                                                () -> config.backgroundColor,
                                                value -> config.backgroundColor = value
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.<String>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.frame_color"))
                                        .binding(
                                                defaults.frameColor,
                                                () -> config.frameColor,
                                                value -> config.frameColor = value
                                        )
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.quantity_color"))
                                        .binding(
                                                defaults.quantityColor,
                                                () -> config.quantityColor,
                                                value -> config.quantityColor = value
                                        )
                                        .controller(IntegerFieldControllerBuilder::create)
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.translatable("improveditempickups.config.components.name_color"))
                                        .binding(
                                                defaults.nameColor,
                                                () -> config.nameColor,
                                                value -> config.nameColor = value
                                        )
                                        .controller(IntegerFieldControllerBuilder::create)
                                        .build())

                                .build())
        ).generateScreen(parent);
    }
}
