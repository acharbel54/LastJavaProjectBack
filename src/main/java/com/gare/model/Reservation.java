package com.gare.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "trajet_id")
    @NotNull
    @JsonIgnoreProperties("reservations")
    private Trajet trajet;

    @Column(name = "nombre_places")
    @NotNull
    private Integer nombrePlaces;

    @Column(name = "prix_total")
    @NotNull
    private BigDecimal prixTotal;

    @Column(name = "date_reservation")
    private LocalDateTime dateReservation;

    @Enumerated(EnumType.STRING)
    private StatutReservation statut;

    @Column(name = "numero_reservation")
    private String numeroReservation;

    @PrePersist
    protected void onCreate() {
        dateReservation = LocalDateTime.now();
        if (statut == null) {
            statut = StatutReservation.EN_ATTENTE;
        }
    }

    // Constructeurs
    public Reservation() {}

    public Reservation(User user, Trajet trajet, Integer nombrePlaces, BigDecimal prixTotal) {
        this.user = user;
        this.trajet = trajet;
        this.nombrePlaces = nombrePlaces;
        this.prixTotal = prixTotal;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Trajet getTrajet() { return trajet; }
    public void setTrajet(Trajet trajet) { this.trajet = trajet; }

    public Integer getNombrePlaces() { return nombrePlaces; }
    public void setNombrePlaces(Integer nombrePlaces) { this.nombrePlaces = nombrePlaces; }

    public BigDecimal getPrixTotal() { return prixTotal; }
    public void setPrixTotal(BigDecimal prixTotal) { this.prixTotal = prixTotal; }

    public LocalDateTime getDateReservation() { return dateReservation; }
    public void setDateReservation(LocalDateTime dateReservation) { this.dateReservation = dateReservation; }

    public StatutReservation getStatut() { return statut; }
    public void setStatut(StatutReservation statut) { this.statut = statut; }

    public String getNumeroReservation() { return numeroReservation; }
    public void setNumeroReservation(String numeroReservation) { this.numeroReservation = numeroReservation; }

    public enum StatutReservation {
        EN_ATTENTE, CONFIRMEE, ANNULEE, PAYEE
    }
}