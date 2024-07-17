package dev.cryptic.lodestonejs.kubejs.events;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.latvian.mods.kubejs.client.ClientEventJS;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import org.joml.Matrix4f;

public class RenderLevelStageEventJS extends ClientEventJS {
    public RenderLevelStageEvent event;
    public RenderLevelStageEventJS(RenderLevelStageEvent event) {
        this.event = event;
    }

    public RenderLevelStageEvent.Stage getStage() {
        return event.getStage();
    }

    public LevelRenderer getLevelRenderer() {
        return event.getLevelRenderer();
    }

    public PoseStack getPoseStack() {
        return event.getPoseStack();
    }

    public Matrix4f getProjectionMatrix() {
        return event.getProjectionMatrix();
    }

    public int getRenderTick() {
        return event.getRenderTick();
    }

    public float getPartialTick() {
        return event.getPartialTick();
    }

    public Camera getCamera() {
        return event.getCamera();
    }

    public Frustum getFrustum() {
        return event.getFrustum();
    }
}
