package dev.cryptic.lodestonejs.kubejs;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.handlers.WorldEventHandler;
import team.lodestar.lodestone.registry.common.LodestoneWorldEventTypes;
import team.lodestar.lodestone.systems.worldevent.WorldEventType;

public class LodestoneJSUtils {

    @Info("Spawns the specified world event")
    public static void spawnWorldEvent(String id, Level level) {
        ResourceLocation eventID = ResourceLocation.tryParse(id);
        if (eventID == null) return;
        WorldEventType type = LodestoneWorldEventTypes.WORLD_EVENT_TYPE_REGISTRY.get(eventID);
        spawnWorldEvent(type, level);
    }

    @Info("Spawns the specified world event")
    public static void spawnWorldEvent(WorldEventType type, Level level) {
        if (type == null) return;
        WorldEventHandler.addWorldEvent(level, type.supplier.getInstance());
    }
}
