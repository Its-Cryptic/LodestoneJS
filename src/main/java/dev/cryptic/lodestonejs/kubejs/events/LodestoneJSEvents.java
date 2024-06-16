package dev.cryptic.lodestonejs.kubejs.events;

import dev.cryptic.lodestonejs.LodestoneJS;
import dev.latvian.mods.kubejs.bindings.event.LevelEvents;
import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import dev.latvian.mods.kubejs.event.Extra;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import team.lodestar.lodestone.events.types.worldevent.WorldEventCreationEvent;
import team.lodestar.lodestone.events.types.worldevent.WorldEventDiscardEvent;
import team.lodestar.lodestone.events.types.worldevent.WorldEventTickEvent;
import team.lodestar.lodestone.registry.common.LodestoneWorldEventTypeRegistry;
import team.lodestar.lodestone.systems.worldevent.WorldEventType;

public class LodestoneJSEvents {
    public static final EventGroup GROUP = EventGroup.of("LodestoneEvents");

    private static final Extra SUPPORTS_WORLD_EVENT_TYPE = new Extra().transformer(LodestoneJSEvents::transformWorldEventType).identity().describeType(context -> context.javaType(WorldEventType.class));

    private static Object transformWorldEventType(Object o) {
        if (o == null || o instanceof WorldEventType) return o;

        ResourceLocation id = ResourceLocation.tryParse(o.toString());
        LodestoneJS.LOGGER.info("Transforming WorldEventType: {}", id);
        WorldEventType type = id == null ? null : LodestoneWorldEventTypeRegistry.EVENT_TYPES.get(id);
        if (type == null) {
            LodestoneJS.LOGGER.error("Failed to transform WorldEventType: {}", id);
        } else {
            LodestoneJS.LOGGER.info("Successfully transformed WorldEventType: {}", id);
        }
        return type;
    }

    public static final EventHandler worldEventSpawn = GROUP.server("worldEventSpawn", () -> WorldEventCreationEventJS.class).extra(SUPPORTS_WORLD_EVENT_TYPE).hasResult();
    public static final EventHandler worldEventEnd = GROUP.server("worldEventDiscard", () -> WorldEventDiscardEventJS.class).extra(SUPPORTS_WORLD_EVENT_TYPE).hasResult();
    public static final EventHandler worldEventTick = GROUP.server("worldEventTick", () -> WorldEventTickEventJS.class).extra(SUPPORTS_WORLD_EVENT_TYPE).hasResult();
    //public static final EventHandler worldEventRender = GROUP.client("worldEventRender", () -> WorldEventRenderEventJS.class).extra(SUPPORTS_WORLD_EVENT_TYPE).hasResult();

    public static void worldEventSpawn(WorldEventCreationEvent event) {
        if (worldEventSpawn.hasListeners()) {
            worldEventSpawn.post(new WorldEventCreationEventJS(event));
        }
    }

    public static void worldEventEnd(WorldEventDiscardEvent event) {
        if (worldEventEnd.hasListeners()) {
            worldEventEnd.post(new WorldEventDiscardEventJS(event));
        }
    }

    public static void worldEventTick(WorldEventTickEvent event) {
        if (worldEventTick.hasListeners()) {
            worldEventTick.post(new WorldEventTickEventJS(event));
        }
    }

//    public static void worldEventRender(WorldEventCreationEvent event) {
//
//    }

    public static void initEvents() {
        MinecraftForge.EVENT_BUS.addListener(LodestoneJSEvents::worldEventSpawn);
        MinecraftForge.EVENT_BUS.addListener(LodestoneJSEvents::worldEventEnd);
        MinecraftForge.EVENT_BUS.addListener(LodestoneJSEvents::worldEventTick);
        //MinecraftForge.EVENT_BUS.addListener(LodestoneJSEvents::worldEventRender);
    }

}
