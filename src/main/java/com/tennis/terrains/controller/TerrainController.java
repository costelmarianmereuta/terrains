package com.tennis.terrains.controller;

import com.tennis.terrains.assembler.TerrainAssembler;
import com.tennis.terrains.entities.TerrainEntity;
import com.tennis.terrains.model.RequestBodyTerrain;
import com.tennis.terrains.model.Terrain;
import com.tennis.terrains.model.Terrains;
import com.tennis.terrains.model.UpdateRequestBodyTerrain;
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

    public TerrainController(TerrainService terrainService, TerrainAssembler terrainAssembler) {
        this.terrainService = terrainService;
        this.terrainAssembler = terrainAssembler;

    }

    @PostMapping
    public ResponseEntity<Terrain> createTerrain(@RequestBody RequestBodyTerrain requestBodyTerrain) {
        TerrainEntity terrain = terrainService.createTerrain(requestBodyTerrain);
        return ResponseEntity.status(201).body(terrainAssembler.toModel(terrain));
    }

    @GetMapping
    public ResponseEntity<Terrains> getTerrains() {
        List<TerrainEntity> terrainEntityList = terrainService.getTerrains();


        List<Terrain> terrains = new ArrayList<>(terrainAssembler.toCollectionModel(terrainEntityList).getContent());

        return ResponseEntity.ok().body(Terrains.builder().terrainsList(terrains).build());
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<Terrain> getTerrainByName(@PathVariable String name) {
        TerrainEntity terrain = terrainService.getTerrain(name);
        return ResponseEntity.ok(terrainAssembler.toModel(terrain));
    }

    @PutMapping()
    public ResponseEntity<Terrain> updateTerrains(@RequestBody UpdateRequestBodyTerrain requestBodyTerrain) {
        TerrainEntity terrain = terrainService.updateTerrain(requestBodyTerrain);
        return ResponseEntity.ok(terrainAssembler.toModel(terrain));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteTerrain(@PathVariable Long id) {
        String message = terrainService.deleteTerrain(id);
        return ResponseEntity.ok(message);
    }

}
