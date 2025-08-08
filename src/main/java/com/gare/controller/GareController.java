package com.gare.controller;

import com.gare.model.Gare;
import com.gare.service.GareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/gares")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "🚉 Gares", description = "API de gestion des gares ferroviaires")
public class GareController {
    
    @Autowired
    private GareService gareService;
    
    @Operation(summary = "Récupérer toutes les gares", 
               description = "Retourne la liste complète de toutes les gares enregistrées dans le système")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des gares récupérée avec succès",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = Gare.class))),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public List<Gare> getAllGares() {
        return gareService.getAllGares();
    }
    
    @Operation(summary = "Récupérer une gare par ID", 
               description = "Retourne les détails d'une gare spécifique basée sur son identifiant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Gare trouvée",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = Gare.class))),
        @ApiResponse(responseCode = "404", description = "Gare non trouvée"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Gare> getGareById(
            @Parameter(description = "ID de la gare à récupérer", required = true)
            @PathVariable Long id) {
        return gareService.getGareById(id)
            .map(gare -> ResponseEntity.ok().body(gare))
            .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Rechercher des gares", 
               description = "Recherche des gares par nom ou ville (insensible à la casse)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Résultats de recherche récupérés",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = Gare.class))),
        @ApiResponse(responseCode = "400", description = "Paramètre de recherche invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/search")
    public List<Gare> searchGares(
            @Parameter(description = "Terme de recherche (nom ou ville)", required = true)
            @RequestParam String q) {
        return gareService.searchGares(q);
    }
    
    @Operation(summary = "Créer une nouvelle gare", 
               description = "Ajoute une nouvelle gare au système")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Gare créée avec succès",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = Gare.class))),
        @ApiResponse(responseCode = "400", description = "Données de gare invalides"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public Gare createGare(
            @Parameter(description = "Données de la gare à créer", required = true)
            @Valid @RequestBody Gare gare) {
        return gareService.createGare(gare);
    }
    
    @Operation(summary = "Modifier une gare existante", 
               description = "Met à jour les informations d'une gare existante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Gare modifiée avec succès",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = Gare.class))),
        @ApiResponse(responseCode = "404", description = "Gare non trouvée"),
        @ApiResponse(responseCode = "400", description = "Données de gare invalides"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Gare> updateGare(
            @Parameter(description = "ID de la gare à modifier", required = true)
            @PathVariable Long id, 
            @Parameter(description = "Nouvelles données de la gare", required = true)
            @Valid @RequestBody Gare gareDetails) {
        try {
            Gare updatedGare = gareService.updateGare(id, gareDetails);
            return ResponseEntity.ok(updatedGare);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @Operation(summary = "Supprimer une gare", 
               description = "Supprime définitivement une gare du système")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Gare supprimée avec succès"),
        @ApiResponse(responseCode = "404", description = "Gare non trouvée"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGare(
            @Parameter(description = "ID de la gare à supprimer", required = true)
            @PathVariable Long id) {
        try {
            gareService.deleteGare(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}