package dev.cryptic.lodestonejs.kubejs.events;

import dev.cryptic.lodestonejs.kubejs.events.worldevent.*;
import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import dev.latvian.mods.kubejs.event.Extra;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.common.MinecraftForge;
import team.lodestar.lodestone.events.types.worldevent.*;
import team.lodestar.lodestone.registry.common.LodestoneWorldEventTypeRegistry;
import team.lodestar.lodestone.systems.worldevent.WorldEventType;

public class LodestoneJSEvents {
    public static final EventGroup GROUP = EventGroup.of("LodestoneEvents");
    public static final EventGroup WORLD_EVENTS = EventGroup.of("LodestoneWorldEvents");
    private static final Extra SUPPORTS_WORLD_EVENT_TYPE = new Extra().transformer(LodestoneJSEvents::transformWorldEventType).identity().describeType(context -> context.javaType(WorldEventType.class));

    private static WorldEventType transformWorldEventType(Object o) {
        if (o == null || o instanceof WorldEventType) return (WorldEventType) o;

        ResourceLocation id = ResourceLocation.tryParse(o.toString());
        return id == null ? null : LodestoneWorldEventTypeRegistry.EVENT_TYPES.get(id);
    }

    public static final EventHandler WORLD_EVENT_SPAWN = WORLD_EVENTS.server("spawn", () -> WorldEventCreationEventJS.class).extra(SUPPORTS_WORLD_EVENT_TYPE);
    public static final EventHandler WORLD_EVENT_DISCARD = WORLD_EVENTS.server("discard", () -> WorldEventDiscardEventJS.class).extra(SUPPORTS_WORLD_EVENT_TYPE);
    public static final EventHandler WORLD_EVENT_TICK = WORLD_EVENTS.server("tick", () -> WorldEventTickEventJS.class).extra(SUPPORTS_WORLD_EVENT_TYPE);
    public static final EventHandler WORLD_EVENT_RENDER = WORLD_EVENTS.client("render", () -> WorldEventRenderEventJS.class).extra(SUPPORTS_WORLD_EVENT_TYPE);

    public static final EventHandler RENDER_LEVEL_STAGE = GROUP.client("renderLevelStageEvent", () -> RenderLevelStageEventJS.class);

    public static void worldEventSpawn(WorldEventCreationEvent event) {
        if (WORLD_EVENT_SPAWN.hasListeners()) {
            WORLD_EVENT_SPAWN.post(new WorldEventCreationEventJS(event), getType(event));
        }

    }

    public static void worldEventEnd(WorldEventDiscardEvent event) {
        if (WORLD_EVENT_DISCARD.hasListeners()) {
            WORLD_EVENT_DISCARD.post(new WorldEventDiscardEventJS(event), getType(event));
        }
    }

    public static void worldEventTick(WorldEventTickEvent event) {
        if (WORLD_EVENT_TICK.hasListeners()) {
            WORLD_EVENT_TICK.post(new WorldEventTickEventJS(event), getType(event));
        }
    }

    public static void worldEventRender(WorldEventRenderEvent event) {
        if (WORLD_EVENT_RENDER.hasListeners()) {
            WORLD_EVENT_RENDER.post(new WorldEventRenderEventJS(event), getType(event));
        }
    }

    public static void renderLevelStage(RenderLevelStageEvent event) {
        if (RENDER_LEVEL_STAGE.hasListeners()) {
            RENDER_LEVEL_STAGE.post(new RenderLevelStageEventJS(event));
        }
    }

    public static void initEvents() {
        MinecraftForge.EVENT_BUS.addListener(LodestoneJSEvents::worldEventSpawn);
        MinecraftForge.EVENT_BUS.addListener(LodestoneJSEvents::worldEventEnd);
        MinecraftForge.EVENT_BUS.addListener(LodestoneJSEvents::worldEventTick);
        MinecraftForge.EVENT_BUS.addListener(LodestoneJSEvents::worldEventRender);
        MinecraftForge.EVENT_BUS.addListener(LodestoneJSEvents::renderLevelStage);
    }

    private static WorldEventType getType(WorldEventInstanceEvent event) {
        return event.getWorldEvent().type;
    }
}
