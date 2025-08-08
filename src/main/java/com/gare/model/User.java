package com.gare.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Schema(description = "Entité représentant un utilisateur du système")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique de l'utilisateur", 
            example = "1", 
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    @Schema(description = "Nom d'utilisateur unique", 
            example = "charbellll", 
            required = true)
    private String username;

    @NotBlank
    @Email
    @Column(unique = true)
    @Schema(description = "Adresse email unique et valide", 
            example = "charbeladonislll@gmail.com", 
            required = true)
    private String email;

    @NotBlank
    @Schema(description = "Mot de passe de l'utilisateur", 
            example = "1234567", 
            required = true)
    private String password;

    @Column(name = "first_name")
    @Schema(description = "Prénom de l'utilisateur", 
            example = "Charbell", 
            required = false)
    private String firstName;

    @Column(name = "last_name")
    @Schema(description = "Nom de famille de l'utilisateur", 
            example = "Adonis", 
            required = false)
    private String lastName;

    @Column(name = "phone_number")
    @Schema(description = "Numéro de téléphone de l'utilisateur", 
            example = "+33123456789", 
            required = false)
    private String phoneNumber;

    @Column(name = "created_at")
    @Schema(description = "Date et heure de création du compte", 
            example = "2025-01-08T14:30:00.123456", 
            accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Constructeurs
    public User() {}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}