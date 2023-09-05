package springboot.football_transfers.exceptions;

public class PlayerAlreadyHasAClubException extends RuntimeException{

    public PlayerAlreadyHasAClubException(String message){
        super(message);
    }
}
