package dev.cryptic.lodestonejs.kubejs.builder;

import dev.latvian.mods.kubejs.registry.BuilderBase;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import team.lodestar.lodestone.systems.particle.world.type.LodestoneWorldParticleType;

@SuppressWarnings({"unused", "rawtypes"})
public class WorldParticleTypeBuilderJS extends BuilderBase<ParticleType> {
    public WorldParticleTypeBuilderJS(ResourceLocation i) {
        super(i);
    }

    @Override
    public RegistryInfo getRegistryType() {
        return RegistryInfo.PARTICLE_TYPE;
    }

    @Override
    public ParticleType createObject() {
        return new LodestoneWorldParticleType();
    }
}
