package bts.sio.api.controller;

import bts.sio.api.model.Pays;
import bts.sio.api.service.PaysService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Pays", description = "API de gestion des pays")
public class PaysController {

    @Autowired
    private PaysService paysService;

    /**
     * Read - Get all pays
     * @return - An Iterable object of Pays full filled
     */
    @GetMapping("/pays")
    @Operation(summary = "Obtenir tous les pays", description = "Renvoie la liste de tous les pays")
    public Iterable<Pays> getAthletes() {
        return paysService.getLesPays();
    }
}