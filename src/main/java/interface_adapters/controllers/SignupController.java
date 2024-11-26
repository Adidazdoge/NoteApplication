package interface_adapters.controllers;

import usecases.signup.SignupUseCase;

/**
 * Handles user input for signing up a new player.
 *
 * The SignupController is responsible for receiving input data from the UI layer
 * and passing this data to the SignupUseCase for processing.
 */
public class SignupController {
    private final SignupUseCase useCase;

    /**
     * Constructs a SignupController with the specified SignupUseCase.
     *
     * @param useCase The SignupUseCase that contains the business logic for signing up a new player.
     */
    public SignupController(SignupUseCase useCase) {
        this.useCase = useCase;
    }

    /**
     * Handles the signup request for a new player.
     *
     * @param username The desired username for the new player.
     * @param password The desired password for the new player.
     * @return A string message indicating the result of the signup attempt, such as
     *         success or failure with an explanation.
     */
    public String handleSignup(String username, String password) {
        return useCase.execute(username, password);
    }
}
