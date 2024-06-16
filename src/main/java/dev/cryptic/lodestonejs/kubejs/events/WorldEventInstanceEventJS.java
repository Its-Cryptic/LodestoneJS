package dev.cryptic.lodestonejs.kubejs.events;

import dev.latvian.mods.kubejs.level.LevelEventJS;
import team.lodestar.lodestone.systems.worldevent.WorldEventInstance;

public abstract class WorldEventInstanceEventJS extends LevelEventJS {
    public abstract WorldEventInstance getWorldEventInstance();
}
