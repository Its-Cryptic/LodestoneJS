package dev.cryptic.lodestonejs.worldevent;

import java.util.HashMap;

/**
 * This class is meant to allow the declaration of any data types as well as read and write methods for those
 */
public interface WorldEventInstanceData {
    HashMap<String, Object> dataMap = new HashMap<>();

    default void createSaveData(String id, Object defaultValue) {
        this.dataMap.put(id, defaultValue);
    }

    default void putSaveData(String id, Object value) {
        this.dataMap.put(id, value);
    }

    default Object readSaveData(String id) {
        return this.dataMap.get(id);
    }
}
