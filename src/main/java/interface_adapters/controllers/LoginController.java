package interface_adapters.controllers;

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
     *
     * @return The response for the giver username and password.
     */
    public String handleLogin(String username, String password) {
        return useCase.execute(username, password);
    }
}
