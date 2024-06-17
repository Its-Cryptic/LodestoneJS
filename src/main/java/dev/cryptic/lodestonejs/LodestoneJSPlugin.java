package dev.cryptic.lodestonejs;

import dev.cryptic.lodestonejs.kubejs.LodestoneJSBinding;
import dev.cryptic.lodestonejs.kubejs.builder.WorldParticleTypeBuilderJS;
import dev.cryptic.lodestonejs.kubejs.events.LodestoneJSEvents;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;

public class LodestoneJSPlugin extends KubeJSPlugin {
    //public static final RegistryInfo<WorldEventType> WORLD_EVENT_TYPE_REGISTRY = RegistryInfo.of(LodestoneWorldEventTypeRegistry.EVENT_TYPES, WorldEventType.class);

    public static final RegistryInfo<ParticleType> PARTICLE_TYPE_REGISTRY = RegistryInfo.of(Registries.PARTICLE_TYPE, ParticleType.class);

    @Override
    public void init() {
        PARTICLE_TYPE_REGISTRY.addType("lodestone:world", WorldParticleTypeBuilderJS.class, WorldParticleTypeBuilderJS::new);
    }

    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("LodestoneJS", LodestoneJSBinding.class);
    }

    @Override
    public void registerEvents() {
        LodestoneJSEvents.GROUP.register();
        LodestoneJSEvents.WORLD_EVENTS_GROUP.register();
    }
}
