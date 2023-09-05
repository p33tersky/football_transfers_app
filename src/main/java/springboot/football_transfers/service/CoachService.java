package springboot.football_transfers.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.football_transfers.exceptions.CoachNotFoundException;
import springboot.football_transfers.persistance.Coach;
import springboot.football_transfers.repository.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoachService {

    private final CoachRepository coachRepository;

    public Coach findById(Long coachId){
        return coachRepository.findById(coachId).orElseThrow(() -> new CoachNotFoundException("Coach was not found"));
    }

    public List<Coach> findAll(){
        return coachRepository.findAll();
    }

    public void deleteById(Long coachId){
        coachRepository.deleteById(coachId);
    }

    public Coach save (Coach coach){
        return coachRepository.save(coach);
    }

    public List<Coach> saveAll(List<Coach> coaches) {
        return coachRepository.saveAll(coaches);
    }

    public List<Coach> getCoachesByNationality(String nationality){
        return coachRepository.findByNationality(nationality);
    }



}
