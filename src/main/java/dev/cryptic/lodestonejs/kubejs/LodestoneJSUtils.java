package dev.cryptic.lodestonejs.kubejs;

import dev.cryptic.lodestonejs.worldevent.CustomWorldEvent;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.handlers.WorldEventHandler;
import team.lodestar.lodestone.registry.common.LodestoneWorldEventTypes;
import team.lodestar.lodestone.systems.worldevent.WorldEventInstance;
import team.lodestar.lodestone.systems.worldevent.WorldEventType;

import java.util.function.Consumer;

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

    @Info("Spawns the specified world event with a consumer for custom data")
    public static void spawnWorldEvent(String id, Level level, Consumer<WorldEventInstance> instanceConsumer) {
        ResourceLocation eventID = ResourceLocation.tryParse(id);
        if (eventID == null) return;
        WorldEventType type = LodestoneWorldEventTypes.WORLD_EVENT_TYPE_REGISTRY.get(eventID);
        spawnWorldEvent(type, level, instanceConsumer);
    }

    @Info("Spawns the specified world event with a consumer for custom data")
    public static void spawnWorldEvent(WorldEventType type, Level level, Consumer<WorldEventInstance> instanceConsumer) {
        if (type == null) return;
        WorldEventInstance worldEvent = type.supplier.getInstance();
        if (worldEvent instanceof CustomWorldEvent customInstance) {
            instanceConsumer.accept(customInstance);
        }
        WorldEventHandler.addWorldEvent(level, worldEvent);
    }

}
