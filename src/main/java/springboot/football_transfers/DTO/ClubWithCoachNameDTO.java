package springboot.football_transfers.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClubWithCoachNameDTO {

    private Long clubId;
    private String footballClubName;
    private String coachNames;
    private String coachLastName;

}
