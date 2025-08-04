package com.gare.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "trajets")
public class Trajet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gare_depart_id")
    @NotNull
    private Gare gareDepart;

    @ManyToOne
    @JoinColumn(name = "gare_arrivee_id")
    @NotNull
    private Gare gareArrivee;

    @Column(name = "heure_depart")
    @NotNull
    private LocalDateTime heureDepart;

    @Column(name = "heure_arrivee")
    @NotNull
    private LocalDateTime heureArrivee;

    @NotNull
    private BigDecimal prix;

    @Column(name = "places_disponibles")
    private Integer placesDisponibles;

    @Column(name = "places_totales")
    private Integer placesTotales;

    @Column(name = "type_transport")
    private String typeTransport; // TER, TGV, Intercités, etc.

    @OneToMany(mappedBy = "trajet", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reservation> reservations;

    // Constructeurs
    public Trajet() {}

    public Trajet(Gare gareDepart, Gare gareArrivee, LocalDateTime heureDepart, 
                  LocalDateTime heureArrivee, BigDecimal prix) {
        this.gareDepart = gareDepart;
        this.gareArrivee = gareArrivee;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.prix = prix;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Gare getGareDepart() { return gareDepart; }
    public void setGareDepart(Gare gareDepart) { this.gareDepart = gareDepart; }

    public Gare getGareArrivee() { return gareArrivee; }
    public void setGareArrivee(Gare gareArrivee) { this.gareArrivee = gareArrivee; }

    public LocalDateTime getHeureDepart() { return heureDepart; }
    public void setHeureDepart(LocalDateTime heureDepart) { this.heureDepart = heureDepart; }

    public LocalDateTime getHeureArrivee() { return heureArrivee; }
    public void setHeureArrivee(LocalDateTime heureArrivee) { this.heureArrivee = heureArrivee; }

    public BigDecimal getPrix() { return prix; }
    public void setPrix(BigDecimal prix) { this.prix = prix; }

    public Integer getPlacesDisponibles() { return placesDisponibles; }
    public void setPlacesDisponibles(Integer placesDisponibles) { this.placesDisponibles = placesDisponibles; }

    public Integer getPlacesTotales() { return placesTotales; }
    public void setPlacesTotales(Integer placesTotales) { this.placesTotales = placesTotales; }

    public String getTypeTransport() { return typeTransport; }
    public void setTypeTransport(String typeTransport) { this.typeTransport = typeTransport; }

    public List<Reservation> getReservations() { return reservations; }
    public void setReservations(List<Reservation> reservations) { this.reservations = reservations; }
}