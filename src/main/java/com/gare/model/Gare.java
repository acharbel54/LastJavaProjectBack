package com.gare.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Entity
@Table(name = "gares")
@Schema(description = "Entité représentant une gare ferroviaire")
public class Gare {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique de la gare", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank
    @Schema(description = "Nom de la gare", example = "Gare de Lyon", required = true)
    private String nom;

    @NotBlank
    @Schema(description = "Ville où se trouve la gare", example = "Lyon", required = true)
    private String ville;

    @Schema(description = "Adresse complète de la gare", example = "Place Louis Armand, 75012 Paris")
    private String adresse;

    @Column(name = "code_gare")
    @Schema(description = "Code unique de la gare", example = "FRLPD")
    private String codeGare;

    @Schema(description = "Latitude GPS de la gare", example = "45.7640")
    private Double latitude;
    
    @Schema(description = "Longitude GPS de la gare", example = "4.8357")
    private Double longitude;

    @OneToMany(mappedBy = "gareDepart", cascade = CascadeType.ALL)
    @JsonIgnore
    @Schema(hidden = true)
    private List<Trajet> trajetsDepart;

    @OneToMany(mappedBy = "gareArrivee", cascade = CascadeType.ALL)
    @JsonIgnore
    @Schema(hidden = true)
    private List<Trajet> trajetsArrivee;

    // Constructeurs
    public Gare() {}

    public Gare(String nom, String ville) {
        this.nom = nom;
        this.ville = ville;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getCodeGare() { return codeGare; }
    public void setCodeGare(String codeGare) { this.codeGare = codeGare; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public List<Trajet> getTrajetsDepart() { return trajetsDepart; }
    public void setTrajetsDepart(List<Trajet> trajetsDepart) { this.trajetsDepart = trajetsDepart; }

    public List<Trajet> getTrajetsArrivee() { return trajetsArrivee; }
    public void setTrajetsArrivee(List<Trajet> trajetsArrivee) { this.trajetsArrivee = trajetsArrivee; }
}