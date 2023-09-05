package springboot.football_transfers.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayersTransfersDTO {

    private Long playerId;
    private String playerNames;
    private String playerLastName;
    private Long transferId;
    private String clubBuyingName;
    private String clubSellingName;

}
