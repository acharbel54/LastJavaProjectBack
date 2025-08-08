package com.gare.controller;

import com.gare.dto.LoginRequest;
import com.gare.model.User;
import com.gare.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Authentification", description = "API pour la gestion de l'authentification et des utilisateurs")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/register")
    @Operation(summary = "Enregistrer un nouvel utilisateur", 
               description = "Créer un nouveau compte utilisateur avec les informations fournies")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                    description = "Utilisateur enregistré avec succès",
                    content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", 
                    description = "Données invalides ou utilisateur déjà existant",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<?> register(
            @Parameter(description = "Informations de l'utilisateur à enregistrer", required = true)
            @Valid @RequestBody User user) {
        try {
            User registeredUser = authService.register(user);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Utilisateur enregistré avec succès");
            response.put("user", registeredUser);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PostMapping("/login")
    @Operation(summary = "Connexion utilisateur", 
               description = "Authentifier un utilisateur avec son nom d'utilisateur et mot de passe")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                    description = "Connexion réussie",
                    content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", 
                    description = "Identifiants invalides",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<?> login(
            @Parameter(description = "Identifiants de connexion", required = true)
            @Valid @RequestBody LoginRequest loginRequest) {
        try {
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();
            
            User user = authService.authenticate(username, password);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Connexion réussie");
            response.put("user", user);
            // Ici vous pourriez ajouter un token JWT
            
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/users")
    @Operation(summary = "Récupérer tous les utilisateurs", 
               description = "Obtenir la liste complète de tous les utilisateurs enregistrés")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                    description = "Liste des utilisateurs récupérée avec succès",
                    content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", 
                    description = "Erreur lors de la récupération",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = authService.getAllUsers();
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Liste des utilisateurs récupérée avec succès");
            response.put("users", users);
            response.put("count", users.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Erreur lors de la récupération des utilisateurs");
            return ResponseEntity.badRequest().body(error);
        }
    }
}