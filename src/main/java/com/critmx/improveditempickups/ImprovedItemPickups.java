package com.critmx.improveditempickups;

import com.critmx.improveditempickups.common.logic.PickupTrackerManager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(ImprovedItemPickups.MODID)
public class ImprovedItemPickups {
    public static final String MODID = "improveditempickups";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ImprovedItemPickups(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        NeoForge.EVENT_BUS.register(this);
    }

    private void clientSetup(FMLClientSetupEvent event) {
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    private void onServerStarting(ServerStartingEvent event) {

    }
}
