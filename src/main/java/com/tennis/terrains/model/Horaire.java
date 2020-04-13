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
 * Horaire
 */

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Horaire extends RepresentationModel<Horaire> implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonProperty("nameHoraire")
    private String nameHoraire;


    @Pattern(regexp = "(?:[01]\\d|2[0123]):(?:[012345]\\d)")

    @JsonProperty("startTime")
    private String startTime;


    @Pattern(regexp = "(?:[01]\\d|2[0123]):(?:[012345]\\d)")

    @JsonProperty("endTime")
    private String endTime;


    @Valid


    @JsonProperty("dateHoraireSpecial")
    private LocalDate dateHoraireSpecial;


    @Valid


    @JsonProperty("intervaleTemps")
    private Intervale intervaleTemps = Intervale.NUMBER_30;

}
