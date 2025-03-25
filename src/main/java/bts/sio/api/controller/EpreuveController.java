package bts.sio.api.controller;

import bts.sio.api.model.Epreuve;

import bts.sio.api.service.EpreuveService;
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


import java.util.Optional;

@RestController
@Tag(name = "Épreuve", description = "API de gestion des épreuves")
public class EpreuveController {

    @Autowired
    private EpreuveService epreuveService;

    /**
     * Create - Add a new epreuve
     * @param epreuve An object epreuve
     * @return The epreuve object saved
     */
    @PostMapping("/epreuve")
    @Operation(summary = "Créer une nouvelle épreuve", description = "Crée une nouvelle épreuve avec les informations fournies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Épreuve créée avec succès",
                    content = @Content(schema = @Schema(implementation = Epreuve.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    public Epreuve createEpreuve(@RequestBody Epreuve epreuve) {
        return epreuveService.saveEpreuve(epreuve);
    }


    /**
     * Read - Get one epreuve
     * @param id The id of the epreuve
     * @return An epreuve object full filled
     */
    @GetMapping("/epreuve/{id}")
    @Operation(summary = "Obtenir une épreuve par ID", description = "Renvoie une épreuve en fonction de l'ID fourni")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Épreuve trouvée",
                    content = @Content(schema = @Schema(implementation = Epreuve.class))),
            @ApiResponse(responseCode = "404", description = "Épreuve non trouvée")
    })
    public Epreuve getEpreuve(@Parameter(description = "ID de l'épreuve à récupérer") @PathVariable("id") final Long id) {
        Optional<Epreuve> epreuve = epreuveService.getEpreuve(id);
        if(epreuve.isPresent()) {
            return epreuve.get();
        } else {
            return null;
        }
    }

    /**
     * Read - Get all epreuves
     * @return - An Iterable object of Epreuve full filled
     */
    @GetMapping("/epreuves")
    @Operation(summary = "Obtenir toutes les épreuves", description = "Renvoie la liste de toutes les épreuves")
    public Iterable<Epreuve> getEpreuves() {
        return epreuveService.getEpreuves();
    }

    /**
     * Update - Update an existing epreuve
     * @param id - The id of the epreuve to update
     * @param epreuve - The epreuve object updated
     * @return
     */
    @PutMapping("/epreuve/{id}")
    @Operation(summary = "Mettre à jour une épreuve", description = "Met à jour une épreuve avec les informations fournies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Épreuve mise à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Épreuve non trouvée")
    })
    public Epreuve updateEpreuve(@Parameter(description = "ID de l'épreuve à mettre à jour") @PathVariable("id") final Long id,
                                 @RequestBody Epreuve epreuve) {
        Optional<Epreuve> e = epreuveService.getEpreuve(id);
        if (e.isPresent()) {
            Epreuve currentEpreuve = e.get();

            String libelle = epreuve.getLibelle();
            if (libelle != null) {
                currentEpreuve.setLibelle(libelle);
            }

            String date_debut = epreuve.getDateDebut();
            if (date_debut != null) {
                currentEpreuve.setDateDebut(date_debut);
            }

            String date_fin = epreuve.getDateFin();
            if (date_fin != null) {
                currentEpreuve.setDateFin(date_fin);
            }

            epreuveService.saveEpreuve(currentEpreuve);
            return currentEpreuve;
        } else {
            return null;
        }
    }



    /**
     * Delete - Delete an epreuve
     * @param id - The id of the epreuve to delete
     */
    @DeleteMapping("/epreuve/{id}")
    @Operation(summary = "Supprimer une épreuve", description = "Supprime une épreuve en fonction de l'ID fourni")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Épreuve supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Épreuve non trouvée")
    })
    public void deleteEpreuve(@Parameter(description = "ID de l'épreuve à supprimer") @PathVariable("id") final Long id) {
        epreuveService.deleteEpreuve(id);
    }
}