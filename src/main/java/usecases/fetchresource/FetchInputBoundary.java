package usecases.fetchresource;

/**
 * Input boundary for fetch.
 */
public interface FetchInputBoundary {

    /**
     * Get the input data and execute.
     * @param inputData inputdata type from player side, no needed.
     */
    void execute(FetchInputData inputData);
}
