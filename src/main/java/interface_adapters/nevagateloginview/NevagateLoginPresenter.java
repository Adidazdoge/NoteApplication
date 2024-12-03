package interface_adapters.nevagateloginview;

import interface_adapters.NavigationManagerJson;
import usecases.nevagateLogin.NevagateLoginOutputBoundary;
import usecases.nevagateLogin.NevagateLoginOutputData;

/**
 * Presenter for navigating to the Login page.
 */
public class NevagateLoginPresenter implements NevagateLoginOutputBoundary {
    private final NavigationManagerJson navigationManager;

    public NevagateLoginPresenter(NavigationManagerJson navigationManager) {
        this.navigationManager = navigationManager;
    }

    @Override
    public void prepareSuccessView(NevagateLoginOutputData outputData) {
        // Trigger navigation via NavigationManager
        navigationManager.showLoginView();
    }
}
