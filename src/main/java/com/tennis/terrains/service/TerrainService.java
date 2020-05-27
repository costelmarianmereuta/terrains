package com.tennis.terrains.service;

import com.tennis.terrains.client.TarifClient;
import com.tennis.terrains.entities.TerrainEntity;
import com.tennis.terrains.model.RequestBodyTerrain;
import com.tennis.terrains.model.Tarif;
import com.tennis.terrains.model.Tarifs;
import com.tennis.terrains.repositories.TerrainRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.tennis.terrains.utils.Constants.TERRAIN_NOT_FOUND;

@Service
public class TerrainService {
    private TerrainRepository terrainRepository;
    private TarifClient tarifClient;

    public TerrainService(TerrainRepository terrainRepository, TarifClient tarifClient) {
        this.terrainRepository = terrainRepository;
        this.tarifClient = tarifClient;
    }

    public List<TerrainEntity> getTerrains() {
        List<TerrainEntity> terrains = terrainRepository.findAll();
        for (TerrainEntity terrain : terrains) {
            setTarrifForTerrain(terrain);
        }
        return terrains;
    }

    public TerrainEntity getTerrain(String name) {
        TerrainEntity terrain = terrainRepository.findByNomTerrain(name);

        if (!ObjectUtils.isEmpty(terrain)) {
            setTarrifForTerrain(terrain);
            return terrain;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, TERRAIN_NOT_FOUND);
        }
    }


    public TerrainEntity createTerrain(RequestBodyTerrain requestBodyTerrain) {
        Tarifs tarifs = tarifClient.getTarifs(requestBodyTerrain.getNamesTarifs());
        List<String> tarifsNames = tarifs.getTarifList().stream().map(Tarif::getName).collect(Collectors.toList());
        TerrainEntity terrainEntity = TerrainEntity.builder().tarifsNames(new HashSet<>(tarifsNames))
                .nomTerrain(requestBodyTerrain.getNameTerrain()).build();

        try {
            terrainRepository.save(terrainEntity);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex.getCause());
        }
        return terrainEntity;
    }

    private void setTarrifForTerrain(TerrainEntity terrain) {
        if (!CollectionUtils.isEmpty(terrain.getTarifsNames())) {
            Tarifs tarifs = tarifClient.getTarifs(new ArrayList<>(terrain.getTarifsNames()));
            terrain.setTarifs(tarifs);
        }
    }

}
