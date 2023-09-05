package springboot.football_transfers.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClubWithTheirExpensesOnGivenYearDTO {

    private String footballClubName;
    private Double yearlyExpense;
}
