package dev.cryptic.lodestonejs.client;

import dev.cryptic.lodestonejs.LodestoneJSPlugin;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import team.lodestar.lodestone.systems.particle.world.type.LodestoneWorldParticleType;

@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class LodestoneJSClientSetup {
    @SubscribeEvent
    public static void registerParticleFactory(RegisterParticleProvidersEvent event) {
        LodestoneJSPlugin.PARTICLE_TYPE_REGISTRY.objects.values().forEach(b -> {
            event.registerSpriteSet(b.get(), LodestoneWorldParticleType.Factory::new);
        });
    }
}
