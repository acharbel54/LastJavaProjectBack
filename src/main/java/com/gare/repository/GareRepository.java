package com.gare.repository;

import com.gare.model.Gare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface GareRepository extends JpaRepository<Gare, Long> {
    List<Gare> findByVilleContainingIgnoreCase(String ville);
    List<Gare> findByNomContainingIgnoreCase(String nom);
    Optional<Gare> findByCodeGare(String codeGare);
    
    @Query("SELECT g FROM Gare g WHERE g.ville LIKE %:searchTerm% OR g.nom LIKE %:searchTerm%")
    List<Gare> searchGares(@Param("searchTerm") String searchTerm);
}