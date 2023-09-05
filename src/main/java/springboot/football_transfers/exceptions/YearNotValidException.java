package springboot.football_transfers.exceptions;

public class YearNotValidException extends RuntimeException{
    public YearNotValidException(String message){super(message);}
}
