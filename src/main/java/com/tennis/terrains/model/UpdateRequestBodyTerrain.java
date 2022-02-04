package com.tennis.terrains.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * UpdateRequestBodyTerrain
 */

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateRequestBodyTerrain extends RepresentationModel<UpdateRequestBodyTerrain> implements Serializable {
	private static final long serialVersionUID = 1L;


	@JsonProperty("id")
	private Long id;


	@JsonProperty("nameTerrain")
	private String nameTerrain;


	@JsonProperty("actif")
	private Boolean actif;


	@JsonProperty("namesHoraire")
	private List<String> namesHoraire;


	public UpdateRequestBodyTerrain addNamesHoraireItem(String namesHoraireItem) {
		if (this.namesHoraire == null) {
			this.namesHoraire = new ArrayList<>();
		}
		this.namesHoraire.add(namesHoraireItem);
		return this;
	}


	@JsonProperty("namesTarifs")
	private List<String> namesTarifs;


	public UpdateRequestBodyTerrain addNamesTarifsItem(String namesTarifsItem) {
		if (this.namesTarifs == null) {
			this.namesTarifs = new ArrayList<>();
		}
		this.namesTarifs.add(namesTarifsItem);
		return this;
	}
}
