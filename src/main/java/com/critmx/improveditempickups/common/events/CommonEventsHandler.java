package com.critmx.improveditempickups.common.events;

import com.critmx.improveditempickups.ImprovedItemPickups;
import com.critmx.improveditempickups.common.logic.PickupTrackerManager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;

import java.util.List;

@EventBusSubscriber(modid = ImprovedItemPickups.MODID)
public class CommonEventsHandler {

    @SubscribeEvent
    public static void onItemEntityPickup(ItemEntityPickupEvent.Post event) {
//        try {
//            PickupTrackerManager.onPickup(event.getOriginalStack(), event.getPlayer().level().getGameTime());
//        } catch (Exception ex) {
//            ImprovedItemPickups.LOGGER.error("[{}] Exception caught for onItemEntityPickup: {}", ImprovedItemPickups.MODID, ex.getMessage());
//        }
    }
}
