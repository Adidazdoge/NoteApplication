package usecases.eventinitialize;

/**
 * OutputBoundary of EventInitializer, it should display player with description, and group of choices button on the UI.
 * Can't think of a way to fail, just write something went wrong in eventinitiallze if it happened.
 */
public interface EventInitializeOutputBoundary {
    /**
     * Outputs for a successful move.
     * @param outputData the output for updating the view.
     */
    void prepareSuccessView(EventInitializeOutputData outputData);

    /**
     * If the use case is invaild. for example, no event was supposed to carry out but this got called.
     * @param errorMessage message of why is invaild, for the player.
     */
    void prepareFailureView(String errorMessage);
}
