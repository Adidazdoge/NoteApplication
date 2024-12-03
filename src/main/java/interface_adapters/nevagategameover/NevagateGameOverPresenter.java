package interface_adapters.nevagategameover;

import interface_adapters.NavigationManager;
import usecases.nevagateGameover.NevagateGameOverOutputBoundary;
import usecases.nevagateGameover.NevagateGameOverOutputdata;

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
