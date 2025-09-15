package net.Limbo.landfallmagic.client.model;

import net.Limbo.landfallmagic.entity.BindingTendrilsEntity;
import net.Limbo.landfallmagic.landfallmagic;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BindingTendrilsModel extends GeoModel<BindingTendrilsEntity> {
    @Override
    public ResourceLocation getModelResource(BindingTendrilsEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "geo/binding_tendrils.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BindingTendrilsEntity animatable) {
        // You can create a texture for it, or reuse one like the particle texture
        return ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "textures/particle/tendril_particle.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BindingTendrilsEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "animations/binding_tendrils.animation.json");
    }
}