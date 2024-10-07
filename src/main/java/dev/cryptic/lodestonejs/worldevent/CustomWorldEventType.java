package dev.cryptic.lodestonejs.worldevent;

import dev.latvian.mods.kubejs.registry.BuilderBase;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.systems.worldevent.WorldEventInstance;
import team.lodestar.lodestone.systems.worldevent.WorldEventType;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CustomWorldEventType extends WorldEventType {
    public CustomWorldEventType(ResourceLocation id, EventInstanceSupplier<?> supplier, boolean clientSynced) {
        super(id, supplier, clientSynced);
    }

    public static class Builder extends BuilderBase<CustomWorldEventType> {
        private CustomWorldEvent.Builder instanceBuilder;
        private boolean isClientSynced;

        public Builder(ResourceLocation id) {
            super(id);
            this.instanceBuilder = new CustomWorldEvent.Builder(id);
        }

        public void isClientSynced() {
            this.isClientSynced = true;
        }

        public void onTick(Consumer<Level> tickConsumer) {
            this.instanceBuilder.onTick(tickConsumer);
        }


        @Override
        public CustomWorldEventType createObject() {
            return new CustomWorldEventType(id, instanceBuilder::build, isClientSynced);
        }
    }
}
