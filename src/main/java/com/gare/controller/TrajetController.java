package com.gare.controller;

import com.gare.model.Trajet;
import com.gare.model.Gare;
import com.gare.service.TrajetService;
import com.gare.service.GareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trajets")
@CrossOrigin(origins = "http://localhost:4200")
public class TrajetController {
    
    @Autowired
    private TrajetService trajetService;
    
    @Autowired
    private GareService gareService;
    
    @GetMapping
    public List<Trajet> getAllTrajets() {
        return trajetService.getAllTrajets();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Trajet> getTrajetById(@PathVariable Long id) {
        return trajetService.getTrajetById(id)
            .map(trajet -> ResponseEntity.ok().body(trajet))
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public List<Trajet> searchTrajets(
            @RequestParam(required = false) Long gareDepartId,
            @RequestParam(required = false) Long gareArriveeId,
            @RequestParam(required = false) String dateDepart) {
        
        if (gareDepartId != null && gareArriveeId != null) {
            Gare gareDepart = gareService.getGareById(gareDepartId).orElse(null);
            Gare gareArrivee = gareService.getGareById(gareArriveeId).orElse(null);
            
            if (gareDepart != null && gareArrivee != null) {
                if (dateDepart != null) {
                    LocalDateTime dateTime = LocalDateTime.parse(dateDepart);
                    LocalDateTime dateFin = dateTime.plusDays(1);
                    return trajetService.searchTrajets(gareDepart, gareArrivee, dateTime, dateFin);
                } else {
                    return trajetService.searchTrajets(gareDepart, gareArrivee);
                }
            }
        }
        
        return trajetService.getAllTrajets();
    }
    
    @PostMapping
    public Trajet createTrajet(@Valid @RequestBody Trajet trajet) {
        return trajetService.createTrajet(trajet);
    }
    
    @PostMapping("/samples")
    public ResponseEntity<?> createSampleTrajets() {
        try {
            trajetService.createSampleTrajets();
            return ResponseEntity.ok().body(Map.of("message", "Trajets d'exemple créés avec succès"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Trajet> updateTrajet(@PathVariable Long id, @Valid @RequestBody Trajet trajetDetails) {
        try {
            Trajet updatedTrajet = trajetService.updateTrajet(id, trajetDetails);
            return ResponseEntity.ok(updatedTrajet);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrajet(@PathVariable Long id) {
        try {
            trajetService.deleteTrajet(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}