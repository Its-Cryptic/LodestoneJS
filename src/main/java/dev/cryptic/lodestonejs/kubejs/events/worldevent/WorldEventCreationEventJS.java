package dev.cryptic.lodestonejs.kubejs.events.worldevent;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.events.types.worldevent.WorldEventCreationEvent;
import team.lodestar.lodestone.systems.worldevent.WorldEventInstance;

@SuppressWarnings("unused")
public class WorldEventCreationEventJS extends WorldEventLevelEventJS {
    private final WorldEventCreationEvent event;
    public WorldEventCreationEventJS(WorldEventCreationEvent worldEvent) {
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
            Get the level the world event instance is in
            @returns {Level} The level
            """)
    @Override
    public Level getLevel() {
        return event.getLevel();
    }
}
