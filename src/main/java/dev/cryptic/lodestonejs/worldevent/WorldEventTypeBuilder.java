package dev.cryptic.lodestonejs.worldevent;

import dev.cryptic.lodestonejs.LodestoneJS;
import dev.latvian.mods.kubejs.registry.BuilderBase;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.systems.worldevent.WorldEventType;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
@SuppressWarnings("unused")
public class WorldEventTypeBuilder extends BuilderBase<WorldEventType> {
    private final CustomWorldEvent.Builder instanceBuilder;
    private boolean isClientSynced;

    public WorldEventTypeBuilder(ResourceLocation id) {
        super(id);
        this.instanceBuilder = new CustomWorldEvent.Builder(id);
    }

    @Info("Makes the event also run on the client")
    public WorldEventTypeBuilder isClientSynced() {
        this.isClientSynced = true;
        LodestoneJS.LOGGER.info("WorldEventTypeBuilder.isClientSynced() called");
        return this;
    }

    @Info("Called every tick on both client and server")
    public WorldEventTypeBuilder onTick(BiConsumer<CustomWorldEvent, Level> tickConsumer) {
        this.instanceBuilder.onTick(tickConsumer);
        return this;
    }

    @Info("Allows you to setup additional save data for the event, enter your default data here")
    public WorldEventTypeBuilder setupAdditionalData(Consumer<WorldEventInstanceData> setupDataConsumer) {
        this.instanceBuilder.createInstanceData(setupDataConsumer);
        return this;
    }

    @Info("Allows you to save additional data for the event that was previously setup in setupAdditionalSaveData")
    public WorldEventTypeBuilder saveAdditionalData(BiConsumer<WorldEventInstanceData, CompoundTag> saveDataConsumer) {
        this.instanceBuilder.writeSaveData(saveDataConsumer);
        return this;
    }

    @Info("Allows you to read additional data for the event that was previously saved in saveAdditionalData")
    public WorldEventTypeBuilder readAdditionalData(BiConsumer<WorldEventInstanceData, CompoundTag> readDataConsumer) {
        this.instanceBuilder.readSaveData(readDataConsumer);
        return this;
    }

    @Override
    public WorldEventType createObject() {
        return new WorldEventType(id, instanceBuilder::build, isClientSynced);
    }
}
