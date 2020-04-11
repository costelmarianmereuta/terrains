package com.tennis.terrains.service;

import com.tennis.terrains.entities.TerrainEntity;
import com.tennis.terrains.model.RequestBodyTerrain;
import com.tennis.terrains.repositories.TerrainRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.tennis.terrains.utils.Constants.TERRAIN_NOT_FOUND;

@Service
public class TerrainService {
    private TerrainRepository terrainRepository;

    public TerrainService(TerrainRepository terrainRepository) {
        this.terrainRepository = terrainRepository;
    }

    public List<TerrainEntity> getTerrains() {
        return terrainRepository.findAll();
    }

    public TerrainEntity getTerrain(String name) {
        TerrainEntity terrain = terrainRepository.findByNomTerrain(name);
        if (!ObjectUtils.isEmpty(terrain)) {
            return terrain;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, TERRAIN_NOT_FOUND);
        }
    }

    public TerrainEntity createTerrain(RequestBodyTerrain requestBodyTerrain) {
        TerrainEntity terrainEntity = TerrainEntity.builder()
                .nomTerrain(requestBodyTerrain.getNameTerrain()).build();

        try {
            terrainRepository.save(terrainEntity);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex.getCause());
        }
        return terrainEntity;
    }


}
