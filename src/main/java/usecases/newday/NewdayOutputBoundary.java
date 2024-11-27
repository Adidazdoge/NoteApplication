package usecases.newday;

/**
 * Output boundary for newday, either prepare a success use case view with message returned as output data, or failed.
 */
public interface NewdayOutputBoundary {
    /**
     * Outputs for a successful move.
     * @param outputData the output for updating the view.
     */
    void prepareSuccessView(NewdayOutputData outputData);

    /**
     * If the usecase is invaild, example: 60 days this get called so intend to move to 61.
     * @param errorMessage message of why is invaild, for the player.
     */
    void prepareFailureView(String errorMessage);
}
