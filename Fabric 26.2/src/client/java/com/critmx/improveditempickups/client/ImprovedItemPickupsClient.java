package com.critmx.improveditempickups.client;

import com.critmx.improveditempickups.ImprovedItemPickups;
import com.critmx.improveditempickups.client.events.ClientEventsHandler;
import com.critmx.improveditempickups.client.gui.PickupNotificationsRenderer;
import com.critmx.improveditempickups.config.ImprovedItemPickupsConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;

public class ImprovedItemPickupsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        ImprovedItemPickupsConfig.initializeClientConfig();
        ClientEventsHandler.register();
        HudElementRegistry.addFirst(ImprovedItemPickups.id(PickupNotificationsRenderer.GUI_LAYER_ID), PickupNotificationsRenderer.INSTANCE);
	}
}