package springboot.football_transfers.exceptions;

public class PlayerIsNotInAnyClubException extends RuntimeException {
    public PlayerIsNotInAnyClubException(String message){super(message);}
}
