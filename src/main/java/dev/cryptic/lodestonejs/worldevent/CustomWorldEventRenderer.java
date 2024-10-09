package dev.cryptic.lodestonejs.worldevent;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import team.lodestar.lodestone.systems.worldevent.WorldEventRenderer;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class CustomWorldEventRenderer extends WorldEventRenderer<CustomWorldEvent> {
    private final Consumer<WorldEventRenderContext> renderConsumer;
    private final Predicate<WorldEventInstanceData> shouldRenderPredicate;

    public CustomWorldEventRenderer(Consumer<WorldEventRenderContext> renderConsumer, Predicate<WorldEventInstanceData> shouldRenderPredicate) {
        this.renderConsumer = renderConsumer;
        this.shouldRenderPredicate = shouldRenderPredicate;
    }

    @Override
    public void render(CustomWorldEvent instance, PoseStack poseStack, MultiBufferSource bufferSource, float partialTicks) {
        renderConsumer.accept(new WorldEventRenderContext(instance.getData(), poseStack, bufferSource, partialTicks));
    }

    @Override
    public boolean canRender(CustomWorldEvent instance) {
        return shouldRenderPredicate.test(instance.getData());
    }

    public static class Builder {
        private Consumer<WorldEventRenderContext> renderConsumer = (context) -> {};
        private Predicate<WorldEventInstanceData> shouldRenderPredicate = (data) -> true;

        public Builder() {
        }

        public void render(Consumer<WorldEventRenderContext> renderConsumer) {
            this.renderConsumer = renderConsumer;
        }

        public void shouldRender(Predicate<WorldEventInstanceData> shouldRenderPredicate) {
            this.shouldRenderPredicate = shouldRenderPredicate;
        }

        public CustomWorldEventRenderer build() {
            return new CustomWorldEventRenderer(renderConsumer, shouldRenderPredicate);
        }

    }
}
