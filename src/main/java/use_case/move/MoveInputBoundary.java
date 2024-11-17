package use_case.move;

/**
 * Input boundary of the use case move.
 */
public interface MoveInputBoundary {

    /**
     * Execute the move for player based on given direction.
     * @param direction direction player choose.
     */
    void execute(String direction);
}
