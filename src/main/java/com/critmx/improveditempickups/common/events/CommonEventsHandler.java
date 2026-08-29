package com.critmx.improveditempickups.common.events;

import com.critmx.improveditempickups.ImprovedItemPickups;
import com.critmx.improveditempickups.common.logic.PickupTrackerManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;

import java.util.List;

@EventBusSubscriber(modid = ImprovedItemPickups.MODID)
public class CommonEventsHandler {

    @SubscribeEvent
    public static void onItemEntityPickup(ItemEntityPickupEvent.Post event) {
//        Player player = event.getPlayer();
//        ItemStack stackRemaining = event.getCurrentStack();
//        ItemStack stackOriginal = event.getOriginalStack();
//        MutableComponent prefix = Component.literal("Item picked up: ");
//        MutableComponent itemCount = Component.literal(String.format("x%d", stackOriginal.getCount()));
//        MutableComponent itemName = (MutableComponent) stackOriginal.getStyledHoverName();
//        MutableComponent finalMsg = prefix.append(itemCount).append(" ").append(itemName);
//
//        player.sendSystemMessage(finalMsg);

        try {
            PickupTrackerManager.onPickup(event.getOriginalStack(), event.getPlayer().level().getGameTime());
        } catch (Exception ex) {
            ImprovedItemPickups.LOGGER.error("[{}] Exception caught for onItemEntityPickup: {}", ImprovedItemPickups.MODID, ex.getMessage());
        }
    }
}
