package dev.cryptic.lodestonejs.kubejs.events.worldevent;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.client.renderer.MultiBufferSource;
import team.lodestar.lodestone.events.types.worldevent.WorldEventRenderEvent;
import team.lodestar.lodestone.systems.worldevent.WorldEventInstance;
import team.lodestar.lodestone.systems.worldevent.WorldEventRenderer;

public class WorldEventRenderEventJS extends WorldEventInstanceEventJS {
    private final WorldEventRenderEvent event;
    public WorldEventRenderEventJS(WorldEventRenderEvent worldEvent) {
        this.event = worldEvent;
    }

    @Info(value = """
            Get the world event instance
            @returns {WorldEventInstance} The world event instance
            """)
    @Override
    public WorldEventInstance getWorldEventInstance() {
        return event.getWorldEvent();
    }

    @Info(value = """
            Get the world event renderer
            @returns {WorldEventRenderer} The world event renderer
            """)
    public WorldEventRenderer<WorldEventInstance> getRenderer() {
        return event.getRenderer();
    }

    @Info(value = """
            Get the pose stack
            @returns {PoseStack} The pose stack
            """)
    public PoseStack getPoseStack() {
        return event.getPoseStack();
    }

    @Info(value = """
            Get the multi buffer source
            @returns {MultiBufferSource} The multi buffer source
            """)
    public MultiBufferSource getMultiBufferSource() {
        return event.getMultiBufferSource();
    }

    @Info(value = """
            Get the partial ticks
            @returns {number} The partial ticks
            """)
    public float getPartialTicks() {
        return event.getPartialTicks();
    }

}
