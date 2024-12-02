package usecases.eventrespond.flood;

/**
 * Input boundary for handling Flood event responses.
 */
public interface FloodInputBoundary {
    void execute(FloodInputData inputData);
}
