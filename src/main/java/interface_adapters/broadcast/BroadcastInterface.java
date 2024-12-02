package interface_adapters.broadcast;

/**
 * Interface for broadcast view, ensures the UI adheres to this contract.
 */
public interface BroadcastInterface {
    /**
     * Updates the UI with the successful broadcast result.
     *
     * @param message The result message of the broadcast.
     */
    void updateUiBroadcast(String message);

    /**
     * Updates the UI with the error message in case of failure.
     *
     * @param errorMessage The error message indicating the failure reason.
     */
    void failureBroadcast(String errorMessage);
}
