package usecases.login;

/**
 * Output boundary for the login use case.
 * Defines methods for preparing success and failure views.
 */
public interface LoginOutputBoundary {
    /**
     * Prepares the view for a successful login.
     *
     * @param outputData The output data containing the success message.
     */
    void prepareSuccessView(LoginOutputData outputData);

    /**
     * Prepares the view for a failed login attempt.
     *
     * @param outputData The output data containing the success message.
     */
    void prepareFailureView(LoginOutputData outputData);
}
