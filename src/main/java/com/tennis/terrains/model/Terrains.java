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
 * Terrains
 */

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Terrains extends RepresentationModel<Terrains> implements Serializable {
	private static final long serialVersionUID = 1L;

  
	@Valid


	@JsonProperty("terrainsList")
	private List<Terrain> terrainsList;


	public Terrains addTerrainsListItem(Terrain terrainsListItem) {
		if (this.terrainsList == null) {
			this.terrainsList = new ArrayList<>();
		}
		this.terrainsList.add(terrainsListItem);
		return this;
	}
}
