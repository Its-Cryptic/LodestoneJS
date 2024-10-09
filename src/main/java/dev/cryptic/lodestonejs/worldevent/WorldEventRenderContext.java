package dev.cryptic.lodestonejs.worldevent;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.nbt.CompoundTag;

public record WorldEventRenderContext(WorldEventInstanceData data, PoseStack poseStack, MultiBufferSource bufferSource, float partialTicks) {
}
