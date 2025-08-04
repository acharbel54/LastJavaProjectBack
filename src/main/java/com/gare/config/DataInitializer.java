package com.gare.config;

import com.gare.model.Gare;
import com.gare.model.Trajet;
import com.gare.repository.GareRepository;
import com.gare.repository.TrajetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private GareRepository gareRepository;

    @Autowired
    private TrajetRepository trajetRepository;

    @Override
    public void run(String... args) throws Exception {
        // Vérifier si les données existent déjà
        if (gareRepository.count() == 0) {
            initializeGares();
            initializeTrajets();
        }
    }

    private void initializeGares() {
        // Gares principales du Sénégal
        Gare dakar = new Gare();
        dakar.setNom("Gare de Dakar");
        dakar.setVille("Dakar");
        dakar.setAdresse("Avenue Blaise Diagne, Dakar");
        dakar.setCodeGare("SNDK");
        dakar.setLatitude(14.6937);
        dakar.setLongitude(-17.4441);
        gareRepository.save(dakar);

        Gare thies = new Gare();
        thies.setNom("Gare de Thiès");
        thies.setVille("Thiès");
        thies.setAdresse("Route de Dakar, Thiès");
        thies.setCodeGare("SNTH");
        thies.setLatitude(14.7886);
        thies.setLongitude(-16.9260);
        gareRepository.save(thies);

        Gare kaolack = new Gare();
        kaolack.setNom("Gare de Kaolack");
        kaolack.setVille("Kaolack");
        kaolack.setAdresse("Avenue Léopold Sédar Senghor, Kaolack");
        kaolack.setCodeGare("SNKL");
        kaolack.setLatitude(14.1593);
        kaolack.setLongitude(-16.0728);
        gareRepository.save(kaolack);

        Gare tambacounda = new Gare();
        tambacounda.setNom("Gare de Tambacounda");
        tambacounda.setVille("Tambacounda");
        tambacounda.setAdresse("Route Nationale 1, Tambacounda");
        tambacounda.setCodeGare("SNTB");
        tambacounda.setLatitude(13.7671);
        tambacounda.setLongitude(-13.6681);
        gareRepository.save(tambacounda);

        Gare saintLouis = new Gare();
        saintLouis.setNom("Gare de Saint-Louis");
        saintLouis.setVille("Saint-Louis");
        saintLouis.setAdresse("Avenue Jean Mermoz, Saint-Louis");
        saintLouis.setCodeGare("SNSL");
        saintLouis.setLatitude(16.0378);
        saintLouis.setLongitude(-16.4897);
        gareRepository.save(saintLouis);

        Gare ziguinchor = new Gare();
        ziguinchor.setNom("Gare de Ziguinchor");
        ziguinchor.setVille("Ziguinchor");
        ziguinchor.setAdresse("Route de Bignona, Ziguinchor");
        ziguinchor.setCodeGare("SNZG");
        ziguinchor.setLatitude(12.5681);
        ziguinchor.setLongitude(-16.2739);
        gareRepository.save(ziguinchor);

        Gare diourbel = new Gare();
        diourbel.setNom("Gare de Diourbel");
        diourbel.setVille("Diourbel");
        diourbel.setAdresse("Avenue Cheikh Ahmadou Bamba, Diourbel");
        diourbel.setCodeGare("SNDB");
        diourbel.setLatitude(14.6522);
        diourbel.setLongitude(-16.2317);
        gareRepository.save(diourbel);

        Gare louga = new Gare();
        louga.setNom("Gare de Louga");
        louga.setVille("Louga");
        louga.setAdresse("Route de Dahra, Louga");
        louga.setCodeGare("SNLG");
        louga.setLatitude(15.6181);
        louga.setLongitude(-16.2267);
        gareRepository.save(louga);

        System.out.println("✅ Gares sénégalaises initialisées avec succès !");
    }

    private void initializeTrajets() {
        // Récupérer les gares
        Gare dakar = gareRepository.findByCodeGare("SNDK").orElse(null);
        Gare thies = gareRepository.findByCodeGare("SNTH").orElse(null);
        Gare kaolack = gareRepository.findByCodeGare("SNKL").orElse(null);
        Gare tambacounda = gareRepository.findByCodeGare("SNTB").orElse(null);
        Gare saintLouis = gareRepository.findByCodeGare("SNSL").orElse(null);
        Gare ziguinchor = gareRepository.findByCodeGare("SNZG").orElse(null);
        Gare diourbel = gareRepository.findByCodeGare("SNDB").orElse(null);
        Gare louga = gareRepository.findByCodeGare("SNLG").orElse(null);

        LocalDateTime now = LocalDateTime.now();

        // Trajets principaux
        createTrajet(dakar, thies, now.plusHours(1), now.plusHours(2), new BigDecimal("1500"), "Express", 80, 100);
        createTrajet(thies, dakar, now.plusHours(3), now.plusHours(4), new BigDecimal("1500"), "Express", 75, 100);
        
        createTrajet(dakar, kaolack, now.plusHours(2), now.plusHours(5), new BigDecimal("3500"), "Rapide", 60, 80);
        createTrajet(kaolack, dakar, now.plusHours(6), now.plusHours(9), new BigDecimal("3500"), "Rapide", 55, 80);
        
        createTrajet(dakar, tambacounda, now.plusHours(1), now.plusHours(8), new BigDecimal("7500"), "Express", 40, 60);
        createTrajet(tambacounda, dakar, now.plusHours(9), now.plusHours(16), new BigDecimal("7500"), "Express", 35, 60);
        
        createTrajet(dakar, saintLouis, now.plusHours(2), now.plusHours(6), new BigDecimal("4500"), "Rapide", 50, 70);
        createTrajet(saintLouis, dakar, now.plusHours(7), now.plusHours(11), new BigDecimal("4500"), "Rapide", 45, 70);
        
        createTrajet(dakar, ziguinchor, now.plusHours(3), now.plusHours(9), new BigDecimal("6500"), "Express", 30, 50);
        createTrajet(ziguinchor, dakar, now.plusHours(10), now.plusHours(16), new BigDecimal("6500"), "Express", 25, 50);
        
        createTrajet(thies, diourbel, now.plusHours(1), now.plusHours(2), new BigDecimal("2000"), "Local", 40, 60);
        createTrajet(diourbel, thies, now.plusHours(3), now.plusHours(4), new BigDecimal("2000"), "Local", 35, 60);
        
        createTrajet(dakar, louga, now.plusHours(2), now.plusHours(5), new BigDecimal("3000"), "Rapide", 45, 70);
        createTrajet(louga, dakar, now.plusHours(6), now.plusHours(9), new BigDecimal("3000"), "Rapide", 40, 70);

        System.out.println("✅ Trajets sénégalais initialisés avec succès !");
    }

    private void createTrajet(Gare gareDepart, Gare gareArrivee, LocalDateTime heureDepart, 
                             LocalDateTime heureArrivee, BigDecimal prix, String typeTransport,
                             int placesDisponibles, int placesTotales) {
        if (gareDepart != null && gareArrivee != null) {
            Trajet trajet = new Trajet();
            trajet.setGareDepart(gareDepart);
            trajet.setGareArrivee(gareArrivee);
            trajet.setHeureDepart(heureDepart);
            trajet.setHeureArrivee(heureArrivee);
            trajet.setPrix(prix);
            trajet.setTypeTransport(typeTransport);
            trajet.setPlacesDisponibles(placesDisponibles);
            trajet.setPlacesTotales(placesTotales);
            trajetRepository.save(trajet);
        }
    }
}