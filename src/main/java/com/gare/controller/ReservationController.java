package com.gare.controller;

import com.gare.dto.CreateReservationRequest;
import com.gare.dto.UpdateReservationStatusRequest;
import com.gare.model.Reservation;
import com.gare.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Réservations", description = "API pour la gestion des réservations de trajets")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    @Operation(summary = "Récupérer toutes les réservations", 
               description = "Obtenir la liste complète de toutes les réservations")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                    description = "Liste des réservations récupérée avec succès",
                    content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", 
                    description = "Erreur interne du serveur",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<Reservation>> getAllReservations() {
        try {
            List<Reservation> reservations = reservationService.getAllReservations();
            return ResponseEntity.ok(reservations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Récupérer les réservations d'un utilisateur", 
               description = "Obtenir toutes les réservations d'un utilisateur spécifique")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                    description = "Réservations de l'utilisateur récupérées avec succès",
                    content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", 
                    description = "Erreur interne du serveur",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<Reservation>> getReservationsByUserId(
            @Parameter(description = "ID de l'utilisateur", required = true)
            @PathVariable Long userId) {
        try {
            List<Reservation> reservations = reservationService.getReservationsByUserId(userId);
            return ResponseEntity.ok(reservations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une réservation par ID", 
               description = "Obtenir les détails d'une réservation spécifique")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                    description = "Réservation trouvée",
                    content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", 
                    description = "Réservation non trouvée",
                    content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", 
                    description = "Erreur interne du serveur",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Reservation> getReservationById(
            @Parameter(description = "ID de la réservation", required = true)
            @PathVariable Long id) {
        try {
            Optional<Reservation> reservation = reservationService.getReservationById(id);
            return reservation.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    @Operation(summary = "Créer une nouvelle réservation", 
               description = "Créer une réservation pour un trajet avec le nombre de places spécifié")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", 
                    description = "Réservation créée avec succès",
                    content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", 
                    description = "Données invalides ou erreur de création",
                    content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", 
                    description = "Erreur interne du serveur",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<?> createReservation(
            @Parameter(description = "Données de la réservation à créer", required = true)
            @Valid @RequestBody CreateReservationRequest reservationRequest) {
        try {
            Reservation reservation = reservationService.createReservation(
                reservationRequest.getUserId(), 
                reservationRequest.getTrajetId(), 
                reservationRequest.getNombrePlaces()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erreur lors de la création de la réservation"));
        }
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Mettre à jour le statut d'une réservation", 
               description = "Modifier le statut d'une réservation existante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                    description = "Statut mis à jour avec succès",
                    content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", 
                    description = "Statut invalide ou erreur de mise à jour",
                    content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", 
                    description = "Erreur interne du serveur",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<?> updateReservationStatus(
            @Parameter(description = "ID de la réservation", required = true)
            @PathVariable Long id,
            @Parameter(description = "Nouveau statut de la réservation", required = true)
            @Valid @RequestBody UpdateReservationStatusRequest statusRequest) {
        try {
            Reservation.StatutReservation statut = Reservation.StatutReservation.valueOf(statusRequest.getStatut());
            
            Reservation reservation = reservationService.updateReservationStatus(id, statut);
            return ResponseEntity.ok(reservation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Statut invalide"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erreur lors de la mise à jour du statut"));
        }
    }

    @PutMapping("/{id}/cancel")
    @Operation(summary = "Annuler une réservation", 
               description = "Annuler une réservation existante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                    description = "Réservation annulée avec succès",
                    content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", 
                    description = "Erreur lors de l'annulation",
                    content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", 
                    description = "Erreur interne du serveur",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<?> cancelReservation(
            @Parameter(description = "ID de la réservation à annuler", required = true)
            @PathVariable Long id) {
        try {
            reservationService.cancelReservation(id);
            return ResponseEntity.ok(Map.of("message", "Réservation annulée avec succès"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erreur lors de l'annulation de la réservation"));
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une réservation", 
               description = "Supprimer définitivement une réservation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                    description = "Réservation supprimée avec succès",
                    content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", 
                    description = "Erreur interne du serveur",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<?> deleteReservation(
            @Parameter(description = "ID de la réservation à supprimer", required = true)
            @PathVariable Long id) {
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity.ok(Map.of("message", "Réservation supprimée avec succès"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erreur lors de la suppression de la réservation"));
        }
    }
}