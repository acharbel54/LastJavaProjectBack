package com.gare.service;

import com.gare.model.Reservation;
import com.gare.model.Trajet;
import com.gare.model.User;
import com.gare.repository.ReservationRepository;
import com.gare.repository.TrajetRepository;
import com.gare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TrajetRepository trajetRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUserIdWithDetails(userId);
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Transactional
    public Reservation createReservation(Long userId, Long trajetId, Integer nombrePlaces) {
        // Vérifier que l'utilisateur existe
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Vérifier que le trajet existe
        Trajet trajet = trajetRepository.findById(trajetId)
                .orElseThrow(() -> new RuntimeException("Trajet non trouvé"));

        // Vérifier qu'il y a assez de places disponibles
        if (trajet.getPlacesDisponibles() < nombrePlaces) {
            throw new RuntimeException("Pas assez de places disponibles");
        }

        // Calculer le prix total
        BigDecimal prixTotal = trajet.getPrix().multiply(BigDecimal.valueOf(nombrePlaces));

        // Créer la réservation
        Reservation reservation = new Reservation(user, trajet, nombrePlaces, prixTotal);
        
        // Générer un numéro de réservation unique
        String numeroReservation = generateNumeroReservation();
        reservation.setNumeroReservation(numeroReservation);

        // Mettre à jour les places disponibles du trajet
        trajet.setPlacesDisponibles(trajet.getPlacesDisponibles() - nombrePlaces);
        trajetRepository.save(trajet);

        // Sauvegarder la réservation
        return reservationRepository.save(reservation);
    }

    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));

        if (reservation.getStatut() == Reservation.StatutReservation.ANNULEE) {
            throw new RuntimeException("Réservation déjà annulée");
        }

        // Remettre les places dans le trajet
        Trajet trajet = reservation.getTrajet();
        trajet.setPlacesDisponibles(trajet.getPlacesDisponibles() + reservation.getNombrePlaces());
        trajetRepository.save(trajet);

        // Marquer la réservation comme annulée
        reservation.setStatut(Reservation.StatutReservation.ANNULEE);
        reservationRepository.save(reservation);
    }

    @Transactional
    public Reservation updateReservationStatus(Long reservationId, Reservation.StatutReservation nouveauStatut) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));

        reservation.setStatut(nouveauStatut);
        return reservationRepository.save(reservation);
    }

    private String generateNumeroReservation() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return "RES" + timestamp + String.format("%03d", (int)(Math.random() * 1000));
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}