# 🚆 GareConnect - Système de Gestion des Gares et Réservations

## 📝 Description du Projet

**GareConnect** est une application web complète de gestion des gares ferroviaires et des réservations de trajets. Elle comprend :

- **Backend** : API REST développée avec **Spring Boot (Java)**
- **Frontend** : Application web développée avec **Angular**

---

## 🧱 Architecture du Projet

```
ProjetGuisse/
├── gare-management-backend/          # API REST Spring Boot
│   ├── src/main/java/com/gare/
│   │   ├── controller/               # Contrôleurs REST
│   │   ├── model/                    # Entités JPA
│   │   ├── service/                  # Services métier
│   │   └── repository/               # Repositories JPA
│   └── pom.xml                       # Configuration Maven
└── gare-management-frontend/         # Application Angular
    ├── src/app/
    │   ├── components/               # Composants Angular
    │   ├── services/                 # Services Angular
    │   └── models/                   # Modèles TypeScript
    └── package.json                  # Configuration npm
```

---

## ✅ Prérequis

### Backend
- Java 17+
- Maven 3.6+
- Base de données H2 (intégrée)

### Frontend
- Node.js 16+ et npm
- Angular CLI 15+

---

## 🚀 Installation et Lancement

### 1. Lancer le Backend

```bash
cd gare-management-backend
mvn spring-boot:run
```

**Accessible sur :** http://localhost:8080

### 2. Lancer le Frontend

```bash
cd gare-management-frontend
npm install
ng serve
```

**Accessible sur :** http://localhost:4200

---

## 📚 Documentation API Détaillée

**Base URL :** http://localhost:8080

---

## 🔐 Authentification (`/api/auth`)

### POST /api/auth/register - Créer un utilisateur
**Headers :**
```
Content-Type: application/json
```

**Body :**
```json
{
  "username": "nomutilisateur",
  "email": "email@example.com",
  "password": "motdepasse",
  "nom": "Nom",
  "prenom": "Prénom"
}
```

### POST /api/auth/login - Connexion
**Headers :**
```
Content-Type: application/json
```
**Body :**
```json
{
  "username": "nomutilisateur",
  "password": "motdepasse"
}
```

### GET /api/auth/users - Liste des utilisateurs
*Aucun header ou body spécifique requis*

---

## 🚉 Gares (`/api/gares`)

### GET /api/gares - Liste des gares
*Aucun header ou body spécifique requis*

### GET /api/gares/{id} - Gare par ID
*Aucun header ou body spécifique requis*

### GET /api/gares/search?q= - Recherche
*Aucun header ou body spécifique requis*

### POST /api/gares - Créer une gare
**Headers :**
```
Content-Type: application/json
```
**Body :**
```json
{
  "nom": "Gare de Lyon",
  "ville": "Lyon",
  "codePostal": "69000"
}
```

### PUT /api/gares/{id} - Modifier une gare
**Headers :**
```
Content-Type: application/json
```
**Body :**
```json
{
  "nom": "Gare de Lyon Part-Dieu",
  "ville": "Lyon",
  "codePostal": "69003"
}
```

### DELETE /api/gares/{id} - Supprimer une gare
**Headers :**
```
Content-Type: application/json
```
**Body :** *Aucun body requis*

---

## 🚄 Trajets (`/api/trajets`)

### GET /api/trajets - Liste des trajets
*Aucun header ou body spécifique requis*

### GET /api/trajets/{id} - Trajet par ID
*Aucun header ou body spécifique requis*

### GET /api/trajets/search - Recherche filtrée
**Paramètres URL :**
- `gareDepartId` (optionnel)
- `gareArriveeId` (optionnel)
- `dateDepart` (optionnel, format ISO)

### POST /api/trajets - Créer un trajet
**Headers :**
```
Content-Type: application/json
```
**Body :**
```json
{
  "gareDepart": {
    "id": 1
  },
  "gareArrivee": {
    "id": 2
  },
  "dateDepart": "2024-01-15T10:30:00",
  "dateArrivee": "2024-01-15T14:30:00",
  "prix": 45.50,
  "placesDisponibles": 120
}
```

### POST /api/trajets/samples - Créer des trajets d'exemple
**Headers :**
```
Content-Type: application/json
```
**Body :** *Aucun body requis*

### PUT /api/trajets/{id} - Modifier un trajet
**Headers :**
```
Content-Type: application/json
```
**Body :**
```json
{
  "gareDepart": {
    "id": 1
  },
  "gareArrivee": {
    "id": 3
  },
  "dateDepart": "2024-01-15T11:00:00",
  "dateArrivee": "2024-01-15T15:00:00",
  "prix": 50.00,
  "placesDisponibles": 100
}
```

### DELETE /api/trajets/{id} - Supprimer un trajet
**Headers :**
```
Content-Type: application/json
```
**Body :** *Aucun body requis*

---

## 🎫 Réservations (`/api/reservations`)

### GET /api/reservations - Liste des réservations
*Aucun header ou body spécifique requis*

### GET /api/reservations/user/{userId} - Réservations par utilisateur
*Aucun header ou body spécifique requis*

### GET /api/reservations/{id} - Réservation par ID
*Aucun header ou body spécifique requis*

### POST /api/reservations - Créer une réservation
**Headers :**
```
Content-Type: application/json
```
**Body :**
```json
{
  "userId": 1,
  "trajetId": 1,
  "nombrePlaces": 2
}
```

### PUT /api/reservations/{id}/status - Modifier le statut
**Headers :**
```
Content-Type: application/json
```
**Body :**
```json
{
  "statut": "ANNULEE"
}
```
**Statuts possibles :** `CONFIRMEE`, `ANNULEE`, `EN_ATTENTE`

### PUT /api/reservations/{id}/cancel - Annuler une réservation
**Headers :**
```
Content-Type: application/json
```
**Body :** *Aucun body requis*

### DELETE /api/reservations/{id} - Supprimer une réservation
**Headers :**
```
Content-Type: application/json
```
**Body :** *Aucun body requis*

---

## 👤 Utilisateurs (`/api/users`)

### GET /api/users/{id} - Détails utilisateur
*Aucun header ou body spécifique requis*

---

## 🧪 Tests avec Postman

### Configuration initiale
1. Créer une collection **GareConnect API**
2. Définir une variable d'environnement `baseUrl = http://localhost:8080`

### Exemples de requêtes Postman

#### 1. Créer une gare
```
POST {{baseUrl}}/api/gares
Headers: Content-Type: application/json
Body:
{
  "nom": "Gare de Marseille",
  "ville": "Marseille",
  "codePostal": "13000"
}
```

#### 2. Créer un trajet
```
POST {{baseUrl}}/api/trajets
Headers: Content-Type: application/json
Body:
{
  "gareDepart": {"id": 1},
  "gareArrivee": {"id": 2},
  "dateDepart": "2024-02-15T09:00:00",
  "dateArrivee": "2024-02-15T13:00:00",
  "prix": 35.00,
  "placesDisponibles": 150
}
```

#### 3. Créer une réservation
```
POST {{baseUrl}}/api/reservations
Headers: Content-Type: application/json
Body:
{
  "userId": 1,
  "trajetId": 1,
  "nombrePlaces": 1
}
```

#### 4. Modifier le statut d'une réservation
```
PUT {{baseUrl}}/api/reservations/1/status
Headers: Content-Type: application/json
Body:
{
  "statut": "CONFIRMEE"
}
```

#### 5. Supprimer une gare
```
DELETE {{baseUrl}}/api/gares/1
Headers: Content-Type: application/json
Body: (vide)
```

---

## ⚠️ Gestion des erreurs

| Code HTTP | Description |
|-----------|-------------|
| 200 | Succès |
| 201 | Créé avec succès |
| 400 | Requête invalide |
| 404 | Ressource non trouvée |
| 500 | Erreur serveur |

### Format de réponse en erreur

```json
{
  "error": "Message d'erreur descriptif"
}
```

---

## 🔐 Sécurité

### Endpoints publics :
- `/api/auth/**`
- `/api/gares/**`
- `/api/trajets/**`
- `/api/reservations/**`
- `/h2-console/**`

---

## 🗃️ Base de Données H2

- **URL :** `jdbc:h2:file:./data/garedb`
- **Console H2 :** http://localhost:8080/h2-console
- **Utilisateur :** `sa`
- **Mot de passe :** (vide)

---

## ✨ Fonctionnalités principales

### Frontend
- Liste des gares
- Recherche et réservation
- Gestion personnelle des réservations
- Authentification utilisateur

### Backend
- API REST complète
- Base H2 intégrée
- Validation et gestion centralisée des erreurs
- CORS configuré pour le frontend

---

## 🧠 Notes Importantes

- ✅ **Bug corrigé :** Le frontend utilisait toujours le premier utilisateur, c'est maintenant corrigé.
- 🔐 L'endpoint `/api/users/{id}` est protégé.
- 🧪 Postman recommandé pour les tests manuels.
- 🧩 Utiliser `POST /api/trajets/samples` pour charger des exemples.
- 📋 **Headers obligatoires :** `Content-Type: application/json` pour tous les POST/PUT/DELETE

---

## 🐞 Dépannage

### Erreur 403
- Vérifiez que l'URL est autorisée.
- Utilisez `/api/auth/users` au lieu de `/api/users/{id}` si non authentifié.

### Connexion impossible
- Assurez-vous que le backend tourne sur 8080.
- Vérifiez que le frontend pointe bien vers cette URL.

### Données absentes
- Créez des entrées via les endpoints POST.
- Vérifiez la console H2 pour inspecter les tables.

### Erreur 400 (Bad Request)
- Vérifiez que le header `Content-Type: application/json` est présent
- Vérifiez que le format JSON du body est correct
- Vérifiez que tous les champs requis sont présents

---

## 👨‍💻 Auteurs

Projet initialement développé dans un cadre pédagogique.

---

## 📄 Licence

Ce projet est sous licence MIT.