# 🏅 Paris 2024 - Système de Gestion

Application complète pour l'administration des Jeux Olympiques de Paris 2024, composée de deux parties distinctes :  
- **Backend API RESTful** développée en Spring Boot  
- **Frontend web** développé avec Thymeleaf et Bootstrap  

---

## Partie 1 – Backend API RESTful

### Description

API RESTful robuste exposant toutes les fonctionnalités métier pour la gestion des athlètes, sports, épreuves et actualités.

### Fonctionnalités principales

- Gestion des athlètes : CRUD complet  
- Gestion des sports olympiques  
- Gestion des épreuves et calendrier  
- Gestion des actualités  
- Sécurisation des endpoints  
- Documentation Swagger/OpenAPI intégrée  

### Technologies utilisées

- Java 17, Spring Boot  
- Spring Data JPA (MariaDB)  
- Swagger/OpenAPI pour documentation interactive  

### Endpoints principaux

| Méthode | URL                 | Description                  |
|---------|---------------------|------------------------------|
| GET     | /athletes           | Récupérer tous les athlètes  |
| GET     | /athlete/{id}       | Récupérer un athlète par ID  |
| POST    | /athlete            | Créer un nouvel athlète      |
| PUT     | /athlete/{id}       | Mettre à jour un athlète     |
| DELETE  | /athlete/{id}       | Supprimer un athlète         |
| GET     | /sports             | Récupérer tous les sports    |
| GET     | /epreuves           | Récupérer toutes les épreuves|

### Installation

1. Cloner le dépôt backend  
2. Configurer la base MariaDB et les paramètres dans application.properties  
3. Lancer l’application Spring Boot  
4. Accéder à la doc Swagger sur `http://localhost:8080/swagger-ui.html`

### WepApp - Front-end
- WepApp : https://github.com/BocquetMa/paris2024webapp
  
---

2025 Bocquet Mathéo
matheo.bocquet@outlook.fr
https://bocquetma.github.io/portfolio/html/projet/paris2024.html
