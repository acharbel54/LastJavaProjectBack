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

## 📚 Documentation API

**Base URL :** http://localhost:8080

### 🔐 Authentification (`/api/auth`)

- `POST /api/auth/register` : Créer un utilisateur
- `POST /api/auth/login` : Connexion
- `GET /api/auth/users` : Liste des utilisateurs

### 🚉 Gares (`/api/gares`)

- `GET /api/gares` : Liste des gares
- `GET /api/gares/{id}` : Gare par ID
- `GET /api/gares/search?q=` : Recherche
- `POST /api/gares` : Créer
- `PUT /api/gares/{id}` : Modifier
- `DELETE /api/gares/{id}` : Supprimer

### 🚄 Trajets (`/api/trajets`)

- `GET /api/trajets` : Liste des trajets
- `GET /api/trajets/{id}` : Trajet par ID
- `GET /api/trajets/search` : Recherche filtrée
- `POST /api/trajets` : Créer
- `POST /api/trajets/samples` : Créer exemples
- `PUT /api/trajets/{id}` : Modifier
- `DELETE /api/trajets/{id}` : Supprimer

### 🎫 Réservations (`/api/reservations`)

- `GET /api/reservations` : Liste
- `GET /api/reservations/user/{userId}` : Réservations par utilisateur
- `GET /api/reservations/{id}` : Réservation par ID
- `POST /api/reservations` : Créer
- `PUT /api/reservations/{id}/status` : Modifier statut
- `PUT /api/reservations/{id}/cancel` : Annuler
- `DELETE /api/reservations/{id}` : Supprimer

### 👤 Utilisateurs (`/api/users`)

- `GET /api/users/{id}` : Détails utilisateur

---

## 🧪 Tests avec Postman

1. Créer une collection **GareConnect API**
2. Définir une variable d'environnement `baseUrl = http://localhost:8080`

### 3. Scénarios à tester

#### Authentification
- Créer, se connecter, afficher les utilisateurs

#### Gares
- Lister, créer, rechercher

#### Trajets
- Créer, chercher, afficher

#### Réservations
- Créer, consulter, annuler

### Headers requis
```
Content-Type: application/json
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

---

## 👨‍💻 Auteurs

Projet initialement développé dans un cadre pédagogique.

---

## 📄 Licence

Ce projet est sous licence MIT.