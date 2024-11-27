package interface_adapters.controllers;

import usecases.login.LoginInputBoundary;
import usecases.login.LoginInputData;

/**
 * The LoginController class is responsible for handling user input from the UI
 * and invoking the Login use case to process the login logic.
 * It acts as the bridge between the user interface and the application's business logic.
 */
public class LoginController {
    private final LoginInputBoundary loginUseCase;

    /**
     * Constructs a new LoginController with the specified LoginInputBoundary.
     *
     * @param loginUseCase The input boundary instance responsible for processing login requests.
     */
    public LoginController(LoginInputBoundary loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    /**
     * Handles the login process by creating a LoginInputData object with the provided
     * username and password, passing it to the Login use case, and returning the result.
     *
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     */
    public void handleLogin(String username, String password) {
        // Create input data object for the use case
        final LoginInputData inputData = new LoginInputData(username, password);

        // Invoke the use case with the input data
        loginUseCase.execute(inputData);
    }
}

