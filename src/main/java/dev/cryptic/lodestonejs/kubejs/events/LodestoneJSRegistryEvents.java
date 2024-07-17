package dev.cryptic.lodestonejs.kubejs.events;

import dev.cryptic.lodestonejs.kubejs.events.obj.OBJModelRegistryEventJS;
import dev.cryptic.lodestonejs.kubejs.events.worldevent.WorldEventTypeRegistryEventJS;
import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class LodestoneJSRegistryEvents {
    public static final EventGroup GROUP = EventGroup.of("LodestoneRegistryEvents");

    public static EventHandler WORLD_EVENT_TYPE_REGISTRY = GROUP.startup("worldEventType", () -> WorldEventTypeRegistryEventJS.class);
    public static EventHandler OBJ_MODEL_REGISTRY = GROUP.client("objModel", () -> OBJModelRegistryEventJS.class);


    public static void init() {
        final IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(LodestoneJSRegistryEvents::loadComplete);
        modEventBus.addListener(LodestoneJSRegistryEvents::clientSetup);
    }
    private static void loadComplete(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            if (WORLD_EVENT_TYPE_REGISTRY.hasListeners()) {
                WORLD_EVENT_TYPE_REGISTRY.post(new WorldEventTypeRegistryEventJS());
            }
        });
    }

    private static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            if (OBJ_MODEL_REGISTRY.hasListeners()) {
                OBJ_MODEL_REGISTRY.post(new OBJModelRegistryEventJS());
            }
        });
    }
}
