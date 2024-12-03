package usecases.nevagateSignup;

/**
 * The interactor for the NavigateSignup use case.
 * Implements the business logic for navigating to the signup page.
 */
public class NevagateSignupInteractor implements NevagateSignupInputBoundary {
    private final NevagateSignupOutputBoundary outputBoundary;

    /**
     * Constructs a NevagateSignupInteractor instance.
     *
     * @param outputBoundary The output boundary for presenting the navigation results.
     */
    public NevagateSignupInteractor(NevagateSignupOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    /**
     * Executes the navigation to the signup page by invoking the output boundary.
     *
     * @param inputData The input data containing navigation context information.
     */
    @Override
    public void execute(NevagateSignupInputData inputData) {
        // Prepare output data
        NevagateSignupOutputData outputData = new NevagateSignupOutputData("Navigating to Signup");
        outputBoundary.present(outputData);
    }
}
