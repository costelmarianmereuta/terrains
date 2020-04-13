package com.tennis.terrains.client;

import com.tennis.terrains.model.Tarifs;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TarifClient {


    public Tarifs getTarifs() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Tarifs> tarifsList = restTemplate.exchange("http://localhost:8080/tarifs/tarifs", HttpMethod.GET, null, new ParameterizedTypeReference<Tarifs>() {
        });

        return tarifsList.getBody();
    }
}
