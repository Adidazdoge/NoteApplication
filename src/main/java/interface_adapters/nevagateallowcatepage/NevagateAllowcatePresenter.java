package interface_adapters.nevagateallowcatepage;

import interface_adapters.NavigationManager;
import usecases.nevagateAllowcatePage.NevagateAllowcateOutputBoundary;

public class NevagateAllowcatePresenter implements NevagateAllowcateOutputBoundary {
    private final NavigationManager navigationManager;

    public NevagateAllowcatePresenter(NavigationManager navigationManager) {
        this.navigationManager = navigationManager;
    }

    @Override
    public void nevagateAllowcatePage() {
        navigationManager.showCharacterCreationView();
    }

    @Override
    public void nevagateAllowcateFail(String errormessage) {
        // will not fail because this don't have any restriction.
    }
}
