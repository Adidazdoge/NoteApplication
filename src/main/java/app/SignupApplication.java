package app;


import java.io.IOException;

import frameworks.database.JsonSignupDataAccess;
import interface_adapters.signup.SignupController;
import interface_adapters.signup.SignupInterface;
import interface_adapters.signup.SignupPresenter;
import usecases.accountsignup.SignupInputBoundary;
import usecases.accountsignup.SignupInteractor;
import usecases.accountsignup.SignupOutputBoundary;

/**
 * Application class for Signup Use Case.
 * Responsible for assembling and initializing the components for the signup feature.
 */
public class SignupApplication {
    private static String filePath = "PlayerFile";

    /**
     * Initializes and assembles the components for the Signup use case.
     *
     * @param signupView The interface representing the view layer of the signup feature.
     * @return A SignupController instance that is connected to the use case.
     * @throws IOException if there is an issue accessing signup data files.
     */
    public static SignupController initializeSignup(SignupInterface signupView) throws IOException {
        // Data access layer
        final JsonSignupDataAccess signupDataAccess = new JsonSignupDataAccess(filePath);

        // Presenter
        final SignupPresenter signupPresenter = new SignupPresenter(signupView);

        // Output boundary is the presenter
        final SignupOutputBoundary outputBoundary = signupPresenter;

        // Use case interactor
        final SignupInputBoundary signupInteractor = new SignupInteractor(signupDataAccess, outputBoundary);

        // Controller
        return new SignupController(signupInteractor);
    }
}
