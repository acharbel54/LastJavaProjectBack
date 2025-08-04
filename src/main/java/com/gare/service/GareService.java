package com.gare.service;

import com.gare.model.Gare;
import com.gare.repository.GareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GareService {
    
    @Autowired
    private GareRepository gareRepository;
    
    public List<Gare> getAllGares() {
        return gareRepository.findAll();
    }
    
    public Optional<Gare> getGareById(Long id) {
        return gareRepository.findById(id);
    }
    
    public List<Gare> searchGares(String searchTerm) {
        return gareRepository.searchGares(searchTerm);
    }
    
    public Gare createGare(Gare gare) {
        return gareRepository.save(gare);
    }
    
    public Gare updateGare(Long id, Gare gareDetails) {
        Gare gare = gareRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Gare non trouvée avec l'id: " + id));
        
        gare.setNom(gareDetails.getNom());
        gare.setVille(gareDetails.getVille());
        gare.setAdresse(gareDetails.getAdresse());
        gare.setCodeGare(gareDetails.getCodeGare());
        gare.setLatitude(gareDetails.getLatitude());
        gare.setLongitude(gareDetails.getLongitude());
        
        return gareRepository.save(gare);
    }
    
    public void deleteGare(Long id) {
        gareRepository.deleteById(id);
    }
}