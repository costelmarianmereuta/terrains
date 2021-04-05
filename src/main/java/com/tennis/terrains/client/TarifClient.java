package com.tennis.terrains.client;

import com.tennis.terrains.model.Tarifs;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class TarifClient {


    public Tarifs getTarifs(List<String> names) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/tarifs/tarifs");
        builder.queryParam("names", String.join(",", names));
        URI uri = builder.build().encode().toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Tarifs> tarifsList = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Tarifs>() {
                }

        );
        return tarifsList.getBody();
    }
}
