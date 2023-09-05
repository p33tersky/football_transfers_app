package springboot.football_transfers.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClubWithCoachNameDTO {

    private Long clubId;
    private String footballClubName;
    private String coachNames;
    private String coachLastName;

}
