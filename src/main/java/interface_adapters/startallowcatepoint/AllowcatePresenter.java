package interface_adapters.startallowcatepoint;

import interface_adapters.NavigationManager;
import usecases.startallowcate.AllowcateOutputBoundary;
import usecases.startallowcate.AllowcateOutputData;

/**
 * Allowcate presenter.
 */
public class AllowcatePresenter implements AllowcateOutputBoundary {
    private final NavigationManager navigationManager;
    private final AllowcateInterface view;

    public AllowcatePresenter(AllowcateInterface view, NavigationManager navigationManager) {
        this.view = view;
        this.navigationManager = navigationManager;
    }

    @Override
    public void NevagateStartGame(AllowcateOutputData outputData) {
        navigationManager.showGameView();
    }

    @Override
    public void preparefailureview(String failmessage) {
        view.failureAllowcate(failmessage);
    }
}
