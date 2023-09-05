package springboot.football_transfers.persistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FootballClub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "club name is required!")
    private String footballClubName;

    @Size(min = 2, max = 3)
    private String shortTermOfClubName;

    @OneToMany
    private List<Player> players;

    @OneToOne
    private Coach coach;

//    @OneToMany
//    private List<Transfer> transfersHistory;

}
