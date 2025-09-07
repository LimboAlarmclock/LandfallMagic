package net.Limbo.landfallmagic.client.renderer;

import net.Limbo.landfallmagic.entity.KarmaCondenserBlockEntity;
import net.Limbo.landfallmagic.client.model.KarmaCondenserModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class KarmaCondenserRenderer extends GeoBlockRenderer<KarmaCondenserBlockEntity> {
    public KarmaCondenserRenderer(BlockEntityRendererProvider.Context context) {
        super(new KarmaCondenserModel());
    }
}