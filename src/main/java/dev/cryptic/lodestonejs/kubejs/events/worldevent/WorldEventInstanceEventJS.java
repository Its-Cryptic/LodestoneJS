package dev.cryptic.lodestonejs.kubejs.events.worldevent;

import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.level.LevelEventJS;
import team.lodestar.lodestone.systems.worldevent.WorldEventInstance;

public abstract class WorldEventInstanceEventJS extends EventJS {
    public abstract WorldEventInstance getWorldEventInstance();
}
