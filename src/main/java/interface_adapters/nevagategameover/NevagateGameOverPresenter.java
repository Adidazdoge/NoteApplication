package interface_adapters.nevagategameover;

import interface_adapters.NavigationManager;
import usecases.nevagategameover.NevagateGameOverOutputBoundary;
import usecases.nevagategameover.NevagateGameOverOutputdata;

/**
 * Nevagate game over view presenter.
 */
public class NevagateGameOverPresenter implements NevagateGameOverOutputBoundary {
    private NavigationManager navigationManager;

    public NevagateGameOverPresenter(NavigationManager navigationManager) {
        this.navigationManager = navigationManager;
    }

    @Override
    public void prepareGameOver(NevagateGameOverOutputdata outputdata) {
        navigationManager.showGameOverView();
    }
}
