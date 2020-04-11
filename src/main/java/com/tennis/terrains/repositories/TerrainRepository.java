package com.tennis.terrains.repositories;

import com.tennis.terrains.entities.TerrainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerrainRepository extends JpaRepository<TerrainEntity, Long> {

    TerrainEntity findByNomTerrain(String nomTerrain);
}
