package usecases.nevagateLogin;

/**
 * Output Boundary for navigating to the Login page.
 * Handles presentation logic for the use case.
 */
public interface NevagateLoginOutputBoundary {
    /**
     * Prepares the view for successful navigation.
     *
     * @param outputData The output data for navigation.
     */
    void prepareSuccessView(NevagateLoginOutputData outputData);
}
