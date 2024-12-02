package interface_adapters.nevagategame;

import interface_adapters.NavigationManager;
import usecases.nevagategame.NevagateGameOutputBoundary;
import usecases.nevagategame.NevagateGameOutputdata;

public class NevagateGamePresenter implements NevagateGameOutputBoundary {
    private NavigationManager navigationManager;

    public NevagateGamePresenter(NavigationManager navigationManager) {
        this.navigationManager = navigationManager;
    }

    @Override
    public void prepareGamePage(NevagateGameOutputdata outputdata) {
        navigationManager.showGameView();
    }
}
