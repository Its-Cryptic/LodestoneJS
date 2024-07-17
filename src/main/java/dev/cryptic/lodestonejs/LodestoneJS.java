package dev.cryptic.lodestonejs;

import com.mojang.logging.LogUtils;
import dev.cryptic.lodestonejs.kubejs.events.LodestoneJSEvents;
import dev.cryptic.lodestonejs.kubejs.events.LodestoneJSRegistryEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(LodestoneJS.MOD_ID)
public class LodestoneJS {
    public static final String MOD_ID = "lodestonejs";
    public static final Logger LOGGER = LogUtils.getLogger();

    public LodestoneJS() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        LodestoneJSEvents.initEvents();
        LodestoneJSRegistryEvents.init();

    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
