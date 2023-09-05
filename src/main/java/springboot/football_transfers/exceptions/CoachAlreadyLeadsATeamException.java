package springboot.football_transfers.exceptions;

public class CoachAlreadyLeadsATeamException extends RuntimeException {
    public CoachAlreadyLeadsATeamException(String message) {
        super(message);
    }
}
