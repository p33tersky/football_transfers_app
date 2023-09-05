package springboot.football_transfers.webController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.football_transfers.DTO.ClubWithCoachNameDTO;
import springboot.football_transfers.DTO.ClubWithTheirExpensesOnGivenYearDTO;
import springboot.football_transfers.persistance.FootballClub;
import springboot.football_transfers.service.FootballClubService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/footballClubs")
public class FootballClubController {

    private final FootballClubService footballClubService;

    @GetMapping("/{id}")
    ResponseEntity<FootballClub> getId(@PathVariable Long id) {
        return ResponseEntity.ok(footballClubService.findById(id));
    }

    @GetMapping
    ResponseEntity<List<FootballClub>> getAll() {
        return ResponseEntity.ok(footballClubService.findAll());
    }

    @PostMapping
    public ResponseEntity<FootballClub> save(@RequestBody FootballClub footballClub) {
        return ResponseEntity.ok(footballClubService.save(footballClub));
    }

    @PostMapping("/{clubId}/newCoach/{coachId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void getNewCoach(@PathVariable Long clubId, @PathVariable Long coachId) {
        footballClubService.getNewCoach(clubId, coachId);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        footballClubService.deleteById(id);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<FootballClub>> saveAll(@RequestBody List<FootballClub> footballClubs) {
        return ResponseEntity.ok(footballClubService.saveAll(footballClubs));
    }

    @GetMapping("/coaches")
    public ResponseEntity<List<ClubWithCoachNameDTO>> getClubsWithCoachesFullNames() {
        return ResponseEntity.ok(footballClubService.getClubsWithCoachesFullNames());
    }

    @GetMapping("/expenses")//?year=
    public ResponseEntity<List<ClubWithTheirExpensesOnGivenYearDTO>> getClubsWithTheirExpensesOnGivenYear(@RequestParam int year) {
        return ResponseEntity.ok(footballClubService.getClubsWithTheirEarlyExpenses(year));
    }


}
