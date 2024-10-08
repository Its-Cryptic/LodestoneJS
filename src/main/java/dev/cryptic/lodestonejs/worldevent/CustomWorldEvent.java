package dev.cryptic.lodestonejs.worldevent;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.registry.common.LodestoneWorldEventTypes;
import team.lodestar.lodestone.systems.worldevent.WorldEventInstance;
import team.lodestar.lodestone.systems.worldevent.WorldEventType;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CustomWorldEvent extends WorldEventInstance {
    private WorldEventInstanceData data;
    private BiConsumer<CustomWorldEvent, Level> tickConsumer;
    private Consumer<WorldEventInstanceData> defaultDataConsumer;
    private BiConsumer<WorldEventInstanceData, CompoundTag> addSaveDataConsumer;
    private BiConsumer<WorldEventInstanceData, CompoundTag> readSaveDataConsumer;
    public CustomWorldEvent(ResourceLocation id, BiConsumer<CustomWorldEvent, Level> tickConsumer, Consumer<WorldEventInstanceData> defaultDataConsumer, BiConsumer<WorldEventInstanceData,CompoundTag> addConsumer, BiConsumer<WorldEventInstanceData, CompoundTag> readConsumer) {
        this(LodestoneWorldEventTypes.WORLD_EVENT_TYPE_REGISTRY.get(id));
        this.data = new WorldEventInstanceData();
        this.tickConsumer = tickConsumer;
        this.defaultDataConsumer = defaultDataConsumer;
        this.addSaveDataConsumer = addConsumer;
        this.readSaveDataConsumer = readConsumer;
        this.createAdditionalSaveData();
    }
    public CustomWorldEvent(WorldEventType type) {
        super(type);
        this.data = new WorldEventInstanceData();
        this.createAdditionalSaveData();
    }

    @Override
    public void tick(Level level) {
        if (this.tickConsumer != null) this.tickConsumer.accept(this, level);
    }

    public void createAdditionalSaveData() {
        if (this.defaultDataConsumer != null) this.defaultDataConsumer.accept(this.data);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        if (this.addSaveDataConsumer != null) this.addSaveDataConsumer.accept(this.data, tag);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if (this.readSaveDataConsumer != null) this.readSaveDataConsumer.accept(this.data, tag);
    }

    @Info("Gets the instance data")
    public WorldEventInstanceData getData() {
        return this.data;
    }


    public static class Builder {
        private final ResourceLocation id;
        private BiConsumer<CustomWorldEvent, Level> tickConsumer;
        private Consumer<WorldEventInstanceData> createDataConsumer;
        private BiConsumer<WorldEventInstanceData, CompoundTag> addSaveDataConsumer, readSaveDataConsumer;

        public Builder(ResourceLocation id) {
            this.id = id;
        }

        public void onTick(BiConsumer<CustomWorldEvent, Level> tickConsumer) {
            this.tickConsumer = tickConsumer;
        }

        public void createInstanceData(Consumer<WorldEventInstanceData> createDataConsumer) {
            this.createDataConsumer = createDataConsumer;
        }

        public void writeSaveData(BiConsumer<WorldEventInstanceData, CompoundTag> addSaveDataConsumer) {
            this.addSaveDataConsumer = addSaveDataConsumer;
        }

        public void readSaveData(BiConsumer<WorldEventInstanceData, CompoundTag> readSaveDataConsumer) {
            this.readSaveDataConsumer = readSaveDataConsumer;
        }

        public CustomWorldEvent build() {
            return new CustomWorldEvent(id, tickConsumer, createDataConsumer, addSaveDataConsumer, readSaveDataConsumer);
        }
    }
}
