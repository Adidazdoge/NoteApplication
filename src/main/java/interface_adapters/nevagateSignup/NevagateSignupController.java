package interface_adapters.nevagateSignup;

import usecases.nevagateSignup.NevagateSignupInputBoundary;
import usecases.nevagateSignup.NevagateSignupInputData;

/**
 * Controller for the NavigateSignup use case.
 * Acts as a bridge between the user interface and the interactor.
 */
public class NevagateSignupController {
    private final NevagateSignupInputBoundary interactor;

    /**
     * Constructs a NevagateSignupController instance.
     *
     * @param interactor The interactor responsible for processing the navigation logic.
     */
    public NevagateSignupController(NevagateSignupInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Triggers the navigation to the signup page by passing input data to the interactor.
     *
     * @param context A string representing the navigation context (e.g., "Navigate to Signup").
     */
    public void navigateToSignup(String context) {
        NevagateSignupInputData inputData = new NevagateSignupInputData(context);
        interactor.execute(inputData);
    }
}
