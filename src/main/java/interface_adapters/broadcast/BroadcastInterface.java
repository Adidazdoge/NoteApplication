package interface_adapters.broadcast;
/**
 * Interface for broadcast view, ensures the UI adheres to this contract.
 */
public interface BroadcastInterface {
    /**
     * Updates the UI with the successful broadcast result.
     *
     * @param message The result message of the broadcast.
     * @param survivorsFound The number of survivors found (if any).
     * @param resourcesFound The resources found (if any).
     * @param attractedZombies True if zombies were attracted by the broadcast.
     */
    void updateUiBroadcast(String message, int survivorsFound, int resourcesFound, boolean attractedZombies);

    /**
     * Updates the UI with the error message in case of failure.
     *
     * @param errorMessage The error message indicating the failure reason.
     */
    void failureBroadcast(String errorMessage);
}
