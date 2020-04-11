package com.tennis.terrains.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * RequestBodyTerrain
 */

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestBodyTerrain extends RepresentationModel<RequestBodyTerrain> implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull

    @JsonProperty("nameTerrain")
    private String nameTerrain;


    @JsonProperty("namesHoraire")
    private List<String> namesHoraire;


    public RequestBodyTerrain addNamesHoraireItem(String namesHoraireItem) {
        if (this.namesHoraire == null) {
            this.namesHoraire = new ArrayList<>();
        }
        this.namesHoraire.add(namesHoraireItem);
        return this;
    }


    @JsonProperty("namesTarifs")
    private List<String> namesTarifs;


    public RequestBodyTerrain addNamesTarifsItem(String namesTarifsItem) {
        if (this.namesTarifs == null) {
            this.namesTarifs = new ArrayList<>();
        }
        this.namesTarifs.add(namesTarifsItem);
        return this;
    }
}
