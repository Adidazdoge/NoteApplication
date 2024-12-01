package interface_adapters.login;

import usecases.accountlogin.LoginOutputBoundary;
import usecases.accountlogin.LoginOutputData;

/**
 * The LoginPresenter class is responsible for formatting the output data
 * from the Login use case into a format suitable for the user interface.
 */
public class LoginPresenter implements LoginOutputBoundary {
    private final LoginInterface view;

    public LoginPresenter(LoginInterface view) {
        this.view = view;
    }

    /**
     * Prepares the view for a successful login attempt.
     */
    @Override
    public void prepareSuccessView(LoginOutputData outputData) {
        view.displayLoginResult(outputData.getMessage());
    }

    /**
     * Prepares the view for a failed login attempt.
     */
    @Override
    public void prepareFailureView(LoginOutputData outputData) {
        view.displayLoginResult(outputData.getMessage());
    }
}
