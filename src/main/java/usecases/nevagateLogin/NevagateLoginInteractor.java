package usecases.nevagateLogin;

/**
 * Interactor for navigating to the Login page.
 * Contains the core business logic for navigation.
 */
public class NevagateLoginInteractor implements NevagateLoginInputBoundary {
    private final NevagateLoginOutputBoundary outputBoundary;

    public NevagateLoginInteractor(NevagateLoginOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(NevagateLoginInputData inputData) {
        // Perform any necessary logic here (e.g., logging, validation).
        outputBoundary.prepareSuccessView(new NevagateLoginOutputData("Navigating to Login Page..."));
    }
}
