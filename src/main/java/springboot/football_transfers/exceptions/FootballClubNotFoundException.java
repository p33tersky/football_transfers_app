package springboot.football_transfers.exceptions;

public class FootballClubNotFoundException extends RuntimeException {
    public FootballClubNotFoundException(String message) {
        super(message);
    }
}
