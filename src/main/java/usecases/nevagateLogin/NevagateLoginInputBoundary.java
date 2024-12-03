package usecases.nevagateLogin;

/**
 * Input Boundary for navigating to the Login page.
 * Acts as the entry point for the use case.
 */
public interface NevagateLoginInputBoundary {
    /**
     * Executes the navigation to the Login page with the provided input data.
     *
     * @param inputData The input data required for navigation.
     */
    void execute(NevagateLoginInputData inputData);
}
