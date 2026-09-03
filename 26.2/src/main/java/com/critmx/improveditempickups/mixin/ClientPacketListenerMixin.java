package com.critmx.improveditempickups.mixin;

import com.critmx.improveditempickups.common.logic.PickupTrackerManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundTakeItemEntityPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
    @Inject(method = "handleTakeItemEntity", at = @At("HEAD"))
    private void onTakeItemEntity(ClientboundTakeItemEntityPacket packet, CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.isSameThread()) {
            return;
        }

        if (mc.level == null || mc.player == null) {
            return;
        }

        if (packet.getPlayerId() != mc.player.getId()) {
            return;
        }
        Entity entity = mc.level.getEntity(packet.getItemId());

        if (!(entity instanceof ItemEntity itemEntity)) {
            return;
        }

        ItemStack stack = itemEntity.getItem().copy();
        stack.setCount(packet.getAmount());

        PickupTrackerManager.onPickup(stack, mc.level.getGameTime());
    }
}
