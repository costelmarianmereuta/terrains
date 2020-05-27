package com.tennis.terrains.controller;

import com.tennis.terrains.assembler.TerrainAssembler;
import com.tennis.terrains.entities.TerrainEntity;
import com.tennis.terrains.model.RequestBodyTerrain;
import com.tennis.terrains.model.Terrain;
import com.tennis.terrains.model.Terrains;
import com.tennis.terrains.repositories.TerrainRepository;
import com.tennis.terrains.service.TerrainService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    TerrainRepository terrainRepository;

    @GetMapping("/test")
    public String get(@RequestParam String terrain) {
        return terrainRepository.findByTarifsNames(terrain).toString();
    }
}
