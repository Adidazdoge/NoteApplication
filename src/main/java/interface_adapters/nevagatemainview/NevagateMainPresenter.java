package interface_adapters.nevagatemainview;

import interface_adapters.NavigationManager;
import interface_adapters.nevagateevent.NevagateEventInterface;
import usecases.nevagatemain.NevagateMainOutputBoundary;

public class NevagateMainPresenter implements NevagateMainOutputBoundary {
    private NavigationManager navigationManager;

    public NevagateMainPresenter(NavigationManager navigationManager) {
        this.navigationManager = navigationManager;
    }

    @Override
    public void prepareMainView() {
        navigationManager.showMainView();
    }
}
