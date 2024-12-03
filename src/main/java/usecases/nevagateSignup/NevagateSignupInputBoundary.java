package usecases.nevagateSignup;

/**
 * The input boundary interface for the NavigateSignup use case.
 */
public interface NevagateSignupInputBoundary {
    /**
     * Executes the navigation to the signup page.
     *
     * @param inputData The data required for navigation, e.g., user context.
     */
    void execute(NevagateSignupInputData inputData);
}
