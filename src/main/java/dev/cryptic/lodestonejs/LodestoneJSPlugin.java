package dev.cryptic.lodestonejs;

import dev.cryptic.lodestonejs.kubejs.LodestoneJSBinding;
import dev.cryptic.lodestonejs.kubejs.builder.WorldParticleTypeBuilderJS;
import dev.cryptic.lodestonejs.kubejs.events.LodestoneJSEvents;
import dev.cryptic.lodestonejs.kubejs.events.LodestoneJSRegistryEvents;
import dev.cryptic.lodestonejs.worldevent.CustomWorldEventType;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.registry.BuilderTypeRegistry;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import team.lodestar.lodestone.registry.common.LodestoneWorldEventTypes;
import team.lodestar.lodestone.systems.model.obj.lod.LODStrategy;

@SuppressWarnings("rawtypes")
public class LodestoneJSPlugin implements KubeJSPlugin {
    public static final RegistryInfo<ParticleType> PARTICLE_TYPE_REGISTRY = RegistryInfo.of(Registries.PARTICLE_TYPE, ParticleType.class);

    @Override
    public void registerBuilderTypes(BuilderTypeRegistry registry) {
        registry.addDefault(LodestoneWorldEventTypes.WORLD_EVENT_TYPE_KEY, CustomWorldEventType.Builder.class, CustomWorldEventType.Builder::new);
    }

    @Override
    public void init() {
        PARTICLE_TYPE_REGISTRY.addType("lodestone:world", WorldParticleTypeBuilderJS.class, WorldParticleTypeBuilderJS::new);
    }

    @Override
    public void registerBindings(BindingRegistry event) {
        event.add("LodestoneJS", LodestoneJSBinding.class);
        event.add("LODStrategy", LODStrategy.class);
        //event.add("LodestoneRenderTypeRegistry", LodestoneRenderTypeRegistry.class);
    }

    @Override
    public void registerEvents() {
        LodestoneJSEvents.GROUP.register();
        LodestoneJSEvents.WORLD_EVENTS.register();
        LodestoneJSRegistryEvents.GROUP.register();
    }
}
