package dev.cryptic.lodestonejs.kubejs.events;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

public class LodestoneJSRegistryEvents {
    public static final EventGroup GROUP = EventGroup.of("LodestoneRegistryEvents");

    //public static EventHandler OBJ_MODEL_REGISTRY = GROUP.client("objModel", () -> OBJModelRegistryEventJS.class);


    public static void init() {
        final IEventBus forgeEventBus = NeoForge.EVENT_BUS;

        final IEventBus modEventBus = ModLoadingContext.get().getActiveContainer().getEventBus();

        modEventBus.addListener(LodestoneJSRegistryEvents::clientSetup);
    }

    private static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
//            if (OBJ_MODEL_REGISTRY.hasListeners()) {
//                OBJ_MODEL_REGISTRY.post(new OBJModelRegistryEventJS());
//            }
        });
    }
}
