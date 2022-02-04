package com.tennis.terrains.entities;

import com.tennis.terrains.model.Tarifs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "terrains", uniqueConstraints = {
        @UniqueConstraint(columnNames = "court_name", name = "court_name")})
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerrainEntity {
    @Id
    @GeneratedValue
    @Column(name = "id_court")
    private long idTerrain;
    @Column(name = "court_name")
    private String nomTerrain;
    @Column(name = "actif")
    private boolean actif;
    @ElementCollection
    private Set<String> tarifsNames;
    @ElementCollection
    Set<String> horairesNames;
    @Transient
    private Tarifs tarifs;
}
