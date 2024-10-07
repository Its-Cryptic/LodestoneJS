package dev.cryptic.lodestonejs.kubejs.worldevent;

import dev.latvian.mods.kubejs.registry.BuilderBase;
import net.minecraft.resources.ResourceLocation;
import team.lodestar.lodestone.systems.worldevent.WorldEventInstance;
import team.lodestar.lodestone.systems.worldevent.WorldEventType;

public class CustomWorldEventInstance extends WorldEventInstance {

    public CustomWorldEventInstance() {
        super();
    }
    public CustomWorldEventInstance(Builder builder) {
        super(builder.worldEventType);
    }

    public static class Builder extends BuilderBase<CustomWorldEventInstance> {
        private final ResourceLocation worldEventId;
        private WorldEventType worldEventType;

        public Builder(ResourceLocation resourceLocation) {
            super(resourceLocation);
            this.worldEventId = resourceLocation;
        }

        @Override
        public RegistryInfo getRegistryType() {
            return null;
        }

        @Override
        public CustomWorldEventInstance createObject() {
            return new CustomWorldEventInstance(this);
        }
    }

}
