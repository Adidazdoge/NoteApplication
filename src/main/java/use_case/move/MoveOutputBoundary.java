package use_case.move;

/**
 * Interface of output boundary.
 */
public interface MoveOutputBoundary {

    /**
     * Outputs for a successful move.
     * @param outputData the output for updating the view.
     */
    void prepareSuccessView(MoveOutputData outputData);

    /**
     * If the move the invaild, example: is on 0,0(edge) but player moved up.
     * @param errorMessage message of why is invaild, for the player.
     */
    void prepareFailureView(String errorMessage);
}
