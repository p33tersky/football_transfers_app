package springboot.football_transfers.exceptions;

public class CoachNotFoundException extends RuntimeException{
   public CoachNotFoundException(String message){super(message);}
}
