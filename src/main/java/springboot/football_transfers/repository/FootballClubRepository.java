package springboot.football_transfers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springboot.football_transfers.DTO.ClubWithCoachNameDTO;
import springboot.football_transfers.DTO.ClubWithTheirExpensesOnGivenYearDTO;
import springboot.football_transfers.persistance.FootballClub;

import java.util.List;

public interface FootballClubRepository extends JpaRepository<FootballClub, Long> {

    @Query("SELECT new springboot.football_transfers.DTO.ClubWithCoachNameDTO(" +
            "footballClub.id, footballClub.footballClubName, " +
            " coach.names, coach.lastName) " +
            " FROM FootballClub footballClub " +
            " INNER JOIN Coach coach ON " +
            " footballClub.coach.id=coach.id")
    List<ClubWithCoachNameDTO>getClubsWithCoachesFullNames();

    @Query("SELECT new springboot.football_transfers.DTO.ClubWithTheirExpensesOnGivenYearDTO( " +
            "club.footballClubName, COALESCE(SUM(transfer.transactionAmountInUSD), 0.0)) " +
            " FROM FootballClub club" +
            " LEFT JOIN Transfer transfer" +
            " ON club.id = transfer.clubBuyingId" +
            " AND YEAR(transfer.dateOfTransfer) = :year" +
            " GROUP BY club.footballClubName")
    List<ClubWithTheirExpensesOnGivenYearDTO> getClubsWithEarlyExpenses(@Param("year") int year);

}
