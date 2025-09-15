package net.Limbo.landfallmagic.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.Limbo.landfallmagic.client.model.BindingTendrilsModel;
import net.Limbo.landfallmagic.entity.spellentity.BindingTendrilsEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BindingTendrilsRenderer extends GeoEntityRenderer<BindingTendrilsEntity> {
    public BindingTendrilsRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BindingTendrilsModel());
        this.shadowRadius = 0.0f; // No shadow
    }

    @Override
    public void preRender(PoseStack poseStack, BindingTendrilsEntity animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
        LivingEntity target = animatable.getTarget();
        if (target != null) {
            float scale = target.getBbWidth() * 1.5f;
            poseStack.scale(scale, scale, scale);
        }
    }
}