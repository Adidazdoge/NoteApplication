package interface_adapters.nevagateSignup;

import usecases.nevagateSignup.NevagateSignupOutputBoundary;
import usecases.nevagateSignup.NevagateSignupOutputData;
import interface_adapters.NavigationManagerJson;

/**
 * Presenter for the NavigateSignup use case.
 * Handles the navigation logic and delegates view updates to the NavigationManager.
 */
public class NevagateSignupPresenter implements NevagateSignupOutputBoundary {
    private final NavigationManagerJson navigationManager;

    /**
     * Constructs a NevagateSignupPresenter instance.
     *
     * @param navigationManager The navigation manager responsible for view transitions.
     */
    public NevagateSignupPresenter(NavigationManagerJson navigationManager) {
        this.navigationManager = navigationManager;
    }

    /**
     * Presents the navigation results by invoking the appropriate view transition.
     *
     * @param outputData The output data containing the navigation result message.
     */
    @Override
    public void present(NevagateSignupOutputData outputData) {
        // Navigate to the Signup view
        navigationManager.showSignUpView();
    }
}
