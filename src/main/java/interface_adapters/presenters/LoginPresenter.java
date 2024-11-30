package interface_adapters.presenters;

import usecases.accountlogin.LoginOutputBoundary;
import usecases.accountlogin.LoginOutputData;

/**
 * The LoginPresenter class is responsible for formatting the output data
 * from the Login use case into a format suitable for the user interface.
 */
public class LoginPresenter implements LoginOutputBoundary {
    // Stores the formatted response message
    private String responseMessage;

    /**
     * Prepares the view for a successful login attempt.
     *
     * @param outputData The output data containing the success message.
     */
    @Override
    public void prepareSuccessView(LoginOutputData outputData) {
        responseMessage = outputData.getMessage();
    }

    /**
     * Prepares the view for a failed login attempt.
     *
     * @param outputData The output data containing the failure message.
     */
    @Override
    public void prepareFailureView(LoginOutputData outputData) {
        responseMessage = outputData.getMessage();
    }

    /**
     * Retrieves the formatted response message.
     *
     * @return A string representing the login response.
     */
    public String getResponseMessage() {
        return responseMessage;
    }
}
