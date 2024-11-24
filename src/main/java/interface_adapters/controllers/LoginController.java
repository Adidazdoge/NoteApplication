package interface_adapters.controllers;

import usecases.login.LoginRequest;
import usecases.login.LoginResponse;
import usecases.login.LoginUseCase;

/**
 * The LoginController class is responsible for handling user input from the UI
 * and invoking the LoginUseCase to process the login logic.
 * It serves as the bridge between the user interface and the business logic.
 */
public class LoginController {
    private final LoginUseCase useCase;

    /**
     * Constructs a new LoginController with the specified LoginUseCase.
     *
     * @param useCase The use case instance responsible for processing login requests.
     */
    public LoginController(LoginUseCase useCase) {
        this.useCase = useCase;
    }

    /**
     * This method creates a LoginRequest object using the provided username and password,
     * passes it to the LoginUseCase for processing, and handles the LoginResponse.
     * If the login is successful, it prints a success message along with the player's ID.
     * Otherwise, it prints an error message.
     *
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     */
    public void handleLogin(String username, String password) {
        final LoginRequest request = new LoginRequest(username, password);
        final LoginResponse response = useCase.execute(request);

        if (response.isSuccess()) {
            System.out.println(response.getMessage() + " Player ID: " + response.getPlayerId());
        }
        else {
            System.out.println(response.getMessage());
        }
    }
}
