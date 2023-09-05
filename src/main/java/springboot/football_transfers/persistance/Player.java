package springboot.football_transfers.persistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import springboot.football_transfers.enumerated.Position;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 50)
    @NotBlank(message = "Name is required!")
    private String names;

    @NotBlank(message = "Last name is required!")
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Nationality is required!")
    private String nationality;

    @Enumerated(value = EnumType.STRING)
    private Position position;


    @OneToMany
    private List<Transfer> transferHistory;

    private Long currentClubId;
}
