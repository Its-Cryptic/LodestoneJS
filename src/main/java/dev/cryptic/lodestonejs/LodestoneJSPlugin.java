package dev.cryptic.lodestonejs;

import dev.cryptic.lodestonejs.kubejs.LodestoneJSBinding;
//import dev.cryptic.lodestonejs.kubejs.builder.WorldParticleTypeBuilderJS;
import dev.cryptic.lodestonejs.kubejs.LodestoneJSUtils;
import dev.cryptic.lodestonejs.kubejs.events.LodestoneJSEvents;
import dev.cryptic.lodestonejs.kubejs.events.LodestoneJSRegistryEvents;
import dev.cryptic.lodestonejs.worldevent.WorldEventInstanceData;
import dev.cryptic.lodestonejs.worldevent.WorldEventTypeBuilder;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.registry.BuilderTypeRegistry;
//import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import team.lodestar.lodestone.LodestoneLib;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypes;
import team.lodestar.lodestone.registry.common.LodestoneWorldEventTypes;
import team.lodestar.lodestone.systems.model.obj.lod.LODStrategy;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;
import team.lodestar.lodestone.systems.rendering.rendeertype.RenderTypeToken;
import team.lodestar.lodestone.systems.worldevent.WorldEventInstance;
import team.lodestar.lodestone.systems.worldevent.WorldEventType;

public class LodestoneJSPlugin implements KubeJSPlugin {
    //public static final RegistryInfo<ParticleType> PARTICLE_TYPE_REGISTRY = RegistryInfo.of(Registries.PARTICLE_TYPE, ParticleType.class);


    @Override
    public void init() {
        LodestoneJS.LOGGER.info("LodestoneJS is initializing...");
        //PARTICLE_TYPE_REGISTRY.addType("lodestone:world", WorldParticleTypeBuilderJS.class, WorldParticleTypeBuilderJS::new);
    }

    @Override
    public void registerBuilderTypes(BuilderTypeRegistry registry) {
        registry.addDefault(LodestoneWorldEventTypes.WORLD_EVENT_TYPE_KEY, WorldEventTypeBuilder.class, WorldEventTypeBuilder::new);
    }

    @Override
    public void registerBindings(BindingRegistry event) {
        event.add("LodestoneJS", LodestoneJSBinding.class);
        event.add("LODStrategy", LODStrategy.class);
        event.add("WorldEventTypeBuilder", WorldEventTypeBuilder.class);
        event.add("WorldEventType", WorldEventType.class);
        event.add("WorldEventInstance", WorldEventInstance.class);
        event.add("WorldEventInstanceData", WorldEventInstanceData.class);
        event.add("LodestoneJSUtils", LodestoneJSUtils.class);
        event.add("VFXBuilders", VFXBuilders.class);
        event.add("LodestoneRenderTypes", LodestoneRenderTypes.class);
        event.add("RenderTypeToken", RenderTypeToken.class);
        event.add("LodestoneLib", LodestoneLib.class);
        //event.add("WorldVFXBuilder", VFXBuilders.WorldVFXBuilder.class);
        //event.add("LodestoneRenderTypeRegistry", LodestoneRenderTypeRegistry.class);
    }
}
