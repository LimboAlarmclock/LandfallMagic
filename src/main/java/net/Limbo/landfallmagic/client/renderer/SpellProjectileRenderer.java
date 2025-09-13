package net.Limbo.landfallmagic.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.Limbo.landfallmagic.entity.sorcerery.SpellProjectileEntity;
import net.Limbo.landfallmagic.landfallmagic;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class SpellProjectileRenderer extends EntityRenderer<SpellProjectileEntity> {
    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "textures/entity/spell_projectile.png");

    public SpellProjectileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(SpellProjectileEntity pEntity) {
        return TEXTURE_LOCATION;
    }

    @Override
    public void render(SpellProjectileEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.scale(0.8F, 0.8F, 0.8F);
        pPoseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());

        PoseStack.Pose pose = pPoseStack.last();
        Matrix4f matrix4f = pose.pose();
        Matrix3f matrix3f = pose.normal();
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityCutout(TEXTURE_LOCATION));

        // --- Fixed Vertex Drawing ---

        // Bottom-Left
        vertexconsumer.addVertex(matrix4f, -0.5F, -0.5F, 0.0F);
        vertexconsumer.setColor(255, 255, 255, 255);
        vertexconsumer.setUv(0.0F, 1.0F);
        vertexconsumer.setOverlay(OverlayTexture.NO_OVERLAY);
        vertexconsumer.setUv2(pPackedLight & 0xFFFF, (pPackedLight >> 16) & 0xFFFF);
        vertexconsumer.setNormal(pose, 0.0F, 1.0F, 0.0F);

        // Bottom-Right
        vertexconsumer.addVertex(matrix4f, 0.5F, -0.5F, 0.0F);
        vertexconsumer.setColor(255, 255, 255, 255);
        vertexconsumer.setUv(1.0F, 1.0F);
        vertexconsumer.setOverlay(OverlayTexture.NO_OVERLAY);
        vertexconsumer.setUv2(pPackedLight & 0xFFFF, (pPackedLight >> 16) & 0xFFFF);
        vertexconsumer.setNormal(pose, 0.0F, 1.0F, 0.0F);

        // Top-Right
        vertexconsumer.addVertex(matrix4f, 0.5F, 0.5F, 0.0F);
        vertexconsumer.setColor(255, 255, 255, 255);
        vertexconsumer.setUv(1.0F, 0.0F);
        vertexconsumer.setOverlay(OverlayTexture.NO_OVERLAY);
        vertexconsumer.setUv2(pPackedLight & 0xFFFF, (pPackedLight >> 16) & 0xFFFF);
        vertexconsumer.setNormal(pose, 0.0F, 1.0F, 0.0F);

        // Top-Left
        vertexconsumer.addVertex(matrix4f, -0.5F, 0.5F, 0.0F);
        vertexconsumer.setColor(255, 255, 255, 255);
        vertexconsumer.setUv(0.0F, 0.0F);
        vertexconsumer.setOverlay(OverlayTexture.NO_OVERLAY);
        vertexconsumer.setUv2(pPackedLight & 0xFFFF, (pPackedLight >> 16) & 0xFFFF);
        vertexconsumer.setNormal(pose, 0.0F, 1.0F, 0.0F);

        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}