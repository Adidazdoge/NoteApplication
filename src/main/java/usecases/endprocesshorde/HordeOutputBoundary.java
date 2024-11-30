package usecases.endprocesshorde;

/**
 * Prepare successview, on the game over page.
 */
public interface HordeOutputBoundary {

    /**
     * Prepare the success view with description of outcome and score player gained.
     * @param outputData outputdata.
     */
    void prepareSuccessView(HordeOutputData outputData);

    /**
     * Prepare the failure view by the error message, not needed likely.
     * @param errorMessage errormessage.
     */
    void prepareFailureView(String errorMessage);
}
