package com.gare.controller;

import com.gare.model.Gare;
import com.gare.service.GareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/gares")
@CrossOrigin(origins = "http://localhost:4200")
public class GareController {
    
    @Autowired
    private GareService gareService;
    
    @GetMapping
    public List<Gare> getAllGares() {
        return gareService.getAllGares();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Gare> getGareById(@PathVariable Long id) {
        return gareService.getGareById(id)
            .map(gare -> ResponseEntity.ok().body(gare))
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public List<Gare> searchGares(@RequestParam String q) {
        return gareService.searchGares(q);
    }
    
    @PostMapping
    public Gare createGare(@Valid @RequestBody Gare gare) {
        return gareService.createGare(gare);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Gare> updateGare(@PathVariable Long id, @Valid @RequestBody Gare gareDetails) {
        try {
            Gare updatedGare = gareService.updateGare(id, gareDetails);
            return ResponseEntity.ok(updatedGare);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGare(@PathVariable Long id) {
        try {
            gareService.deleteGare(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}