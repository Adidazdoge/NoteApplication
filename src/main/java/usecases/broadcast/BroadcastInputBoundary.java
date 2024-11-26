package usecases.broadcast;

/**
 * Input boundary for the broadcast use case.
 */
public interface BroadcastInputBoundary {
    /**
     * Executes the broadcast action with the given input data.
     *
     * @param inputData The input data for the broadcast.
     */
    void execute(BroadcastInputData inputData);
}
