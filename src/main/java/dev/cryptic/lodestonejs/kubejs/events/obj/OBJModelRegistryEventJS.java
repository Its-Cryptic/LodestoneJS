//package dev.cryptic.lodestonejs.kubejs.events.obj;
//
//import dev.latvian.mods.kubejs.client.*;
//import net.minecraft.resources.ResourceLocation;
//import team.lodestar.lodestone.registry.client.LodestoneOBJModels;
//import team.lodestar.lodestone.systems.model.obj.ObjModel;
//import team.lodestar.lodestone.systems.model.obj.lod.LODStrategy;
//import team.lodestar.lodestone.systems.model.obj.lod.MultiLODModel;
//
//public class OBJModelRegistryEventJS extends ClientEventJS {
//    public ObjModel createModel(String modelLocation) {
//        ResourceLocation resourceLocation = ResourceLocation.tryParse(modelLocation);
//        return LodestoneOBJModels.register(new ObjModel(resourceLocation));
//    }
//
//    public MultiLODModel createLODModel(LODStrategy<?> strategy) {
//        return LodestoneOBJModels.register(new MultiLODModel(strategy));
//    }
//}
