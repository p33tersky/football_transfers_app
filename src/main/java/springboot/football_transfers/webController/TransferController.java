package springboot.football_transfers.webController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.football_transfers.persistance.Transfer;
import springboot.football_transfers.service.FootballClubService;
import springboot.football_transfers.service.PlayerService;
import springboot.football_transfers.service.TransferService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transfers")
public class TransferController {


    private final TransferService transferService;
    private final FootballClubService footballClubService;
    private final PlayerService playerService;

    @GetMapping("/{id}")
    ResponseEntity<Transfer> getId(@PathVariable Long id) {
        return ResponseEntity.ok(transferService.findById(id));
    }

    @GetMapping
    ResponseEntity<List<Transfer>> getAll() {
        return ResponseEntity.ok(transferService.findAll());
    }


    @PostMapping("makeTransferOf/{playerId}/from/{clubSellingId}/to/{clubBuyingId}/cost/{amount}")
    public ResponseEntity<Transfer> save(@PathVariable Long playerId, @PathVariable Long clubSellingId, @PathVariable Long clubBuyingId, @PathVariable Double amount) {
        return ResponseEntity.ok(transferService.save(playerId, clubSellingId, clubBuyingId, amount));
    }

//    @PostMapping("transfer/{transferId}/toClub/{clubId}")
//    public void saveTransferInClubTransferHistoryList(@PathVariable Long transferId, @PathVariable Long clubId){
//        transferService.saveTransferInClubTransferHistoryList(clubId,transferId);
//    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        transferService.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        transferService.deleteAll();
    }

    @DeleteMapping("/dropTransfersFromEveryEntity")
    public void clearWholeTransferHistoryInEveryEntity() {
        transferService.clearWholeTransferHistoryFromEveryEntity(footballClubService.findAll(), playerService.findAll());
    }


}
