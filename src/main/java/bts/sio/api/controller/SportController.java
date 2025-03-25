package bts.sio.api.controller;

import bts.sio.api.model.Sport;
import bts.sio.api.service.SportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Tag(name = "Sport", description = "API de gestion des sports")
public class SportController {

    @Autowired
    private SportService sportService;

    /**
     * Create - Add a new sport
     * @param sport An object sport
     * @return The sport object saved
     */
    @PostMapping("/sport")
    @Operation(summary = "Créer un nouveau sport", description = "Crée un nouveau sport avec les informations fournies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sport créé avec succès",
                    content = @Content(schema = @Schema(implementation = Sport.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    public Sport createSport(@RequestBody Sport sport) {
        return sportService.saveSport(sport);
    }


    /**
     * Read - Get one sport
     * @param id The id of the sport
     * @return An Sport object full filled
     */
    @GetMapping("/sport/{id}")
    @Operation(summary = "Obtenir un sport par ID", description = "Renvoie un sport en fonction de l'ID fourni")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sport trouvé",
                    content = @Content(schema = @Schema(implementation = Sport.class))),
            @ApiResponse(responseCode = "404", description = "Sport non trouvé")
    })
    public Sport getSport(@Parameter(description = "ID du sport à récupérer") @PathVariable("id") final Long id) {
        Optional<Sport> sport = sportService.getSport(id);
        if(sport.isPresent()) {
            return sport.get();
        } else {
            return null;
        }
    }

    /**
     * Read - Get all sports
     * @return - An Iterable object of Sport full filled
     */
    @GetMapping("/sports")
    @Operation(summary = "Obtenir tous les sports", description = "Renvoie la liste de tous les sports")
    public Iterable<Sport> getSports() {
        return sportService.getSports();
    }

    /**
     * Update - Update an existing sport
     * @param id - The id of the sport to update
     * @param sport - The sport object updated
     * @return
     */
    @PutMapping("/sport/{id}")
    @Operation(summary = "Mettre à jour un sport", description = "Met à jour un sport avec les informations fournies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sport mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Sport non trouvé")
    })
    public Sport updateSport(@Parameter(description = "ID du sport à mettre à jour") @PathVariable("id") final Long id,
                             @RequestBody Sport sport) {
        Optional<Sport> s = sportService.getSport(id);
        if(s.isPresent()) {
            Sport currentSport = s.get();

            String nom = sport.getNom();
            if(nom != null) {
                currentSport.setNom(nom);
            }
            String descriptif = sport.getDescriptif();
            if(descriptif != null) {
                currentSport.setDescriptif(descriptif);;
            }

            sportService.saveSport(currentSport);
            return currentSport;
        } else {
            return null;
        }
    }


    /**
     * Delete - Delete a sport
     * @param id - The id of the sport to delete
     */
    @DeleteMapping("/sport/{id}")
    @Operation(summary = "Supprimer un sport", description = "Supprime un sport en fonction de l'ID fourni")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sport supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Sport non trouvé")
    })
    public void deleteSport(@Parameter(description = "ID du sport à supprimer") @PathVariable("id") final Long id) {
        sportService.deleteSport(id);
    }
}