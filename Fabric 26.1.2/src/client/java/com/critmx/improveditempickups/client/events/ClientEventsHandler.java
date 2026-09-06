package com.critmx.improveditempickups.client.events;

import com.critmx.improveditempickups.client.ClientSession;
import com.critmx.improveditempickups.logic.PickupTrackerManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;

public class ClientEventsHandler {
    private static ClientSession clientSession;

    public static void register() {
        ClientPlayConnectionEvents.JOIN.register(ClientEventsHandler::onClientJoin);
        ClientPlayConnectionEvents.DISCONNECT.register(ClientEventsHandler::onClientLeave);
        ClientTickEvents.END_CLIENT_TICK.register(ClientEventsHandler::onClientTick);
    }

    private static void onClientJoin(ClientPacketListener clientPacketListener, PacketSender packetSender, Minecraft minecraft) {
        if (clientSession == null) {
            clientSession = new ClientSession(minecraft.player);
        }
    }

    private static void onClientLeave(ClientPacketListener clientPacketListener, Minecraft minecraft) {
        PickupTrackerManager.discardTracker();
        if (clientSession != null) {
            clientSession.close();
            clientSession = null;
        }
    }

    private static void onClientTick(Minecraft minecraft) {
        var level = minecraft.level; // always clientside
        if (level == null) {
            return;
        }

        PickupTrackerManager.tick(level.getGameTime());

        if (clientSession != null) {
            clientSession.tick();
        }
    }
}
