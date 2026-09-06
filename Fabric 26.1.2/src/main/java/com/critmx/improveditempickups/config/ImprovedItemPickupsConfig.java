package com.critmx.improveditempickups.config;

import com.critmx.improveditempickups.ImprovedItemPickups;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;

public final class ImprovedItemPickupsConfig {
    public static final ConfigClassHandler<ImprovedItemPickupsConfig> HANDLER =
            ConfigClassHandler.createBuilder(ImprovedItemPickupsConfig.class)
            .id(Identifier.fromNamespaceAndPath(ImprovedItemPickups.MOD_ID, "client_config"))
            .serializer(config ->
                    GsonConfigSerializerBuilder.create(config).setPath(
                            FabricLoader.getInstance().getConfigDir().resolve(ImprovedItemPickups.MOD_ID + ".json5")
                    ).setJson5(true).build()
            )
            .build();

    public static ImprovedItemPickupsConfig CLIENT_CONFIG;

    public static void initializeClientConfig() {
        HANDLER.load();
        CLIENT_CONFIG = HANDLER.instance();
    }

    @SerialEntry(value = "notification_lifetime_ticks", comment = "How long a pickup notification remains visible, in ticks.")
    public int notificationLifetimeTicks = 60;

    @SerialEntry(value = "max_active_notifications", comment = "Maximum number of pickup notifications shown at once.")
    public int maxActiveNotifications = 5;

    @SerialEntry(value = "global_scale", comment = "Size of pickup notifications. (Applies on top of GUI scale)")
    public float globalScale = 1.0f;

    @SerialEntry(value = "repeated_pickup_policy", comment = "How repeated pickups of the same item are presented.")
    public RepeatedPickupPolicy repeatedPickupPolicy = RepeatedPickupPolicy.MERGE;

    @SerialEntry(value = "position_preset", comment = "Preset anchor used for the notification stack.")
    public PositionPreset positionPreset = PositionPreset.HOTBAR_RIGHT;

    @SerialEntry(value = "position_offset_x", comment = "Horizontal offset applied after the preset anchor.")
    public int positionOffsetX = 0;

    @SerialEntry(value = "position_offset_y", comment = "Vertical offset applied after the preset anchor.")
    public int positionOffsetY = 0;

    @SerialEntry(value = "notification_spacing", comment = "Vertical distance between stacked notifications.")
    public int notificationSpacing = 28;

    @SerialEntry
    public AnimationSettings animation = new AnimationSettings();

    @SerialEntry(value = "background_enabled", comment = "Whether the notification background image is rendered.")
    public boolean backgroundEnabled = true;

    @SerialEntry(value = "frame_enabled", comment = "Whether the notification frame image is rendered.")
    public boolean frameEnabled = true;

    @SerialEntry(value = "icon_enabled", comment = "Whether the picked-up item icon is rendered.")
    public boolean iconEnabled = true;

    @SerialEntry(value = "quantity_enabled", comment = "Whether the picked-up quantity is rendered.")
    public boolean quantityEnabled = true;

    @SerialEntry(value = "name_enabled", comment = "Whether the picked-up item name is rendered.")
    public boolean nameEnabled = true;

    @SerialEntry(value = "quantity_prefix", comment = "Literal text placed before the picked-up quantity.")
    public String quantityPrefix = "x";

    @SerialEntry(value = "quantity_suffix", comment = "Literal text placed after the picked-up quantity.")
    public String quantitySuffix = " ";

    @SerialEntry(value = "name_prefix", comment = "Literal text placed before the item name.")
    public String namePrefix = "";

    @SerialEntry(value = "name_suffix", comment = "Literal text placed after the item name.")
    public String nameSuffix = "";

    @SerialEntry(value = "show_rarity_as_item_name_color", comment = "Use the item's styled hover name so rarity controls the item name color.")
    public boolean showRarityAsItemNameColor = true;

    @SerialEntry(value = "background_offset_x", comment = "Horizontal background image offset.")
    public int backgroundOffsetX = 0;

    @SerialEntry(value = "background_offset_y", comment = "Vertical background image offset.")
    public int backgroundOffsetY = 0;

    @SerialEntry(value = "frame_offset_x", comment = "Horizontal frame image offset.")
    public int frameOffsetX = 0;

    @SerialEntry(value = "frame_offset_y", comment = "Vertical frame image offset.")
    public int frameOffsetY = 0;

    @SerialEntry(value = "icon_offset_x", comment = "Horizontal item icon offset.")
    public int iconOffsetX = -12;

    @SerialEntry(value = "icon_offset_y", comment = "Vertical item icon offset.")
    public int iconOffsetY = -9;

    @SerialEntry(value = "quantity_offset_x", comment = "Horizontal quantity text offset.")
    public int quantityOffsetX = 8;

    @SerialEntry(value = "quantity_offset_y", comment = "Vertical quantity text offset.")
    public int quantityOffsetY = -4;

    @SerialEntry(value = "name_offset_x", comment = "Horizontal item name text offset."
    ) public int nameOffsetX = 8;

    @SerialEntry(value = "name_offset_y", comment = "Vertical item name text offset.")
    public int nameOffsetY = -4;

    @SerialEntry(value = "content_padding_left", comment = "Left content padding.")
    public int contentPaddingLeft = 12;

    @SerialEntry(value = "content_padding_right", comment = "Right content padding.")
    public int contentPaddingRight = 12;

    @SerialEntry(value = "content_padding_top", comment = "Top content padding.")
    public int contentPaddingTop = 6;

    @SerialEntry(value = "content_padding_bottom", comment = "Bottom content padding.")
    public int contentPaddingBottom = 6;

    @SerialEntry(value = "background_min_width", comment = "Minimum adaptive background width.")
    public int backgroundMinWidth = 0;

    @SerialEntry(value = "background_height", comment = "Background image height.")
    public int backgroundHeight = 42;

    @SerialEntry(value = "frame_width", comment = "Frame image width. Set to -1 to match the adaptive background width.")
    public int frameWidth = -1;

    @SerialEntry(value = "frame_height", comment = "Frame image height. Set to -1 to match the background height.")
    public int frameHeight = -1;

    @SerialEntry(value = "background_color", comment = "ARGB background image color in hexadecimal.")
    public String backgroundColor = "F0100010";

    @SerialEntry(value = "frame_color", comment = "ARGB frame image color in hexadecimal.")
    public String frameColor = "E05000ff";

    @SerialEntry(value = "quantity_color", comment = "Default RGB quantity text color.")
    public int quantityColor = 0xD0D0D0;

    @SerialEntry(value = "name_color", comment = "Default RGB item name text color when rarity-based text color is disabled.")
    public int nameColor = 0xD0D0D0;

    public ImprovedItemPickupsConfig() {
    }

    public static final class AnimationSettings {
        public int inDurationTicks = 4;
        public int outDurationTicks = 4;
        public PhaseSettings in = new PhaseSettings(
                true,
                50.0f,
                0.0f,
                AnimationEase.OUT_QUAD,
                false,
                0.0f,
                AnimationEase.OUT_QUAD,
                false,
                1.0f,
                1.0f,
                AnimationEase.OUT_QUAD,
                true,
                "00FFFFFF",
                "FFFFFFFF",
                AnimationEase.IN_QUAD
        );

        public PhaseSettings out = new PhaseSettings(
                true,
                50.0f,
                0.0f,
                AnimationEase.IN_QUAD,
                false,
                0.0f,
                AnimationEase.IN_QUAD,
                false,
                1.0f,
                1.0f,
                AnimationEase.IN_QUAD,
                true,
                "FFFFFFFF",
                "00FFFFFF",
                AnimationEase.OUT_QUAD
        );
    }

    public static final class PhaseSettings {
        public boolean positionEnabled;
        public float positionOffsetX;
        public float positionOffsetY;
        public AnimationEase positionEase;

        public boolean rotationEnabled;
        public float rotationDegrees;
        public AnimationEase rotationEase;

        public boolean scaleEnabled;
        public float scaleX;
        public float scaleY;
        public AnimationEase scaleEase;

        public boolean colorEnabled;
        public String colorStart;
        public String colorEnd;
        public AnimationEase colorEase;

        private PhaseSettings(
                boolean positionEnabled,
                float positionOffsetX,
                float positionOffsetY,
                AnimationEase positionEase,
                boolean rotationEnabled,
                float rotationDegrees,
                AnimationEase rotationEase,
                boolean scaleEnabled,
                float scaleX,
                float scaleY,
                AnimationEase scaleEase,
                boolean colorEnabled,
                String colorStart,
                String colorEnd,
                AnimationEase colorEase
        ) {
            this.positionEnabled = positionEnabled;
            this.positionOffsetX = positionOffsetX;
            this.positionOffsetY = positionOffsetY;
            this.positionEase = positionEase;

            this.rotationEnabled = rotationEnabled;
            this.rotationDegrees = rotationDegrees;
            this.rotationEase = rotationEase;

            this.scaleEnabled = scaleEnabled;
            this.scaleX = scaleX;
            this.scaleY = scaleY;
            this.scaleEase = scaleEase;

            this.colorEnabled = colorEnabled;
            this.colorStart = colorStart;
            this.colorEnd = colorEnd;
            this.colorEase = colorEase;
        }
    }

}
