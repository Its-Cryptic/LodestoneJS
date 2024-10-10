package dev.cryptic.lodestonejs.worldevent;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import team.lodestar.lodestone.systems.worldevent.WorldEventRenderer;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class CustomWorldEventRenderer extends WorldEventRenderer<CustomWorldEvent> {
    protected Consumer<WorldEventRenderContext> renderConsumer = (context) -> {};
    protected Predicate<WorldEventInstanceData> shouldRenderPredicate = (data) -> true;

    @Override
    public void render(CustomWorldEvent instance, PoseStack poseStack, MultiBufferSource bufferSource, float partialTicks) {
        renderConsumer.accept(new WorldEventRenderContext(instance.getData(), poseStack, bufferSource, partialTicks));
    }

    @Override
    public boolean canRender(CustomWorldEvent instance) {
        return shouldRenderPredicate.test(instance.getData());
    }
}
