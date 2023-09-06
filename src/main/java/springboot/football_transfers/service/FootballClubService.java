package springboot.football_transfers.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.football_transfers.DTO.ClubWithCoachNameDTO;
import springboot.football_transfers.DTO.ClubWithTheirExpensesOnGivenYearDTO;
import springboot.football_transfers.exceptions.CoachAlreadyLeadsATeamException;
import springboot.football_transfers.exceptions.FootballClubNotFoundException;
import springboot.football_transfers.exceptions.YearNotValidException;
import springboot.football_transfers.persistance.Coach;
import springboot.football_transfers.persistance.FootballClub;
import springboot.football_transfers.repository.FootballClubRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FootballClubService {

    private final FootballClubRepository footballClubRepository;
    private final CoachService coachService;

    public FootballClub findById(Long footballClubId) {
        return footballClubRepository.findById(footballClubId).orElseThrow(() -> new FootballClubNotFoundException("Club was not found"));
    }

    public List<FootballClub> findAll() {
        return footballClubRepository.findAll();
    }

    public void deleteById(Long coachId) {
        footballClubRepository.deleteById(coachId);
    }

    public FootballClub save(FootballClub footballClub) {
        return footballClubRepository.save(footballClub);
    }

    public List<FootballClub> saveAll(List<FootballClub> footballClubs) {
        return footballClubRepository.saveAll(footballClubs);
    }

    boolean isGivenCoachLeadingAnyClub(Coach coach) {
        return !(coach.getCoachesClubId() == null);
    }

    public void hireNewCoach(Long footballClubId, Long coachId) {

        FootballClub clubTryingToHireNewCoach = findById(footballClubId);
        Coach oldCoach = clubTryingToHireNewCoach.getCoach();
        Coach newCoach = coachService.findById(coachId);

        String coachName = newCoach.getNames() + " " + newCoach.getLastName();

        if (isGivenCoachLeadingAnyClub(newCoach)) {
            String coachClubsName = findById(newCoach.getCoachesClubId()).getFootballClubName();
            throw new CoachAlreadyLeadsATeamException(coachName + " already leads a club " + coachClubsName + "!");
        }

        oldCoach.setCoachesClubId(null);
        coachService.save(oldCoach);
        newCoach.setCoachesClubId(footballClubId);
        coachService.save(newCoach);
        clubTryingToHireNewCoach.setCoach(newCoach);
        save(clubTryingToHireNewCoach);
    }

    public List<ClubWithCoachNameDTO> getClubsWithCoachesFullNames() {
        return footballClubRepository.getClubsWithCoachesFullNames();
    }

    public List<ClubWithTheirExpensesOnGivenYearDTO> getClubsWithTheirEarlyExpenses(int year) {
        LocalDate today = LocalDate.now();
        if (year < 1950 || year > today.getYear()) {
            throw new YearNotValidException("Returning clubs expenses on given year is not possible");
        }
        return footballClubRepository.getClubsWithEarlyExpenses(year);
    }


}
