package springboot.football_transfers.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlayersTransfersDTO {

    private Long playerId;
    private String playerNames;
    private String playerLastName;
    private Long transferId;
    private String clubBuyingName;
    private String clubSellingName;

}
