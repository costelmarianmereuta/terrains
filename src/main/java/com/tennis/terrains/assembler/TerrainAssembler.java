package com.tennis.terrains.assembler;

import com.tennis.terrains.entities.TerrainEntity;
import com.tennis.terrains.model.Terrain;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class TerrainAssembler implements RepresentationModelAssembler<TerrainEntity, Terrain> {
    @Override
    public Terrain toModel(TerrainEntity entity) {
        return Terrain.builder()
                .name(entity.getNomTerrain())
                .actif(entity.isActif())
                .build();
    }
}
