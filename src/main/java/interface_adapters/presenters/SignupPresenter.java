package interface_adapters.presenters;

import usecases.accountsignup.SignupOutputBoundary;
import usecases.accountsignup.SignupOutputData;

/**
 * The SignupPresenter class is responsible for formatting the output data
 * from the Signup use case into a format suitable for the user interface.
 */
public class SignupPresenter implements SignupOutputBoundary {
    // Stores the formatted response message
    private String responseMessage;

    /**
     * Prepares the view for a successful signup attempt.
     *
     * @param outputData The output data containing the success message.
     */
    @Override
    public void prepareSuccessView(SignupOutputData outputData) {
        responseMessage = outputData.getMessage();
    }

    /**
     * Prepares the view for a failed signup attempt.
     *
     * @param outputData The error message to display.
     */
    @Override
    public void prepareFailureView(SignupOutputData outputData) {
        responseMessage = outputData.getMessage();
    }

    /**
     * Retrieves the formatted response message.
     *
     * @return A string representing the signup response.
     */
    public String getResponseMessage() {
        return responseMessage;
    }
}
