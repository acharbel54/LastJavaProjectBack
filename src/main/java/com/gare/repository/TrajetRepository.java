package com.gare.repository;

import com.gare.model.Trajet;
import com.gare.model.Gare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrajetRepository extends JpaRepository<Trajet, Long> {
    List<Trajet> findByGareDepartAndGareArrivee(Gare gareDepart, Gare gareArrivee);
    
    @Query("SELECT t FROM Trajet t WHERE t.gareDepart = :gareDepart AND t.gareArrivee = :gareArrivee " +
           "AND t.heureDepart >= :dateDepart AND t.heureDepart < :dateFin")
    List<Trajet> findTrajets(@Param("gareDepart") Gare gareDepart, 
                            @Param("gareArrivee") Gare gareArrivee,
                            @Param("dateDepart") LocalDateTime dateDepart,
                            @Param("dateFin") LocalDateTime dateFin);
    
    List<Trajet> findByHeureDepartBetween(LocalDateTime start, LocalDateTime end);
}