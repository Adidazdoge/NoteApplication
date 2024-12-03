package usecases.nevagateSignup;

/**
 * The output boundary interface for the NavigateSignup use case.
 * Defines the contract for presenting the results of the navigation process.
 */
public interface NevagateSignupOutputBoundary {
    /**
     * Presents the output data for the navigation logic.
     *
     * @param outputData The output data containing navigation results.
     */
    void present(NevagateSignupOutputData outputData);
}
