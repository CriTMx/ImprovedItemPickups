package com.critmx.improveditempickups.client.events;

import com.critmx.improveditempickups.ImprovedItemPickups;
import com.critmx.improveditempickups.client.ClientSession;
import com.critmx.improveditempickups.client.PickupAggregationPresenter;
import com.critmx.improveditempickups.common.logic.PickupTrackerManager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = ImprovedItemPickups.MODID)
public class ClientEventsHandler {
    private static ClientSession clientSession;

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
    }
}
