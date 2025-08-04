package com.gare.repository;

import com.gare.model.Reservation;
import com.gare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
    List<Reservation> findByUserId(Long userId);
    List<Reservation> findByUserOrderByDateReservationDesc(User user);
    Reservation findByNumeroReservation(String numeroReservation);
    
    @Query("SELECT r FROM Reservation r " +
           "JOIN FETCH r.trajet t " +
           "JOIN FETCH t.gareDepart " +
           "JOIN FETCH t.gareArrivee " +
           "JOIN FETCH r.user " +
           "WHERE r.user.id = :userId " +
           "ORDER BY r.dateReservation DESC")
    List<Reservation> findByUserIdWithDetails(@Param("userId") Long userId);
}