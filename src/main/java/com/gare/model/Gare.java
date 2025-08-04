package com.gare.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "gares")
public class Gare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nom;

    @NotBlank
    private String ville;

    private String adresse;

    @Column(name = "code_gare")
    private String codeGare;

    private Double latitude;
    private Double longitude;

    @OneToMany(mappedBy = "gareDepart", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Trajet> trajetsDepart;

    @OneToMany(mappedBy = "gareArrivee", cascade = CascadeType.ALL)
    @JsonIgnore
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