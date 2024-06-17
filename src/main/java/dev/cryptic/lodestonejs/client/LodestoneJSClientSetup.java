package dev.cryptic.lodestonejs.client;

import dev.cryptic.lodestonejs.LodestoneJSPlugin;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import team.lodestar.lodestone.systems.particle.world.type.LodestoneWorldParticleType;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LodestoneJSClientSetup {
    @SubscribeEvent
    public static void registerParticleFactory(RegisterParticleProvidersEvent event) {
        LodestoneJSPlugin.PARTICLE_TYPE_REGISTRY.objects.values().forEach(b -> {
            event.registerSpriteSet(b.get(), LodestoneWorldParticleType.Factory::new);
        });
    }
}
