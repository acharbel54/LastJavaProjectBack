package com.gare.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Données de connexion utilisateur")
public class LoginRequest {
    
    @NotBlank
    @Schema(description = "Nom d'utilisateur ou email", 
            example = "charbellll", 
            required = true)
    private String username;
    
    @NotBlank
    @Schema(description = "Mot de passe de l'utilisateur", 
            example = "1234567", 
            required = true)
    private String password;
    
    // Constructeurs
    public LoginRequest() {}
    
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // Getters et Setters
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}