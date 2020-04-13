package com.tennis.terrains.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Terrain
 */

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Terrain extends RepresentationModel<Terrain> implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonProperty("name")
    private String name;


    @JsonProperty("actif")
    private Boolean actif;


    @Valid


    @JsonProperty("tarifs")
    private Tarifs tarifs;


    @Valid


    @JsonProperty("horaires")
    private List<Horaire> horaires;


    public Terrain addHorairesItem(Horaire horairesItem) {
        if (this.horaires == null) {
            this.horaires = new ArrayList<>();
        }
        this.horaires.add(horairesItem);
        return this;
    }
}
