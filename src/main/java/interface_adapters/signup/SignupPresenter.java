package interface_adapters.signup;

import usecases.accountsignup.SignupOutputBoundary;
import usecases.accountsignup.SignupOutputData;

/**
 * The SignupPresenter class processes use case output for the signup operation
 * and converts it into a format suitable for the UI via the SignupInterface.
 */
public class SignupPresenter implements SignupOutputBoundary {
    // The UI interface to interact with
    private final SignupInterface view;

    /**
     * Constructs a new SignupPresenter with the specified SignupInterface.
     *
     * @param view The UI interface for displaying signup results.
     */
    public SignupPresenter(SignupInterface view) {
        this.view = view;
    }

    /**
     * Prepares the UI for a successful signup attempt.
     *
     * @param outputData The output data containing the success message.
     */
    @Override
    public void prepareSuccessView(SignupOutputData outputData) {
        view.displaySignupResult(outputData.getMessage());
    }

    /**
     * Prepares the UI for a failed signup attempt.
     *
     * @param outputData The output data containing the failure message.
     */
    @Override
    public void prepareFailureView(SignupOutputData outputData) {
        view.displaySignupError(outputData.getMessage());
    }
}
