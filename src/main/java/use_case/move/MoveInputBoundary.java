package use_case.move;

/**
 * Input boundary of the use case move.
 */
public interface MoveInputBoundary {

    /**
     * Execute the move for player based on given direction.
     * @param inputdata direction player choose in MoveInputData format.
     */
    void execute(MoveInputData inputdata);
}
