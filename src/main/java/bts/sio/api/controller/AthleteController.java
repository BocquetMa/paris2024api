package bts.sio.api.controller;

import bts.sio.api.model.Athlete;
import bts.sio.api.model.Sport;
import bts.sio.api.model.Epreuve;
import bts.sio.api.model.Pays;

import bts.sio.api.service.AthleteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;


import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Athlète", description = "API de gestion des athlètes")
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    /**
     * Create - Add a new athlete
     * @param athlete An object athlete
     * @return The athlete object saved
     */
    @PostMapping("/athlete")
    @Operation(summary = "Créer un nouvel athlète", description = "Crée un nouvel athlète avec les informations fournies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Athlète créé avec succès",
                    content = @Content(schema = @Schema(implementation = Athlete.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    public Athlete createAthlete(@RequestBody Athlete athlete) {
        return athleteService.saveAthlete(athlete);
    }


    /**
     * Read - Get one athlete
     * @param id The id of the athlete
     * @return An Athlete object full filled
     */
    @GetMapping("/athlete/{id}")
    @Operation(summary = "Obtenir un athlète par ID", description = "Renvoie un athlète en fonction de l'ID fourni")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Athlète trouvé",
                    content = @Content(schema = @Schema(implementation = Athlete.class))),
            @ApiResponse(responseCode = "404", description = "Athlète non trouvé")
    })
    public Athlete getAthlete(@Parameter(description = "ID de l'athlète à récupérer") @PathVariable("id") final Long id) {
        Optional<Athlete> athlete = athleteService.getAthlete(id);
        if(athlete.isPresent()) {
            return athlete.get();
        } else {
            return null;
        }
    }

    /**
     * Read - Get all athletes
     * @return - An Iterable object of Athlete full filled
     */
    @GetMapping("/athletes")
    @Operation(summary = "Obtenir tous les athlètes", description = "Renvoie la liste de tous les athlètes")
    public Iterable<Athlete> getAthletes() {
        return athleteService.getAthletes();
    }

    /**
     * Update - Update an existing athlete
     * @param id - The id of the athlete to update
     * @param athlete - The athlete object updated
     * @return
     */
    @PutMapping("/athlete/{id}")
    @Operation(summary = "Mettre à jour un athlète", description = "Met à jour un athlète avec les informations fournies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Athlète mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Athlète non trouvé")
    })
    public Athlete updateAthlete(@Parameter(description = "ID de l'athlète à mettre à jour") @PathVariable("id") final Long id,
                                 @RequestBody Athlete athlete) {
        Optional<Athlete> e = athleteService.getAthlete(id);
        if (e.isPresent()) {
            Athlete currentAthlete = e.get();

            String nom = athlete.getNom();
            if (nom != null) {
                currentAthlete.setNom(nom);
            }

            String prenom = athlete.getPrenom();
            if (prenom != null) {
                currentAthlete.setPrenom(prenom);
            }

            LocalDate dateNaissance = athlete.getDateNaiss();
            if (dateNaissance != null) {
                currentAthlete.setDateNaiss(dateNaissance);
            }

            Sport sport = athlete.getSport();
            if (sport != null) {
                currentAthlete.setSport(sport);
            }

            Pays pays = athlete.getPays();
            if (pays != null) {
                currentAthlete.setPays(pays);
            }

            athleteService.saveAthlete(currentAthlete);
            return currentAthlete;
        } else {
            return null; // Gérer l'absence d'athlète
        }
    }
    /**
     * Delete - Delete an athlete
     * @param id - The id of the athlete to delete
     */
    @DeleteMapping("/athlete/{id}")
    @Operation(summary = "Supprimer un athlète", description = "Supprime un athlète en fonction de l'ID fourni")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Athlète supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Athlète non trouvé")
    })
    public void deleteAthlete(@Parameter(description = "ID de l'athlète à supprimer") @PathVariable("id") final Long id) {
        athleteService.deleteAthlete(id);
    }

    @GetMapping("/{athleteId}/epreuves")
    @Operation(summary = "Obtenir les épreuves par ID d'athlète", description = "Renvoie toutes les épreuves associées à un athlète")
    public List<Epreuve> getEpreuvesByAthlete(@Parameter(description = "ID de l'athlète") @PathVariable Long athleteId){
        return athleteService.getEpreuvesByAthleteId(athleteId);
    }

    @PostMapping("/{athleteId}/epreuves")
    @Operation(summary = "Ajouter des épreuves à un athlète", description = "Associe plusieurs épreuves à un athlète")
    public Athlete addEpreuvesToAthlete(@Parameter(description = "ID de l'athlète") @PathVariable Long athleteId,
                                        @RequestBody List<Long> epreuves) {
        return athleteService.addEpreuvesToAthlete(athleteId, epreuves);
    }
}