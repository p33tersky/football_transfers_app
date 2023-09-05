package springboot.football_transfers.webController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.football_transfers.DTO.PlayersTransfersDTO;
import springboot.football_transfers.persistance.Player;
import springboot.football_transfers.persistance.Transfer;
import springboot.football_transfers.service.FootballClubService;
import springboot.football_transfers.service.PlayerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;
    private final FootballClubService footballClubService;

    @GetMapping("/{id}")
    ResponseEntity<Player> getId(@PathVariable Long id){
        return ResponseEntity.ok(playerService.findById(id));
    }

    @GetMapping
    ResponseEntity<List<Player>>getAll(){
        return ResponseEntity.ok(playerService.findAll());
    }

    @PostMapping
    public ResponseEntity<Player> save(@RequestBody Player player){
        return ResponseEntity.ok(playerService.save(player));
    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<Player>> saveAll(@RequestBody List<Player> players) {
        return ResponseEntity.ok(playerService.saveAll(players));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        playerService.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        playerService.deleteAll();
    }

    @PostMapping("{clubId}/newPlayer/{playerId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void addPlayerToClubIfHeIsNotInAny(@PathVariable Long clubId, @PathVariable Long playerId){
        playerService.addPlayerToClubIfHeIsNotInAny(clubId, playerId);
    }

//    @PostMapping("playerToClub/{clubId}")
//    @ResponseStatus(value = HttpStatus.OK)
//    public void addPlayerToClubAnytime(@RequestBody Player player, @PathVariable Long clubId){
//        playerService.savePlayerInGivenClub(player,footballClubService.findById(clubId));
//    }

    @PostMapping("/FIRE/{playerId}")
    public void fire(@PathVariable Long playerId){
        playerService.firePlayer(playerId);
    }

    @GetMapping("transferHistory/{playerId}")
    ResponseEntity<List<Transfer>> transferHistoryOfGivenPlayer(@PathVariable Long playerId){
        return ResponseEntity.ok(playerService.transferHistoryOfGivenPlayer(playerId));
    }

    // NA POTEM
//    @GetMapping("/fromClub") //?clubName=
//    ResponseEntity<List<Player>> playersFromGivenClubByShortOfClubName(@RequestParam String clubName){
//        return ResponseEntity.ok(playerService.findPlayersByShortTermOfClubName(clubName));
//    }

//    @DeleteMapping("/remove/{playerId}/from/{clubId}")
//    public void removeGivenPlayerFromGivenClub(@PathVariable Long playerId, @PathVariable Long clubId){
//        playerService.removeGivenPlayerFromGivenClub(playerId, clubId);
//    }

    @GetMapping("/transfers/all")
    ResponseEntity<List<PlayersTransfersDTO>> getPlayerAndTheirTransfers(){
        return ResponseEntity.ok(playerService.getPlayersAndTheirTransfers());
    }

    @GetMapping("/transfers/")//?playerId=
    ResponseEntity<List<PlayersTransfersDTO>> getEveryTransferOfGivenPlayer(@RequestParam Long playerId){
        return ResponseEntity.ok(playerService.getEveryTransferOfGivenPlayer(playerId));
    }
}