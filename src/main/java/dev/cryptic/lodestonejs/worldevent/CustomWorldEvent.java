package dev.cryptic.lodestonejs.worldevent;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.registry.common.LodestoneWorldEventTypes;
import team.lodestar.lodestone.systems.worldevent.WorldEventInstance;
import team.lodestar.lodestone.systems.worldevent.WorldEventType;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CustomWorldEvent extends WorldEventInstance implements WorldEventInstanceData {
    private Consumer<Level> tickConsumer;
    private Consumer<WorldEventInstanceData> defaultDataConsumer;
    private BiConsumer<WorldEventInstanceData, CompoundTag> addSaveDataConsumer;
    private BiConsumer<WorldEventInstanceData, CompoundTag> readSaveDataConsumer;
    public CustomWorldEvent(ResourceLocation id, Consumer<Level> tickConsumer, Consumer<WorldEventInstanceData> defaultDataConsumer, BiConsumer<WorldEventInstanceData,CompoundTag> addConsumer, BiConsumer<WorldEventInstanceData, CompoundTag> readConsumer) {
        this(LodestoneWorldEventTypes.WORLD_EVENT_TYPE_REGISTRY.get(id));
        this.tickConsumer = tickConsumer;
        this.defaultDataConsumer = defaultDataConsumer;
        this.addSaveDataConsumer = addConsumer;
        this.readSaveDataConsumer = readConsumer;
    }
    public CustomWorldEvent(WorldEventType type) {
        super(type);
    }

    @Override
    public void tick(Level level) {
        if (this.tickConsumer != null) this.tickConsumer.accept(level);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        if (this.addSaveDataConsumer != null) this.addSaveDataConsumer.accept(this, tag);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if (this.readSaveDataConsumer != null) this.readSaveDataConsumer.accept(this, tag);
    }


    public static class Builder {
        private final ResourceLocation id;
        private Consumer<Level> tickConsumer;
        private Consumer<WorldEventInstanceData> createDataConsumer;
        private BiConsumer<WorldEventInstanceData, CompoundTag> addSaveDataConsumer;
        private BiConsumer<WorldEventInstanceData, CompoundTag> readSaveDataConsumer;

        public Builder(ResourceLocation id) {
            this.id = id;
        }

        public void onTick(Consumer<Level> tickConsumer) {
            this.tickConsumer = tickConsumer;
        }

        public CustomWorldEvent build() {
            return new CustomWorldEvent(id, tickConsumer, createDataConsumer, addSaveDataConsumer, readSaveDataConsumer);
        }
    }
}
