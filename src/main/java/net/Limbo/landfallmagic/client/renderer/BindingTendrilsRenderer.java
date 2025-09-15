package net.Limbo.landfallmagic.client.renderer;

import net.Limbo.landfallmagic.client.model.BindingTendrilsModel;
import net.Limbo.landfallmagic.entity.BindingTendrilsEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BindingTendrilsRenderer extends GeoEntityRenderer<BindingTendrilsEntity> {
    public BindingTendrilsRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BindingTendrilsModel());
        this.shadowRadius = 0.0f; // No shadow
    }
}