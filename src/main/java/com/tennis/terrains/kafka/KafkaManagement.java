package com.tennis.terrains.kafka;

import com.tennis.terrains.entities.TerrainEntity;
import com.tennis.terrains.repositories.TerrainRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaManagement {
    private TerrainRepository terrainRepository;

    public KafkaManagement(TerrainRepository terrainRepository) {
        this.terrainRepository = terrainRepository;
    }

    @KafkaListener(topics = "tarifsRemoveTopic", groupId = "goupe-ms")
    public void removeTarifsFromTerrain(ConsumerRecord<String, String> message) {

        List<TerrainEntity> allByTarifsNames = terrainRepository.findByTarifsNames(message.value());
        for (TerrainEntity terrainEntity : allByTarifsNames) {
            terrainEntity.getTarifsNames().remove(message.value());
        }
    }
}
