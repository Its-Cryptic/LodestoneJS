package dev.cryptic.lodestonejs.test;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import team.lodestar.lodestone.registry.client.LodestoneWorldEventRendererRegistry;
import team.lodestar.lodestone.systems.worldevent.WorldEventRenderer;
import team.lodestar.lodestone.systems.worldevent.WorldEventType;

import java.util.ArrayList;
import java.util.List;

public class WorldEventRenderers {
    private static final List<WorldEventRenderHolder> renderers = new ArrayList<>();
//    public static final WorldEventRenderHolder TEST_RENDERER = register(TestWorldEventTypes.TEST, new TestWorldEventRenderer());

    private static WorldEventRenderHolder register(WorldEventType type, WorldEventRenderer<?> renderer) {
        WorldEventRenderHolder pair = new WorldEventRenderHolder(type, renderer);
        renderers.add(pair);
        return pair;
    }
    public static void init(FMLClientSetupEvent event) {
        renderers.forEach(pair -> LodestoneWorldEventRendererRegistry.registerRenderer(pair.type, pair.renderer));
    }

    public record WorldEventRenderHolder(WorldEventType type, WorldEventRenderer<?> renderer) {
    }
}
