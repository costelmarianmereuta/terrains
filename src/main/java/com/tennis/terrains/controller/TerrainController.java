package com.tennis.terrains.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tennis.terrains.assembler.TerrainAssembler;
import com.tennis.terrains.client.TarifClient;
import com.tennis.terrains.entities.TerrainEntity;
import com.tennis.terrains.model.RequestBodyTerrain;
import com.tennis.terrains.model.Terrain;
import com.tennis.terrains.model.Terrains;
import com.tennis.terrains.service.TerrainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/terrains")
public class TerrainController {

    private TerrainService terrainService;
    private TerrainAssembler terrainAssembler;
    private TarifClient tarifClient;

    public TerrainController(TerrainService terrainService, TerrainAssembler terrainAssembler, TarifClient tarifClient) {
        this.terrainService = terrainService;
        this.terrainAssembler = terrainAssembler;
        this.tarifClient = tarifClient;
    }

    @PostMapping
    public ResponseEntity<Terrain> createTerrain(@RequestBody RequestBodyTerrain requestBodyTerrain) {
        TerrainEntity terrain = terrainService.createTerrain(requestBodyTerrain);
        return ResponseEntity.status(201).body(terrainAssembler.toModel(terrain));
    }

    @GetMapping
    public ResponseEntity<Terrains> getTerrains() throws JsonProcessingException {
        List<TerrainEntity> terrainEntityList = terrainService.getTerrains();
        tarifClient.getTarifs();
        List<Terrain> terrains = new ArrayList<>(terrainAssembler.toCollectionModel(terrainEntityList).getContent());
        return ResponseEntity.ok().body(Terrains.builder().terrainsList(terrains).build());
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<Terrain> getTerrainByName(@PathVariable String name) {
        TerrainEntity terrain = terrainService.getTerrain(name);
        return ResponseEntity.ok(terrainAssembler.toModel(terrain));
    }
}
