package usecases.accountsignup;

/**
 * Input boundary for the signup use case.
 * Defines the interface for initiating the signup process.
 */
public interface SignupInputBoundary {
    /**
     * Executes the signup use case with the provided input data.
     *
     * @param inputData The input data containing the username and password for registration.
     */
    void execute(SignupInputData inputData);
}
