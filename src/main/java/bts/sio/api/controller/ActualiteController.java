package bts.sio.api.controller;

import bts.sio.api.model.Actualite;
import bts.sio.api.service.ActualiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ActualiteController {

    @Autowired
    private ActualiteService actualiteService;

    @GetMapping("/actualite/{id}")
    public Actualite getActualite(@PathVariable("id") final Long id) {
        Optional<Actualite> actualite = actualiteService.getActualite(id);
        if(actualite.isPresent()) {
            return actualite.get();
        } else {
            return null;
        }
    }

    @GetMapping("/actualites")
    public Iterable<Actualite> getActualite() {
        return actualiteService.getActualites();
    }

    @PostMapping("/actualite")
    public Actualite createActualite(@RequestBody Actualite actualite) {
        return actualiteService.saveActualite(actualite);
    }

}