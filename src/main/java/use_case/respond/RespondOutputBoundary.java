package use_case.respond;

/**
 * Output boundary, responsible for passing output data to the controller.
 */
public interface RespondOutputBoundary {

    /**
     * Outputs for a successful respond.
     * @param outputData the output for updating the view.
     */
    void prepareSuccessView(RespondOutputData outputData);

    /**
     * If the respond is invaild, example: only choices 1,2,3,4(represented in number internally) but somehow player
     * input 5? though it shouldn't happen because at the end we provide player with only buttons.
     * @param errorMessage message of why is invaild, for the player.
     */
    void prepareFailureView(String errorMessage);
}
