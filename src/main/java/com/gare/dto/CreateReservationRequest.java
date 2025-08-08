package com.gare.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Données pour créer une nouvelle réservation")
public class CreateReservationRequest {
    
    @NotNull
    @Schema(description = "ID de l'utilisateur qui fait la réservation", 
            example = "1", 
            required = true)
    private Long userId;
    
    @NotNull
    @Schema(description = "ID du trajet à réserver", 
            example = "1", 
            required = true)
    private Long trajetId;
    
    @NotNull
    @Positive
    @Schema(description = "Nombre de places à réserver", 
            example = "2", 
            required = true)
    private Integer nombrePlaces;
    
    // Constructeurs
    public CreateReservationRequest() {}
    
    public CreateReservationRequest(Long userId, Long trajetId, Integer nombrePlaces) {
        this.userId = userId;
        this.trajetId = trajetId;
        this.nombrePlaces = nombrePlaces;
    }
    
    // Getters et Setters
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Long getTrajetId() {
        return trajetId;
    }
    
    public void setTrajetId(Long trajetId) {
        this.trajetId = trajetId;
    }
    
    public Integer getNombrePlaces() {
        return nombrePlaces;
    }
    
    public void setNombrePlaces(Integer nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }
}