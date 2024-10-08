package dev.cryptic.lodestonejs.kubejs.events.worldevent;

import dev.latvian.mods.kubejs.event.KubeEvent;
import team.lodestar.lodestone.systems.worldevent.WorldEventInstance;

public abstract class WorldEventInstanceEventJS implements KubeEvent {
    public abstract WorldEventInstance getWorldEventInstance();
}
