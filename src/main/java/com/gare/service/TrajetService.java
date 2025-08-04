package com.gare.service;

import com.gare.model.Trajet;
import com.gare.model.Gare;
import com.gare.repository.TrajetRepository;
import com.gare.repository.GareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TrajetService {
    
    @Autowired
    private TrajetRepository trajetRepository;
    
    @Autowired
    private GareRepository gareRepository;
    
    public List<Trajet> getAllTrajets() {
        return trajetRepository.findAll();
    }
    
    public Optional<Trajet> getTrajetById(Long id) {
        return trajetRepository.findById(id);
    }
    
    public List<Trajet> searchTrajets(Gare gareDepart, Gare gareArrivee) {
        return trajetRepository.findByGareDepartAndGareArrivee(gareDepart, gareArrivee);
    }
    
    public List<Trajet> searchTrajets(Gare gareDepart, Gare gareArrivee, LocalDateTime dateDepart, LocalDateTime dateFin) {
        return trajetRepository.findTrajets(gareDepart, gareArrivee, dateDepart, dateFin);
    }
    
    public Trajet createTrajet(Trajet trajet) {
        return trajetRepository.save(trajet);
    }
    
    public Trajet updateTrajet(Long id, Trajet trajetDetails) {
        Trajet trajet = trajetRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Trajet non trouvé avec l'id: " + id));
        
        trajet.setGareDepart(trajetDetails.getGareDepart());
        trajet.setGareArrivee(trajetDetails.getGareArrivee());
        trajet.setHeureDepart(trajetDetails.getHeureDepart());
        trajet.setHeureArrivee(trajetDetails.getHeureArrivee());
        trajet.setPrix(trajetDetails.getPrix());
        trajet.setPlacesDisponibles(trajetDetails.getPlacesDisponibles());
        trajet.setPlacesTotales(trajetDetails.getPlacesTotales());
        trajet.setTypeTransport(trajetDetails.getTypeTransport());
        
        return trajetRepository.save(trajet);
    }
    
    public void deleteTrajet(Long id) {
        trajetRepository.deleteById(id);
    }
    
    // Méthode pour créer des trajets d'exemple
    public void createSampleTrajets() {
        List<Gare> gares = gareRepository.findAll();
        if (gares.size() < 2) {
            throw new RuntimeException("Il faut au moins 2 gares pour créer des trajets");
        }
        
        // Créer 10 trajets d'exemple
        LocalDateTime now = LocalDateTime.now();
        
        for (int i = 0; i < 10; i++) {
            Gare gareDepart = gares.get(i % gares.size());
            Gare gareArrivee = gares.get((i + 1) % gares.size());
            
            if (!gareDepart.equals(gareArrivee)) {
                Trajet trajet = new Trajet();
                trajet.setGareDepart(gareDepart);
                trajet.setGareArrivee(gareArrivee);
                trajet.setHeureDepart(now.plusHours(i + 1));
                trajet.setHeureArrivee(now.plusHours(i + 3));
                trajet.setPrix(new BigDecimal(25 + (i * 5)));
                trajet.setPlacesDisponibles(50 - (i * 2));
                trajet.setPlacesTotales(100);
                trajet.setTypeTransport(i % 3 == 0 ? "TGV" : i % 3 == 1 ? "TER" : "Intercités");
                
                trajetRepository.save(trajet);
            }
        }
    }
}