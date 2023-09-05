package springboot.football_transfers.webController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.football_transfers.persistance.Coach;
import springboot.football_transfers.service.CoachService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coaches")
public class CoachController {
    private final CoachService coachService;

    @GetMapping("/{id}")
    public ResponseEntity<Coach> getId(@PathVariable Long id) {
        return ResponseEntity.ok(coachService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Coach>> getAll() {
        return ResponseEntity.ok(coachService.findAll());
    }

    @PostMapping
    public ResponseEntity<Coach> save(@RequestBody Coach coach) {
        return ResponseEntity.ok(coachService.save(coach));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        coachService.deleteById(id);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<Coach>> saveAll(@RequestBody List<Coach> coaches) {
        return ResponseEntity.ok(coachService.saveAll(coaches));
    }

    @GetMapping("/nation/{nationality}")
    public ResponseEntity<List<Coach>> findCoachesByNationality(@PathVariable String nationality) {
        return ResponseEntity.ok(coachService.getCoachesByNationality(nationality));
    }
}
