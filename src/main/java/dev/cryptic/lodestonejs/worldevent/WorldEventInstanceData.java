package dev.cryptic.lodestonejs.worldevent;

import dev.latvian.mods.kubejs.typings.Info;

import java.util.HashMap;

@Info("Allows you to create additional fields for a world event instance as well as serialize and deserialize them")
public class WorldEventInstanceData {
    private HashMap<String, Object> dataMap;

    public WorldEventInstanceData() {
        this.dataMap = new HashMap<>();
    }

    @Info("Creates the default data for the instance")
    public void create(String id, Object defaultValue) {
        this.dataMap.computeIfAbsent(id, k -> defaultValue);
    }

    @Info("Writes the data to the instance")
    public void write(String id, Object value) {
        this.dataMap.computeIfPresent(id, (k, v) -> value);
    }

    @Info("Reads the data from the instance")
    public Object read(String id) {
        return this.dataMap.get(id);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WorldEventInstanceData{");
        for (int i = 0; i < dataMap.keySet().size(); i++) {
            builder.append(dataMap.keySet().toArray()[i]).append(": ").append(dataMap.values().toArray()[i]);
            if (i < dataMap.keySet().size() - 1) {
                builder.append(", ");
            }
        }
        builder.append("}");
        return builder.toString();
    }
}
