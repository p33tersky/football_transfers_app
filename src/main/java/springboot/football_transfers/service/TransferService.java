package springboot.football_transfers.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.football_transfers.exceptions.TransferNotFoundException;
import springboot.football_transfers.persistance.FootballClub;
import springboot.football_transfers.persistance.Player;
import springboot.football_transfers.persistance.Transfer;
import springboot.football_transfers.repository.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;

    private final PlayerService playerService;
    private final FootballClubService footballClubService;


    public Transfer findById(Long transferId) {
        return transferRepository.findById(transferId).orElseThrow(() -> new TransferNotFoundException("Transfer was not found"));
    }

    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }

    public void deleteById(Long transferId) {
        transferRepository.deleteById(transferId);
    }

    public void deleteAll() {
        transferRepository.deleteAll();
    }

    public void clearWholeTransferHistoryFromEveryEntity(List<FootballClub> clubs, List<Player> players) {
        for (FootballClub club : clubs) {
//            club.setTransfersHistory(new ArrayList<>());
            club.setPlayers(new ArrayList<>());
        }
        for (Player player : players) {
            player.setTransferHistory(new ArrayList<>());
            playerService.firePlayer(player.getId());
        }

        deleteAll();
    }

    public void saveTransferInPlayerTransferHistoryList(Player player, Transfer transfer) {
        player.getTransferHistory().add(transfer);
        playerService.save(player);
    }


    @Transactional
    public Transfer save(Long playerToTransferId, Long clubSellingId, Long clubBuyingId, Double transactionAmountInUSD) {
        playerService.throwExceptionIfPlayerIsNotInGivenClub(playerToTransferId, clubSellingId);

        Player playerToTransfer = playerService.findById(playerToTransferId);

        Transfer transfer = new Transfer();
        transfer.setDateOfTransfer(LocalDate.now());
        transfer.setTransactionAmountInUSD(transactionAmountInUSD);
        transfer.setPlayerBeingSoldId(playerToTransferId);

        transfer.setClubSellingId(clubSellingId);
        playerService.removeGivenPlayerFromGivenClub(playerToTransferId, clubSellingId);

        transfer.setClubBuyingId(clubBuyingId);
        playerService.savePlayerInGivenClub(playerService.findById(playerToTransferId), footballClubService.findById(clubBuyingId));

        Transfer savedTransfer = transferRepository.save(transfer);

        saveTransferInPlayerTransferHistoryList(playerToTransfer, savedTransfer);

        return savedTransfer;
    }


}
