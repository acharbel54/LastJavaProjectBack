package com.gare.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Données pour mettre à jour le statut d'une réservation")
public class UpdateReservationStatusRequest {
    
    @NotBlank
    @Schema(description = "Nouveau statut de la réservation", 
            example = "CONFIRMEE", 
            allowableValues = {"EN_ATTENTE", "CONFIRMEE", "ANNULEE", "PAYEE"},
            required = true)
    private String statut;
    
    // Constructeurs
    public UpdateReservationStatusRequest() {}
    
    public UpdateReservationStatusRequest(String statut) {
        this.statut = statut;
    }
    
    // Getters et Setters
    public String getStatut() {
        return statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }
}