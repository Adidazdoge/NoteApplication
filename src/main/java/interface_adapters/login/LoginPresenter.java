package interface_adapters.login;

import interface_adapters.NavigationManagerJson;
import usecases.accountlogin.LoginOutputBoundary;
import usecases.accountlogin.LoginOutputData;

/**
 * The LoginPresenter class is responsible for formatting the output data
 * from the Login use case into a format suitable for the user interface.
 */
public class LoginPresenter implements LoginOutputBoundary {
    private final LoginInterface view;
    private final NavigationManagerJson navigationManager;

    /**
     * Constructs a LoginPresenter with the specified view and navigation manager.
     *
     * @param view              The view interface for displaying login results.
     * @param navigationManager The navigation manager for handling navigation logic.
     */
    public LoginPresenter(LoginInterface view, NavigationManagerJson navigationManager) {
        this.view = view;
        this.navigationManager = navigationManager;
    }

    /**
     * Prepares the view for a successful login attempt.
     */
    @Override
    public void prepareSuccessView(LoginOutputData outputData) {
        view.displayLoginResult(outputData.getMessage());
        if (outputData.isLoginSuccess()) {
            navigationManager.showMainView();
        }
    }

    /**
     * Prepares the view for a failed login attempt.
     */
    @Override
    public void prepareFailureView(LoginOutputData outputData) {
        view.displayLoginResult(outputData.getMessage());
    }
}
