package interface_adapters.controllers;

import usecases.accountsignup.SignupInputBoundary;
import usecases.accountsignup.SignupInputData;

/**
 * The SignupController class is responsible for handling user input from the UI
 * and invoking the Signup use case to process the user registration logic.
 * It acts as the bridge between the user interface and the application's business logic.
 */
public class SignupController {
    private final SignupInputBoundary signupUseCase;

    /**
     * Constructs a new SignupController with the specified SignupInputBoundary.
     *
     * @param signupUseCase The input boundary instance responsible for processing signup requests.
     */
    public SignupController(SignupInputBoundary signupUseCase) {
        this.signupUseCase = signupUseCase;
    }

    /**
     * Handles the signup process by creating a SignupInputData object with the provided
     * username and password, passing it to the Signup use case.
     *
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     */
    public void handleSignup(String username, String password) {
        // Create input data object for the use case
        final SignupInputData inputData = new SignupInputData(username, password);

        // Invoke the use case with the input data
        signupUseCase.execute(inputData);
    }
}
