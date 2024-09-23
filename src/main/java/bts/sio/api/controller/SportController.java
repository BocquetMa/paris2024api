package bts.sio.api.controller;

import bts.sio.api.model.Sport;
import bts.sio.api.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SportController {

    @Autowired
    private SportService sportService;

    /**
     * Create - Add a new athlete
     * @param sport An object athlete
     * @return The athlete object saved
     */
    @PostMapping("/sport")
    public Sport createSport(@RequestBody Sport sport) {
        return sportService.saveSport(sport);
    }


    /**
     * Read - Get one athlete
     * @param id The id of the athlete
     * @return An Athlete object full filled
     */
    @GetMapping("/athlete/{id}")
    public Sport getSport(@PathVariable("id") final Long id) {
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
    public Iterable<Sport> getSports() {
        return sportService.getSports();
    }

    /**
     * Update - Update an existing sport
     * @param id - The id of the sport to update
     * @param sport - The athlete object updated
     * @return
     */
    @PutMapping("/sport/{id}")
    public Sport updateSport(@PathVariable("id") final Long id, @RequestBody Sport sport) {
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
     * Delete - Delete an sport
     * @param id - The id of the sport to delete
     */
    @DeleteMapping("/sport/{id}")
    public void deleteSport(@PathVariable("id") final Long id) {
        sportService.deleteSport(id);
    }

}
