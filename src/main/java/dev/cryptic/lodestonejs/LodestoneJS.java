package dev.cryptic.lodestonejs;

import com.mojang.logging.LogUtils;
import dev.cryptic.lodestonejs.kubejs.events.LodestoneJSEvents;
import dev.cryptic.lodestonejs.kubejs.events.LodestoneJSRegistryEvents;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(LodestoneJS.MOD_ID)
public class LodestoneJS {
    public static final String MOD_ID = "lodestonejs";
    public static final Logger LOGGER = LogUtils.getLogger();

    public LodestoneJS() {
        IEventBus modEventBus = ModLoadingContext.get().getActiveContainer().getEventBus();
        IEventBus neoForgeEventBus = NeoForge.EVENT_BUS;
        //neoForgeEventBus.register(this);

        LodestoneJSEvents.initEvents();
        LodestoneJSRegistryEvents.init();

    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
