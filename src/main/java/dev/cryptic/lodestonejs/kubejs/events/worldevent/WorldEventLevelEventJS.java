package dev.cryptic.lodestonejs.kubejs.events.worldevent;

import net.minecraft.world.level.Level;

public abstract class WorldEventLevelEventJS extends WorldEventInstanceEventJS {
    public abstract Level getLevel();
}
