package bts.sio.api.controller;

import bts.sio.api.model.Actualite;
import bts.sio.api.model.Epreuve;
import bts.sio.api.model.Sport;
import bts.sio.api.service.ActualiteService;
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
@Tag(name = "Actualité", description = "API de gestion des actualités")
public class ActualiteController {

    @Autowired
    private ActualiteService actualiteService;

    @GetMapping("/actualite/{id}")
    @Operation(summary = "Obtenir une actualité par ID", description = "Renvoie une actualité en fonction de l'ID fourni")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualité trouvée",
                    content = @Content(schema = @Schema(implementation = Actualite.class))),
            @ApiResponse(responseCode = "404", description = "Actualité non trouvée")
    })
    public Actualite getActualite(@Parameter(description = "ID de l'actualité à récupérer") @PathVariable("id") final Long id) {
        Optional<Actualite> actualite = actualiteService.getActualite(id);
        if(actualite.isPresent()) {
            return actualite.get();
        } else {
            return null;
        }
    }

    @GetMapping("/actualites")
    @Operation(summary = "Obtenir toutes les actualités", description = "Renvoie la liste de toutes les actualités")
    public Iterable<Actualite> getActualite() {
        return actualiteService.getActualites();
    }

    @PostMapping("/actualite")
    @Operation(summary = "Créer une nouvelle actualité", description = "Crée une nouvelle actualité avec les informations fournies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualité créée avec succès",
                    content = @Content(schema = @Schema(implementation = Actualite.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    public Actualite createActualite(@RequestBody Actualite actualite) {
        return actualiteService.saveActualite(actualite);
    }

    @PutMapping("/actualite/{id}")
    @Operation(summary = "Mettre à jour une actualité", description = "Met à jour une actualité avec les informations fournies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualité mise à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Actualité non trouvée")
    })
    public Actualite updateActualite(@Parameter(description = "ID de l'actualité à mettre à jour") @PathVariable("id") final Long id, @RequestBody Actualite actualite) {
        Optional<Actualite> a = actualiteService.getActualite(id);
        if (a.isPresent()) {
            Actualite currentActualite = a.get();

            String titre = actualite.getTitre();
            if (titre != null) {
                currentActualite.setTitre(titre);
            }

            String contenu = actualite.getContenu();
            if (contenu != null) {
                currentActualite.setContenu(contenu);
            }

            LocalDate dateActualite = actualite.getDateActualite();
            if (dateActualite != null) {
                currentActualite.setDateActualite(dateActualite);
            }

            Sport sport = actualite.getSport();
            if (sport != null) {
                currentActualite.setSport(sport);
            }

            Epreuve epreuve = actualite.getEpreuve();
            if (epreuve != null) {
                currentActualite.setEpreuve(epreuve);
            }

            actualiteService.saveActualite(currentActualite);
            return currentActualite;
        } else {
            return null;
        }
    }
}