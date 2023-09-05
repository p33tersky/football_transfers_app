package springboot.football_transfers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springboot.football_transfers.DTO.PlayersTransfersDTO;
import springboot.football_transfers.persistance.Player;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    //NA POTEM
//    @Query(value = "SELECT p.names, p.last_name, p.nationality, p.position " +
//            "FROM football_club fc " +
//            "JOIN football_club_players fcp ON fc.id = fcp.football_club_id " +
//            "JOIN player p ON fcp.players_id = p.id " +
//            "WHERE fc.short_term_of_club_name = ?1", nativeQuery = true)
//    List<Player> findPlayersByClubName(String shortTermOfClubName);


    @Query("SELECT new springboot.football_transfers.DTO.PlayersTransfersDTO(" +
            "player.id, player.names, player.lastName, transfer.id, " +
            "clubBuying.footballClubName, clubSelling.footballClubName) " +
            "FROM Player player " +
            "INNER JOIN Transfer transfer ON player.id = transfer.playerBeingSoldId " +
            "INNER JOIN FootballClub clubSelling ON clubSelling.id = transfer.clubSellingId " +
            "INNER JOIN FootballClub clubBuying ON clubBuying.id = transfer.clubBuyingId")
    List<PlayersTransfersDTO> getPlayersTransfers();



}
