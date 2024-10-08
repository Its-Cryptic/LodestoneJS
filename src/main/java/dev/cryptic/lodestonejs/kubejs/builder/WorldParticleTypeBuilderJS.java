//package dev.cryptic.lodestonejs.kubejs.builder;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
////import dev.latvian.mods.kubejs.generator.AssetJsonGenerator;
//import dev.latvian.mods.kubejs.registry.BuilderBase;
////import dev.latvian.mods.kubejs.registry.RegistryInfo;
//import dev.latvian.mods.kubejs.typings.Info;
//import net.minecraft.core.particles.ParticleType;
//import net.minecraft.resources.ResourceLocation;
//import team.lodestar.lodestone.systems.particle.world.type.LodestoneWorldParticleType;
//
//@SuppressWarnings({"unused", "rawtypes"})
//public class WorldParticleTypeBuilderJS extends BuilderBase<ParticleType> {
//    public JsonObject particleJson = new JsonObject();
//
//    public WorldParticleTypeBuilderJS(ResourceLocation i) {
//        super(i);
//    }
//
//    @Info("""
//        Sets the particle texture locations. Required.
//        Example: js
//        // You can provide multiple textures:
//        .textures("lodestone:star", "lodestone:smoke")
//        // ...or just one:
//        .textures("lodestone:star")
//    """)
//    public WorldParticleTypeBuilderJS textures(String... textures) {
//        JsonArray tex = new JsonArray();
//        for (String s : textures) {
//            tex.add(s);
//        }
//        particleJson.add("textures", tex);
//        return this;
//    }
//
//    @Override
//    public void generateAssetJsons(AssetJsonGenerator generator) {
//        generator.json(newID("particles/", ""), particleJson);
//    }
//
//    @Override
//    public RegistryInfo getRegistryType() {
//        return RegistryInfo.PARTICLE_TYPE;
//    }
//
//    @Override
//    public ParticleType createObject() {
//        return new LodestoneWorldParticleType();
//    }
//}