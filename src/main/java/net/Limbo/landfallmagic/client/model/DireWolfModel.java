package net.Limbo.landfallmagic.client.model;

import net.Limbo.landfallmagic.entity.DireWolfEntity;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;

public class DireWolfModel extends WolfModel<DireWolfEntity> {

    public DireWolfModel(ModelPart pRoot) {
        super(pRoot);
    }
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = WolfModel.createMeshDefinition(CubeDeformation.NONE);
        // You could add customizations to the mesh here in the future if you want
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

}