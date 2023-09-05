package springboot.football_transfers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springboot.football_transfers.DTO.PlayersByClubNameDTO;
import springboot.football_transfers.DTO.PlayersTransfersDTO;
import springboot.football_transfers.persistance.Player;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query(value = "SELECT new springboot.football_transfers.DTO.PlayersByClubNameDTO(" +
            "player.id, CONCAT(player.names,' ', player.lastName), fc.footballClubName, fc.id) " +
            "FROM Player player " +
            "JOIN FootballClub fc ON player.currentClubId = fc.id " +
            "WHERE fc.shortTermOfClubName = ?1")
    List<PlayersByClubNameDTO> findPlayersByClubName(String shortTermOfClubName);


    @Query("SELECT new springboot.football_transfers.DTO.PlayersTransfersDTO(" +
            "player.id, player.names, player.lastName, transfer.id, " +
            "clubBuying.footballClubName, clubSelling.footballClubName) " +
            "FROM Player player " +
            "INNER JOIN Transfer transfer ON player.id = transfer.playerBeingSoldId " +
            "INNER JOIN FootballClub clubSelling ON clubSelling.id = transfer.clubSellingId " +
            "INNER JOIN FootballClub clubBuying ON clubBuying.id = transfer.clubBuyingId")
    List<PlayersTransfersDTO> getPlayersTransfers();


}
