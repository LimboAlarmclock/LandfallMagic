package net.Limbo.landfallmagic.client.renderer;

import net.Limbo.landfallmagic.client.model.DireWolfModel;
import net.Limbo.landfallmagic.client.model.ModModelLayers;
import net.Limbo.landfallmagic.entity.DireWolfEntity;
import net.Limbo.landfallmagic.landfallmagic;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DireWolfRenderer extends MobRenderer<DireWolfEntity, DireWolfModel> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "textures/entity/dire_wolf.png");

    public DireWolfRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DireWolfModel(pContext.bakeLayer(ModModelLayers.DIRE_WOLF_LAYER)), 0.5f); // 0.5f is shadow size
    }

    @Override
    public ResourceLocation getTextureLocation(DireWolfEntity pEntity) {
        return TEXTURE;
    }
}