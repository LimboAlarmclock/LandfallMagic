package net.Limbo.landfallmagic.client.model;

import net.Limbo.landfallmagic.entity.KarmaCondenserBlockEntity;
import net.Limbo.landfallmagic.landfallmagic;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class KarmaCondenserModel extends GeoModel<KarmaCondenserBlockEntity> {
    @Override
    public ResourceLocation getModelResource(KarmaCondenserBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "geo/karma_condenser.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(KarmaCondenserBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "textures/entity/karma_condenser.png");
    }

    @Override
    public ResourceLocation getAnimationResource(KarmaCondenserBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "animations/karma_condenser.animation.json");
    }
}