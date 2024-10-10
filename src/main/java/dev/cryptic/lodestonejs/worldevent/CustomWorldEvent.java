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
    private final WorldEventInstanceData data;
    private BiConsumer<CustomWorldEvent, Level> tickConsumer;
    private Consumer<WorldEventInstanceData> createDataConsumer;
    private BiConsumer<WorldEventInstanceData, CompoundTag> writeSaveDataConsumer, readSaveDataConsumer;
    public CustomWorldEvent(ResourceLocation id) {
        this(LodestoneWorldEventTypes.WORLD_EVENT_TYPE_REGISTRY.get(id));
    }
    public CustomWorldEvent(WorldEventType type) {
        super(type);
        this.data = new WorldEventInstanceData();
    }

    @Override
    public void tick(Level level) {
        if (this.tickConsumer != null) this.tickConsumer.accept(this, level);
    }

    public void createAdditionalSaveData() {
        if (this.createDataConsumer != null) this.createDataConsumer.accept(this.data);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        if (this.writeSaveDataConsumer != null) this.writeSaveDataConsumer.accept(this.data, tag);
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
        protected BiConsumer<CustomWorldEvent, Level> tickConsumer = (instance, level) -> {};
        protected Consumer<WorldEventInstanceData> createDataConsumer = data -> {};
        protected BiConsumer<WorldEventInstanceData, CompoundTag> writeSaveDataConsumer, readSaveDataConsumer = (data, tag) -> {};

        public Builder(ResourceLocation id) {
            this.id = id;
        }

        public CustomWorldEvent build() {
            CustomWorldEvent instance = new CustomWorldEvent(id);
            instance.tickConsumer = tickConsumer;
            instance.createDataConsumer = createDataConsumer;
            instance.writeSaveDataConsumer = writeSaveDataConsumer;
            instance.readSaveDataConsumer = readSaveDataConsumer;
            instance.createAdditionalSaveData(); // Ensures the event instance holds default values to prevent issues
            return instance;
        }
    }
}
