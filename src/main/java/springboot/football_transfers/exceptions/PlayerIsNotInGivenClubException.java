package springboot.football_transfers.exceptions;

public class PlayerIsNotInGivenClubException extends RuntimeException {

    public PlayerIsNotInGivenClubException(String message) {
        super(message);
    }

}
