package bts.sio.api.service;

import bts.sio.api.model.Athlete;
import bts.sio.api.model.Epreuve;
import bts.sio.api.repository.AthleteRepository;
import bts.sio.api.repository.EpreuveRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Data
@Service
public class AthleteService {
    @Autowired
    private AthleteRepository athleteRepository;
    @Autowired
    private EpreuveRepository epreuveRepository;

    public Optional<Athlete> getAthlete(final Long id) {
        return athleteRepository.findById(id);
    }

    public Iterable<Athlete> getAthletes() {
        return athleteRepository.findAll();
    }

    public void deleteAthlete(final Long id) {
        athleteRepository.deleteById(id);
    }

    public Athlete saveAthlete(Athlete athlete) {
        Athlete savedAthlete = athleteRepository.save(athlete);
        return savedAthlete;
    }

    public List<Epreuve> getEpreuvesByAthleteId(Long athleteId){
        Optional<Athlete> athleteOptional = athleteRepository.findById(athleteId);

        if (athleteOptional.isPresent()){
            Athlete athlete = athleteOptional.get();
            return athlete.getEpreuves();
        }
        throw new RuntimeException("Athlete not found");
    }

    public Athlete addEpreuvesToAthlete(Long athleteId, List<Long> epreuveIds) {
        Athlete athlete = athleteRepository.findById(athleteId)
                .orElseThrow(() -> new RuntimeException("Athlete not found with id: " + athleteId));

        for (Long epreuveId : epreuveIds){
            Epreuve epreuve = epreuveRepository.findById(epreuveId)
                    .orElseThrow(() -> new RuntimeException("Epreuve not found with id: " + epreuveId)) ;

                    athlete.getEpreuves().add(epreuve);
        }
        athleteRepository.save(athlete);
        return athlete;

    }
}
