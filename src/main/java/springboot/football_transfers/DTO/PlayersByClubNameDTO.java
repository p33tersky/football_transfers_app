package springboot.football_transfers.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayersByClubNameDTO {
    private Long playerId;
    private String playerFullName;
    private String clubName;
    private Long clubId;
}
