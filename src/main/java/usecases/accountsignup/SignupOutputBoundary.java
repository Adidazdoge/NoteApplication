package usecases.accountsignup;

/**
 * Output boundary for the signup use case.
 * Defines methods for preparing success and failure views.
 */
public interface SignupOutputBoundary {
    /**
     * Prepares the view for a successful signup attempt.
     *
     * @param outputData The output data containing the success message.
     */
    void prepareSuccessView(SignupOutputData outputData);

    /**
     * Prepares the view for a failed signup attempt.
     *
     * @param outputData The output data containing the failure message.
     */
    void prepareFailureView(SignupOutputData outputData);
}
