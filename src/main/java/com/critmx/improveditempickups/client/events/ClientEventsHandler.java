package com.critmx.improveditempickups.client.events;

import com.critmx.improveditempickups.ImprovedItemPickups;
import com.critmx.improveditempickups.client.ClientSession;
import com.critmx.improveditempickups.client.gui.PickupNotificationsRenderer;
import com.critmx.improveditempickups.common.logic.PickupTrackerManager;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = ImprovedItemPickups.MODID, value = Dist.CLIENT)
public class ClientEventsHandler {
    private static ClientSession clientSession;

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        var modContainer = event.getContainer();
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, (mc, parent) -> new ConfigurationScreen(modContainer, parent));
    }

    @SubscribeEvent
    public static void onClientJoin(ClientPlayerNetworkEvent.LoggingIn event) {
        clientSession = new ClientSession(event.getPlayer());
    }

    @SubscribeEvent
    public static void onClientLeave(ClientPlayerNetworkEvent.LoggingOut event) {
        PickupTrackerManager.discardTracker();
        if (clientSession != null) {
            clientSession.close();
            clientSession = null;
        }
    }

    @SubscribeEvent
    public static void onClientPlayerTick(PlayerTickEvent.Post event) {
        var level = event.getEntity().level();
        if (!level.isClientSide()) {
            return;
        }
        PickupTrackerManager.tick(level.getGameTime());

        if (clientSession != null) {
            clientSession.tick();
        }
    }

    @SubscribeEvent
    public static void registerGuiLayers(RegisterGuiLayersEvent event) {
        event.registerAboveAll(Identifier.fromNamespaceAndPath(ImprovedItemPickups.MODID, PickupNotificationsRenderer.GUI_LAYER_ID), PickupNotificationsRenderer.INSTANCE);
    }
}
