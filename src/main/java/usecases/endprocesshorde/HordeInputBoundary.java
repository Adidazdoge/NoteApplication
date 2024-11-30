package usecases.endprocesshorde;

/**
 * Interactor will have execute method, take in inputdata and run.
 */
public interface HordeInputBoundary {

    /**
     * Execute the main logic.
     * @param inputData input data from player's side.
     */
    void execute(HordeInputData inputData);
}
