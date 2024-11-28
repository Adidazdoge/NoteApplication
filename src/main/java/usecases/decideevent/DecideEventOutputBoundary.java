package usecases.decideevent;

import usecases.move.MoveOutputData;

public interface DecideEventOutputBoundary {
    /**
     * Outputs for a successful move.
     * @param outputData the output for updating the view.
     */
    void prepareSuccessView(DecideEventOutputData outputData);

    /**
     * If the decide event is invaild, example: is on day 61? did player already lose?
     * @param errorMessage message of why is invaild, for the player.
     */
    void prepareFailureView(String errorMessage);
}
