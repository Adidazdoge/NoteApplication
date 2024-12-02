package app;

import java.io.IOException;

import frameworks.database.JsonLoginDataAccess;
import interface_adapters.login.LoginController;
import interface_adapters.login.LoginInterface;
import interface_adapters.login.LoginPresenter;
import usecases.accountlogin.LoginInputBoundary;
import usecases.accountlogin.LoginInteractor;
import usecases.accountlogin.LoginOutputBoundary;

/**
 * The LoginApplication class initializes and connects all components
 * for the login feature, including the interactor, controller, presenter,
 * and navigation manager.
 */
public class LoginApplication {
    private static String filePath = "PlayerFile";

    /**
     * Sets up the Login feature and returns the configured LoginController.
     *
     * @param loginView       The LoginInterface implementation for displaying login results.
     * @return A configured LoginController instance.
     * @throws IOException If there is an issue reading the JSON file.
     */
    public static LoginController initializeLogin(LoginInterface loginView)
            throws IOException {
        final JsonLoginDataAccess loginDataAccess = new JsonLoginDataAccess(filePath);

        final LoginPresenter loginPresenter = new LoginPresenter(loginView);

        final LoginOutputBoundary outputBoundary = loginPresenter;
        final LoginInputBoundary loginInteractor = new LoginInteractor(loginDataAccess, outputBoundary);

        return new LoginController(loginInteractor);
    }
}
