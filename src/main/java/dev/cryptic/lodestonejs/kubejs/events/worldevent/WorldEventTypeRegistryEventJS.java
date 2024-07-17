package dev.cryptic.lodestonejs.kubejs.events.worldevent;

import dev.cryptic.lodestonejs.LodestoneJS;
import dev.cryptic.lodestonejs.test.TestWorldEvent;
import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.event.StartupEventJS;
import net.minecraft.resources.ResourceLocation;
import team.lodestar.lodestone.events.types.worldevent.WorldEventTypeRegistryEvent;
import team.lodestar.lodestone.registry.common.LodestoneWorldEventTypeRegistry;
import team.lodestar.lodestone.systems.worldevent.WorldEventType;

import java.util.function.Supplier;

public class WorldEventTypeRegistryEventJS extends StartupEventJS {
    public WorldEventTypeRegistryEventJS() {
    }

    public WorldEventType create(String id) {
        ResourceLocation rl = ResourceLocation.tryParse(id);
        return create(rl, TestWorldEvent::new);
    }

    private WorldEventType create(ResourceLocation id, WorldEventType.EventInstanceSupplier instanceSupplier) {
        LodestoneJS.LOGGER.info("Registering world event type: " + id);
        WorldEventType worldEventType = new WorldEventType(id, instanceSupplier);
        LodestoneWorldEventTypeRegistry.registerEventType(worldEventType);
        return worldEventType;
    }
}
