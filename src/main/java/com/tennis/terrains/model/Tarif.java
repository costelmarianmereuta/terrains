package com.tennis.terrains.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;
/**
 * Tarif
 */

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tarif extends RepresentationModel<Tarif> implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonProperty("name")
    private String name;


    @JsonProperty("prix")
    private Float prix;


    @Valid


    @JsonProperty("startDate")
    private LocalDate startDate;


    @Valid


    @JsonProperty("endDate")
    private LocalDate endDate;


    @Pattern(regexp = "(?:[01]\\d|2[0123]):(?:[012345]\\d)")

    @JsonProperty("startTime")
    private String startTime;


    @Pattern(regexp = "(?:[01]\\d|2[0123]):(?:[012345]\\d)")

    @JsonProperty("endTime")
    private String endTime;


    @JsonProperty("weekend")
    private Boolean weekend;


    @JsonProperty("actif")
    private Boolean actif;


    @JsonProperty("specialTarif")
    private Boolean specialTarif;


    @JsonProperty("defaultTarif")
    private Boolean defaultTarif;


    @Valid


    @JsonProperty("terrains")
    private Terrains terrains;

}
