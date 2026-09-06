package com.critmx.improveditempickups.logic.pickup;

import net.minecraft.world.entity.player.Player;

import java.util.Collection;

public record PickupAggregation(
        Player player,
        Collection<PickupEntry> entries
) {}
