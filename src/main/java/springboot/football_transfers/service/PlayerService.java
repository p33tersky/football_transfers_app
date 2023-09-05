package springboot.football_transfers.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.condition.DisabledIf;
import org.springframework.stereotype.Service;
import springboot.football_transfers.DTO.PlayersByClubNameDTO;
import springboot.football_transfers.DTO.PlayersTransfersDTO;
import springboot.football_transfers.exceptions.*;
import springboot.football_transfers.persistance.FootballClub;
import springboot.football_transfers.persistance.Player;
import springboot.football_transfers.persistance.Transfer;
import springboot.football_transfers.repository.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class PlayerService {
    private final PlayerRepository playerRepository;
    private final FootballClubService footballClubService;

    public Player findById(Long playerId){
        return playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException("Player was not found"));
    }

    public List<Player> findAll(){
        return playerRepository.findAll();
    }

    public void deleteById(Long playerId){
        playerRepository.deleteById(playerId);
    }

    public void deleteAll(){
        playerRepository.deleteAll();
    }

    public Player save(Player player){
        return playerRepository.save(player);
    }

    public void savePlayerInGivenClub(Player player, FootballClub club){
        player.setCurrentClubId(club.getId());
        save(player);

        club.getPlayers().add(player);
        footballClubService.save(club);
    }

    public List<PlayersByClubNameDTO> findPlayersByShortTermOfClubName(String shortTermOfClubName){
        if (shortTermOfClubName.length() !=3 ){
            throw new ShortTermNotValidException("Short term has three signs");
        }
        return playerRepository.findPlayersByClubName(shortTermOfClubName);
    }

    public List<Player> saveAll(List<Player> players) {
        return playerRepository.saveAll(players);
    }

    public String getPlayerFullName(Long playerId){
        return findById(playerId).getNames() + " " + findById(playerId).getLastName();
    }

    public List<Transfer> transferHistoryOfGivenPlayer (Long playerId){
        return findById(playerId).getTransferHistory();
    }

    public void addPlayerToClubIfHeIsNotInAny(Long clubId, Long playerId){
        FootballClub club = footballClubService.findById(clubId);
        Player player = findById(playerId);
        if (player.getCurrentClubId() != null){
            throw new PlayerAlreadyHasAClubException(getPlayerFullName(player.getId()) + " already has a club. If You want him" +
                    "to move to somewhere else You should make a transfer");
        }
        savePlayerInGivenClub(player, club);
    }

    public boolean isGivenPlayerInGivenClub(Long clubId, Long playerId) {
        FootballClub givenClub = footballClubService.findById(clubId);
        for (int i = 0; i < givenClub.getPlayers().size(); i++) {
            if (Objects.equals(givenClub.getPlayers().get(i).getId(), playerId)) {
                return true;
            }
        }
        return false;
    }

    public void throwExceptionIfPlayerIsNotInGivenClub(Long playerId, Long clubId){
        String playerFullName = getPlayerFullName(playerId);
        String footballClubSellingName = footballClubService.findById(clubId).getFootballClubName();
        if (!isGivenPlayerInGivenClub(clubId, playerId)){
            throw new PlayerIsNotInGivenClubException("Player " + playerFullName +" is not in club " + footballClubSellingName);
        }
    }

    public void removeGivenPlayerFromGivenClub(Long playerId, Long clubId){
        FootballClub club = footballClubService.findById(clubId);
        Player player = findById(playerId);

        throwExceptionIfPlayerIsNotInGivenClub(playerId, clubId);

        club.getPlayers().remove(player);
        footballClubService.save(club);
    }

    public void firePlayer(Long playerId){

        if (findById(playerId).getCurrentClubId() != null){
            Player player = findById(playerId);
            FootballClub club = footballClubService.findById(player.getCurrentClubId());

            club.getPlayers().remove(player);
            footballClubService.save(club);

            player.setCurrentClubId(null);
            save(player);
        }

    }

    public List<PlayersTransfersDTO> getPlayersAndTheirTransfers(){
        return playerRepository.getPlayersTransfers();
    }

    public List<PlayersTransfersDTO> getEveryTransferOfGivenPlayer(Long playerId){
        return playerRepository.getPlayersTransfers()
                .stream()
                .filter(p -> p.getPlayerId().equals(playerId))
                .collect(Collectors.toList());
    }


}
