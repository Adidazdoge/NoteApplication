package usecases.broadcast;

/**
 * Output boundary for the broadcast use case.
 */
public interface BroadcastOutputBoundary {
    /**
     * Prepares the success view for a broadcast.
     *
     * @param outputData The output data for the broadcast.
     */
    void prepareSuccessView(BroadcastOutputData outputData);

    /**
     * Prepares the failure view for a broadcast.
     *
     * @param errorMessage The error message for the failure.
     */
    void prepareFailureView(String errorMessage);
}
